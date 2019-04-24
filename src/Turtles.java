import org.newdawn.slick.SlickException;

/**
 * The type Turtles.
 */
public class Turtles extends Rideable {

    private int timer = 0;

    /**
     * Instantiates a new Turtles.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Turtles(float x, float y, boolean direction) throws SlickException {
        super("assets/turtles.png", x, y, 0.085f, direction);
    }

    /**
     * Update makes use of parent update and makes the turtles appear or disappear.
     *
     * @param delta the delta
     */
    public void update(int delta) throws SlickException {
        super.update(delta);
        timer += delta;

        if (timer > 9000) {
            appear();
            timer = 0;
        } else if (timer > 7000) {
            disappear();
        } else {
            appear();
        }

    }
}
