import org.newdawn.slick.SlickException;

import java.util.ArrayList;

public class ExtraLife extends Sprite {

    private int direction = 1, offset = 0;
    private int movementTimer = 0, spawnTimer = 0, spawnTime;
    private boolean isActive = false;
    private Rideable log;

    private static final int DISAPPEAR_TIME = 14;

    public ExtraLife(int spawnTime) throws SlickException {
        super("assets/extralife.png", 0, 0);
        this.spawnTime = spawnTime;
        disappear();
    }

    public void update(ArrayList<Rideable> logs, int delta, Player player) {
        if (isActive) {
            if (movementTimer > 2000) {
                if (getxPos() + direction * TILE_SIZE > log.getBox().getRight() ||
                        getxPos() + direction * TILE_SIZE < log.getBox().getLeft()) {
                    direction = -direction;
                }
                offset += direction * TILE_SIZE;
                movementTimer = 0;
            }
            this.setxPos(log.getxPos() + offset);
            this.getBox().setX(getxPos());
            movementTimer += delta;

            if (spawnTimer > DISAPPEAR_TIME * World.CONVERSION_UNIT || player.addExtraLife(this)) {
                deactivate();
                spawnTimer = 0;
            }
        } else if (spawnTimer > spawnTime * World.CONVERSION_UNIT && !isActive()) {
            log = chooseLog(logs);
            activate(log);
            log.moveSprite(this, delta);
            spawnTimer = 0;
        } else {
            spawnTimer += delta;
        }
    }

    private Rideable chooseLog(ArrayList<Rideable> logs) {
        int logNumber = World.selectRandom(0, logs.size() - 1);
        return logs.get(logNumber);
    }

    public void deactivate() {
        this.disappear();
        isActive = false;
    }

    public void activate(Rideable log) {
        this.setyPos(log.getyPos());
        this.appear();
        isActive = true;
    }

    public boolean isActive() {
        return isActive;
    }
}
