import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.util.Random;

/**
 * The type World.
 */
public class World {

    /**
     * The constant CONVERSION_UNIT to convert seconds to milliseconds.
     */
    public static final int CONVERSION_UNIT = 1000;

    /**
     * The constant TILE_SIZE.
     */
    public final static int TILE_SIZE = 48;

    private Player frog;
    private Level level;
    private int numLevel = 0;
    private int delayTimer = 0;
    private ExtraLife extralife;
    private int spawnTime = selectRandom(25, 35);
    private boolean gameComplete = false;

    private Image lives = new Image("assets/lives.png");

    /**
     * Instantiates a new World.
     *
     * @throws SlickException the slick exception
     */
    public World() throws SlickException {
        frog = new Player();
        extralife = new ExtraLife(spawnTime);
        try {
            level = new Level("assets/levels/" + numLevel + ".lvl");
        } catch (FileNotFoundException e) {
            System.exit(0);
        }
    }

    /**
     * Updates the world.
     *
     * @param input the input
     * @param delta the delta
     * @throws SlickException the slick exception
     */
    public void update(Input input, int delta) throws SlickException {

        //Updates for all vehicle objects
        for (Vehicle vehicle : level.getVehicles()) {
            vehicle.update(delta);

            if (frog.willCrash(vehicle) != -1 && vehicle instanceof Bulldozer) {
                frog.setFalse(frog.willCrash(vehicle));
            }

            if (frog.contactSprite(vehicle)) {
                if (vehicle instanceof Bulldozer) {
                    ((Bulldozer) vehicle).pushPlayer(frog, delta);
                } else {
                    frog.decreaseLife();
                }
            }
        }

        //Updates for all rideable objects
        for (Rideable rideable : level.getRideables()) {
            rideable.update(delta);
            if (rideable.getBox() != null) {
                if (frog.contactSprite(rideable)) {
                    frog.setRiding(true);
                    rideable.movePlayer(frog, delta);
                }
            }
        }

        //Updates for all tile objects
        for (Tile tile : level.getTiles()) {
            if (frog.contactSprite(tile) && tile.isDangerous && !frog.isRiding()) {
                frog.decreaseLife();
                break;
            }
            if (frog.willCrash(tile) != -1 && tile.isSolid) {
                frog.setFalse(frog.willCrash(tile));
            }
        }

        //Update the frog's movement only if it is not being blocked by solid object
        if (!level.Completed()) {
            frog.update(input);
        }

        //Reset all moves to be valid
        frog.resetMoves();

        //Goals update
        for (Tile completed : level.getGoals()) {
            if (frog.contactSprite(completed)) {
                if (completed.isDangerous) {
                    frog.decreaseLife();
                    break;
                } else {
                    completed.getSprite().setAlpha(1);
                    completed.setDangerous(true);
                    level.increaseGoalsFilled();
                    frog.resetPosition();
                }
            }
        }

        //Extra life update
        extralife.update(level.getLogs(), delta, frog);

        //Check for level completion
        if (level.Completed()) {
            if (delayTimer > 1000) {
                levelUp();
            } else {
                delayTimer += delta;
            }
        }
    }

    /**
     * Render.
     *
     * @param g the g
     */
    public void render(Graphics g) {
        // Draw all of the sprites in the game
        for (Tile tile : level.getTiles()) {
            tile.render();
        }

        for (Vehicle vehicle : level.getVehicles()) {
            vehicle.render();
        }

        for (Rideable rideable : level.getRideables()) {
            rideable.render();
        }

        for (Tile completed : level.getGoals()) {
            completed.render();
        }

        for (int i = 0; i < frog.getLives(); i++) {
            renderLives(24 + i * 32);
        }

        if (extralife.isActive()) {
            extralife.render();
        }
        frog.render();
    }

    private void renderLives(float xPos) {
        float yPos = 744;
        lives.drawCentered(xPos, yPos);
    }

    private void levelUp() throws SlickException {
        numLevel += 1;
        delayTimer = 0;
        if (extralife.isActive()) {
            extralife.deactivate();
        }
        try {
            level = new Level("assets/levels/" + numLevel + ".lvl");
        } catch (FileNotFoundException e) {
            gameComplete = true;
        }
    }

    /**
     * Select random int.
     *
     * @param min the min
     * @param max the max
     * @return the int
     */
    public static int selectRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Is game complete boolean that checks game state.
     *
     * @return the boolean
     */
    public boolean isGameComplete() {
        return gameComplete;
    }

    /**
     * Is game over boolean that checks if player had died.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return !frog.isAlive();
    }

}
