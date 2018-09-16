import org.newdawn.slick.SlickException;

public class Turtles extends Rideable {

    private int time = 0;

    public Turtles(float x, float y, boolean direction) throws SlickException {
        super("assets/turtles.png", x, y, 0.085f, direction);
    }

    public void update(int delta){
        super.update(delta);
        time += delta;

        if(time > 9000){
            appear();
            time = 0;
        } else if(time > 7000){
            disappear();
        } else {
            appear();
        }

    }
}
