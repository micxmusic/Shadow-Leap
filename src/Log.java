import org.newdawn.slick.SlickException;

/**
 * The type Log.
 */
public class Log extends Rideable {
    /**
     * Instantiates a new Log.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Log(float x, float y, boolean direction) throws SlickException {
        super("assets/log.png", x, y, 0.1f, direction);
    }
}
