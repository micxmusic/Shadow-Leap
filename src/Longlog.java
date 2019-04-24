import org.newdawn.slick.SlickException;

/**
 * The type Longlog.
 */
public class Longlog extends Rideable {
    /**
     * Instantiates a new Longlog.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Longlog(float x, float y, boolean direction) throws SlickException {
        super("assets/longlog.png", x, y, 0.07f, direction);
    }
}
