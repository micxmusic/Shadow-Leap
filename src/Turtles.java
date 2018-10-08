import org.newdawn.slick.SlickException;

public class Turtles extends Rideable {

    private int timer = 0;

    public Turtles(float x, float y, boolean direction) throws SlickException {
        super("assets/turtles.png", x, y, 0.085f, direction);
    }

    public void update(int delta) throws SlickException{
        super.update(delta);
        timer += delta;

        if(timer > 9000){
            appear();
            timer = 0;
        } else if(timer > 7000){
            disappear();
        } else {
            appear();
        }

    }
}
