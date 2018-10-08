import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.effects.FireEmitter;

import java.io.FileNotFoundException;
import java.util.Random;

public class World {

    public static final int CONVERSION_UNIT = 1000;

    private Player frog;
    private Level level;
    private int numLevel = 0;
    private int delayTimer = 0;
    private ExtraLife extralife;
    private boolean[] validMove = {true, true, true, true};
    private int spawnTime = selectRandom(25, 35);

    private Image lives = new Image("assets/lives.png");

    public World() throws SlickException {
        // Perform initialisation logic
        frog = new Player();
        extralife = new ExtraLife(spawnTime);
        try {
            level = new Level("assets/levels/" + numLevel + ".lvl");
        } catch (FileNotFoundException e) {
            System.exit(0);
        }

    }

    public void update(Input input, int delta) throws SlickException {

        //Updates for all vehicle objects
        for (Vehicle vehicle : level.getVehicles()) {
            vehicle.update(delta);

            if (frog.willCrash(vehicle) != -1 && vehicle instanceof Bulldozer) {
                validMove[frog.willCrash(vehicle)] = false;
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
                validMove[frog.willCrash(tile)] = false;
            }
        }

        //Update the frog's movement only if it is not being blocked by solid object
        if (!level.Completed()) {
            frog.update(input, validMove);
        }

        //Reset all moves to be valid
        for (int i = 0; i < 4; i++) {
            validMove[i] = true;
        }

        //Goals update
        for (Tile completed : level.getGoals()) {
            if (frog.contactSprite(completed)) {
                if(completed.isDangerous) {
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
                numLevel += 1;
                delayTimer = 0;
                if(extralife.isActive()){
                    extralife.deactivate();
                }
                try {
                    level = new Level("assets/levels/" + numLevel + ".lvl");
                } catch (FileNotFoundException e) {
                    System.exit(0);
                }
            } else {
                delayTimer += delta;
            }
        }
    }

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

    public static int selectRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
