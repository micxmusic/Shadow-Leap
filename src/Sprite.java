import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

public abstract class Sprite {

    public final static int TILE_SIZE = 48;

    private Image sprite;
    private float xPos;
    private float yPos;
    private BoundingBox box;

    public Sprite(String imageSrc, float x, float y) throws SlickException {
        sprite = new Image(imageSrc);
        xPos = x;
        yPos = y;
        box = new BoundingBox(sprite, xPos, yPos);
    }

    public void render() {
        sprite.drawCentered(xPos, yPos);
    }

    public Image getSprite() {
        return sprite;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) { this.xPos = xPos; }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) { this.yPos = yPos; }

    public BoundingBox getBox() {
        return box;
    }

    public void setBox(BoundingBox box) {
        this.box = box;
    }
}