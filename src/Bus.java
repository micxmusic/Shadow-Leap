import org.newdawn.slick.SlickException;

/**
 * The type Bus.
 */
public class Bus extends Vehicle {

    private Tile smoke1, smoke2;
    private boolean smoke1Active = false, smoke2Active = false;
    private int time = 0, smokeTime;

    /**
     * Instantiates a new Bus.
     *
     * @param x         the x position
     * @param y         the y position
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Bus(float x, float y, boolean direction) throws SlickException {
        super("assets/bus.png", x, y, 0.15f, direction);
        smokeTime = World.selectRandom(0, 5) * World.CONVERSION_UNIT;
        smoke1 = new Tile("assets/smoke1.png", getxPos() + -getSign() * World.TILE_SIZE * 0.75f,
                getyPos(), false, false);
        smoke2 = new Tile("assets/smoke2.png", getxPos() + -getSign() * World.TILE_SIZE * 1.25f,
                getyPos(), false, false);
    }

    /**
     * Update makes use of parent update and updates the smoke emitted by the bus.
     *
     * @param delta the delta
     */
    public void update(int delta) throws SlickException {
        super.update(delta);
        smoke1.setxPos(getxPos() + -getSign() * World.TILE_SIZE * 0.75f);
        smoke2.setxPos(getxPos() + -getSign() * World.TILE_SIZE * 1.25f);

        //logic for animating smoke
        if (smoke1Active && smoke2Active) {
            if (time > 500 || isOutOfBounds()) {
                smoke2.disappear();
            }
            if (time > 1000 || isOutOfBounds()) {
                smoke1.disappear();
            }
            if (time > 1500) {
                smoke1Active = false;
                smoke2Active = false;
                time = 0;
            }

        } else {
            if (time > smokeTime && !isOutOfBounds()) {
                smoke1.appear();
                smoke1Active = true;
            }
            if (time > smokeTime + 500 && !isOutOfBounds()) {
                if (smoke2.getSprite().getAlpha() < 1) {
                    smoke2.appear();
                } else {
                    smoke2Active = true;
                    time = 0;
                }
            }
            if (smoke1Active && isOutOfBounds()) {
                smoke1.disappear();
                smoke1Active = false;
            }
        }
        time += delta;
    }

    /**
     * Render the bus and the smoke that follows it.
     */
    public void render() {
        super.render();
        if (smoke1Active) {
            smoke1.render();
        }
        if (smoke2Active) {
            smoke2.render();
        }
    }

    /**
     * Checks if the bus has hit the screen edges.
     *
     * @return the boolean
     */
    public boolean isOutOfBounds() {
        return (getBox().getRight() > App.SCREEN_WIDTH || getBox().getLeft() < 0);
    }
}
