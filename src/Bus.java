import org.newdawn.slick.SlickException;

public class Bus extends Vehicle{

    public Bus(float x, float y, boolean direction) throws SlickException {
        super("assets/bus.png", x, y, 0.15f, direction);
    }

}
