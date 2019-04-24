import org.newdawn.slick.SlickException;

/**
 * The type Racecar.
 */
public class Racecar extends Vehicle {

    private Tile smoke2;
    private int smokeTime;
    private int time;
    private boolean smoke2Active = false;

    /**
     * Instantiates a new Racecar.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Racecar(float x, float y, boolean direction) throws SlickException {
        super("assets/racecar.png", x, y, 0.5f, direction);
        smokeTime = World.selectRandom(0, 4) * World.CONVERSION_UNIT;
        smoke2 = new Tile("assets/smoke2.png", getxPos() + -getSign() * World.TILE_SIZE,
                getyPos(), false, false);
        smoke2.getSprite().setAlpha(0f);
    }

    /**
     * Update makes use of parent update and updates the smoke emitted by the racecar.
     *
     * @param delta the delta
     */
    public void update(int delta) throws SlickException {
        super.update(delta);
        smoke2.setxPos(getxPos() + -getSign() * World.TILE_SIZE);

        //logic for animating smoke
        if (smoke2Active) {
            if (time > 500) {
                smoke2.disappear();
            }
            if (time > 1000) {
                smoke2Active = false;
                time = 0;
            }
        } else {
            if (time > smokeTime) {
                if (smoke2.getSprite().getAlpha() < 1) {
                    smoke2.appear();
                } else {
                    smoke2Active = true;
                    time = 0;
                }
            }
        }
        time += delta;
    }

    /**
     * Render the racecar and the smoke that follows it.
     */
    public void render() {
        super.render();
        smoke2.render();
    }
}
