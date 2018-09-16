import org.newdawn.slick.SlickException;

public class Racecar extends Vehicle{

    public Racecar(float x, float y, boolean direction) throws SlickException {
        super("assets/racecar.png", x, y, 0.5f, direction);
    }
}
