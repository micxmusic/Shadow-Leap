import org.newdawn.slick.SlickException;

/**
 * The type Vehicle.
 */
public abstract class Vehicle extends Obstacle {

    /**
     * Instantiates a new Vehicle.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param speed     the speed
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Vehicle(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y, speed, direction);
    }

}