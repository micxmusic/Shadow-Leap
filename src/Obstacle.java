import org.newdawn.slick.SlickException;

public abstract class Obstacle extends Sprite {

    private final float speed;
    private boolean direction;
    private int sign;

    public Obstacle(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y);
        this.speed = speed;
        this.direction = direction;
    }

    public void update(int delta) throws SlickException{
        sign = getDirection()? 1 : -1;
        setxPos(getxPos() + sign * getSpeed() * delta);
        if (getxPos() < 0 - getSprite().getWidth()/2) {
            setxPos(App.SCREEN_WIDTH + getSprite().getWidth()/2);
        }
        if(getxPos() > App.SCREEN_WIDTH + getSprite().getWidth()/2) {
            setxPos(0 - getSprite().getWidth()/2);
        }

        if(getBox() != null) {
            getBox().setX(getxPos());
        }
    }

    public float getSpeed() {
        return speed;
    }

    public boolean getDirection() {
        return direction;
    }

    public int getSign() {
        return sign;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
