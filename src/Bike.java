import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The type Bike.
 */
public class Bike extends Vehicle {

    private static final float EPSILON = 0.000001f;
    private Image original = getSprite();
    private Image flipped = getSprite().getFlippedCopy(true, false);

    /**
     * Instantiates a new Bike.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Bike(float x, float y, boolean direction) throws SlickException {
        super("assets/bike.png", x, y, 0.2f, direction);
        if (!direction) {
            setSprite(getSprite().getFlippedCopy(true, false));
        }
    }

    /**
     * Update makes use of parent update and flips bike image and direction upon impact with edge.
     *
     * @param delta the delta
     */
    public void update(int delta) throws SlickException {
        super.update(delta);

        //checks if the bike has hit the edges to flip the image
        if (getxPos() - 24 < EPSILON) {
            setDirection(true);
            setSprite(original);
        }
        if (1000 - getxPos() < EPSILON) {
            setDirection(false);
            setSprite(flipped);
        }
        getBox().setX(getxPos());

    }
}
