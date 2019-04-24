import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * The type Extra life.
 */
public class ExtraLife extends Sprite {

    //initial spawn point and direction movement of object
    private int direction = 1, offset = 0;

    private int movementTimer = 0, spawnTimer = 0, spawnTime;
    private boolean isActive = false;
    private Rideable log;

    private static final int DISAPPEAR_TIME = 14;

    /**
     * Instantiates a new Extra life.
     *
     * @param spawnTime the spawn time selected for the object
     * @throws SlickException the slick exception
     */
    public ExtraLife(int spawnTime) throws SlickException {
        super("assets/extralife.png", 0, 0);
        this.spawnTime = spawnTime;
        disappear();
    }

    /**
     * Update.
     *
     * @param logs   the logs
     * @param delta  the delta
     * @param player the player
     */
    public void update(ArrayList<Rideable> logs, int delta, Player player) {
        //update the extra life object only if it is active
        if (isActive) {
            //movement timer to cycle the extra life object on the log
            if (movementTimer > 2000) {
                if (getxPos() + direction * World.TILE_SIZE > log.getBox().getRight() ||
                        getxPos() + direction * World.TILE_SIZE < log.getBox().getLeft()) {
                    direction = -direction;
                }
                offset += direction * World.TILE_SIZE;
                movementTimer = 0;
            }
            this.setxPos(log.getxPos() + offset);
            this.getBox().setX(getxPos());
            movementTimer += delta;

            //deactivate the extra life if the player hits it or the disappear time has been reached
            if (spawnTimer > DISAPPEAR_TIME * World.CONVERSION_UNIT || player.addExtraLife(this)) {
                deactivate();
                spawnTimer = 0;
            }
            //if the spawn time has been reached and there is no object active, then create it
        } else if (spawnTimer > spawnTime * World.CONVERSION_UNIT && !isActive()) {
            log = chooseLog(logs);
            activate(log);
            log.moveSprite(this, delta);
            spawnTimer = 0;
            //otherwise increase the spawn timer
        } else {
            spawnTimer += delta;
        }
    }

    /**
     * Choosing a log for the extra life to ride on.
     *
     * @param logs the list of logs in the game
     * @return a log
     */
    private Rideable chooseLog(ArrayList<Rideable> logs) {
        int logNumber = World.selectRandom(0, logs.size() - 1);
        return logs.get(logNumber);
    }

    /**
     * Deactivate the extra life.
     */
    public void deactivate() {
        this.disappear();
        isActive = false;
    }

    /**
     * Activate the extra life on a log.
     *
     * @param log the log to place the extra life object on
     */
    public void activate(Rideable log) {
        this.setyPos(log.getyPos());
        this.appear();
        isActive = true;
    }

    /**
     * Checks if extra life object is active.
     *
     * @return the boolean
     */
    public boolean isActive() {
        return isActive;
    }
}
