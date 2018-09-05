import org.newdawn.slick.SlickException;

public class Bus extends Sprite{

    private final static float SPEED = 0.15f;

    public Bus(float x, float y) throws SlickException {
        super("assets/bus.png", x, y);
    }

    public void update(int direction, int delta) {
        setxPos(getxPos() + direction * SPEED * delta);
        if (getxPos() < 0 - getSprite().getWidth()) {
            setxPos(App.SCREEN_WIDTH);
        }
        if(getxPos() > App.SCREEN_WIDTH) {
            setxPos(0 - getSprite().getWidth());
        }
        getBox().setX(getxPos());

    }

}
