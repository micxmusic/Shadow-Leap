import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * The type Sprite.
 */
public abstract class Sprite {

    private Image sprite;
    private float xPos;
    private float yPos;
    private BoundingBox box;

    /**
     * Instantiates a new Sprite.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public Sprite(String imageSrc, float x, float y) throws SlickException {
        sprite = new Image(imageSrc);
        xPos = x;
        yPos = y;
        box = new BoundingBox(sprite, xPos, yPos);
    }

    /**
     * Activates sprite image and bounding box.
     */
    public void appear() {
        if (sprite.getAlpha() < 1f) {
            sprite.setAlpha(sprite.getAlpha() + 0.1f);
        }
        setBox(new BoundingBox(sprite, xPos, yPos));
    }

    /**
     * Deactivates sprite image and bounding box.
     */
    public void disappear() {
        if (sprite.getAlpha() > 0f) {
            sprite.setAlpha(sprite.getAlpha() - 0.1f);
        }
        setBox(null);
    }

    /**
     * Render.
     */
    public void render() {
        sprite.drawCentered(xPos, yPos);
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * Sets sprite.
     *
     * @param sprite the sprite
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets x position.
     *
     * @return the x position
     */
    public float getxPos() {
        return xPos;
    }

    /**
     * Sets x position.
     *
     * @param xPos the x position
     */
    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    /**
     * Gets y position.
     *
     * @return the y position
     */
    public float getyPos() {
        return yPos;
    }

    /**
     * Sets y position.
     *
     * @param yPos the y position
     */
    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    /**
     * Gets box.
     *
     * @return the box
     */
    public BoundingBox getBox() {
        return box;
    }

    /**
     * Sets box.
     *
     * @param box the box
     */
    public void setBox(BoundingBox box) {
        this.box = box;
    }
}