import org.newdawn.slick.SlickException;

/**
 * The type Obstacle.
 */
public abstract class Obstacle extends Sprite {

    private final float speed;
    private boolean direction;
    private int sign;

    /**
     * Instantiates a new Obstacle.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param speed     the speed
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Obstacle(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y);
        this.speed = speed;
        this.direction = direction;
    }

    /**
     * Update.
     *
     * @param delta the delta
     * @throws SlickException the slick exception
     */
    public void update(int delta) throws SlickException {
        sign = getDirection() ? 1 : -1;

        setxPos(getxPos() + sign * getSpeed() * delta);
        //wrapping the object around once it moves off the screen
        if (getxPos() < 0 - getSprite().getWidth() / 2) {
            setxPos(App.SCREEN_WIDTH + getSprite().getWidth() / 2);
        }
        if (getxPos() > App.SCREEN_WIDTH + getSprite().getWidth() / 2) {
            setxPos(0 - getSprite().getWidth() / 2);
        }

        //update the bounding box of the object when it is active
        if (getBox() != null) {
            getBox().setX(getxPos());
        }
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * Gets sign.
     *
     * @return the sign
     */
    public int getSign() {
        return sign;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
