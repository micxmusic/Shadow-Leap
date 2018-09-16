import org.newdawn.slick.SlickException;

public abstract class Vehicle extends Obstacle {

    public Vehicle(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y, speed, direction);
    }

}