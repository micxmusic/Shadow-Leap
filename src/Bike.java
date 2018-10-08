import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bike extends Vehicle{

    private static final float EPSILON = 0.000001f;
    private Image original = getSprite();
    private Image flipped = getSprite().getFlippedCopy(true, false);

    public Bike(float x, float y, boolean direction) throws SlickException {
        super("assets/bike.png", x, y, 0.2f, direction);
        if(!direction){
            setSprite(getSprite().getFlippedCopy(true, false));
        }
    }

    public void update(int delta) throws SlickException{
        super.update(delta);
        if (getxPos() - 24 < EPSILON) {
            setDirection(true);
            setSprite(original);
        }
        if(1000 - getxPos() < EPSILON) {
            setDirection(false);
            setSprite(flipped);
        }
        getBox().setX(getxPos());

    }
}
