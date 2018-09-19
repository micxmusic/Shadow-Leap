import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.util.Random;

public class World {

    public final static int PLAYER_INITIAL_X = 512;
    public final static int PLAYER_INITIAL_Y = 720;
    public final int GOAL = 48;

    private Player frog;
    private Level level;
    private int numLevel = 0;
    private int timer = 0;
    private int delayTimer = 0;
    private Extralife extralife;
    private Rideable log;
    private boolean[] validMove = {true, true, true, true};
    private boolean extraLifeActive = false;
    private int spawnTime = selectRandom(25, 35);

    private Image lives = new Image("assets/lives.png");

    public World() throws SlickException {
        // Perform initialisation logic
        frog = new Player("assets/frog.png", PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
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

        if (frog.getyPos() == GOAL) {
            boolean reachedGoal = true;
            for (Tile completed : level.getGoals()) {
                if (frog.contactSprite(completed) || frog.willCrash(completed) != -1) {
                    frog.decreaseLife();
                    reachedGoal = false;
                    break;
                }
            }

            if (reachedGoal) {
                level.getGoals().add(new Tile("assets/frog.png", frog.getxPos(), frog.getyPos(), true, false));
                frog.resetPosition();
            }
        }

        //Extra life update
        if (extraLifeActive) {
            if (timer > 14000 || frog.addExtraLife(extralife)) {
                extralife.disappear();
                extraLifeActive = false;
                timer = 0;
            }
        } else if (timer > spawnTime * 1000 && !extraLifeActive) {
            int logNumber = selectRandom(0, level.getLogs().size() - 1);
            log = level.getLogs().get(logNumber);
            extralife = new Extralife(log.getxPos(), log.getyPos());
            log.moveSprite(extralife, delta);
            extraLifeActive = true;
            timer = 0;
        } else {
            timer += delta;
        }

        //Update extra life
        if (extraLifeActive) {
            extralife.update(log, delta);
        }

        //Check for level completion
        if (level.Completed()) {
            if (delayTimer > 1000) {
                numLevel += 1;
                delayTimer = 0;
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

        if (extraLifeActive) {
            extralife.render();
        }
        frog.render();
    }

    public void renderLives(float xPos) {
        float yPos = 744;
        lives.drawCentered(xPos, yPos);
    }

    public int selectRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }
}
