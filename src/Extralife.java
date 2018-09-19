import org.newdawn.slick.SlickException;

public class Extralife extends Sprite {

    int direction = 1;
    int offset = 0;
    int timer = 0;

    public Extralife(float x, float y) throws SlickException {
        super("assets/extralife.png", x, y);
    }

    public void update(Obstacle other, int delta) {
        if (timer > 2000) {
            if (getxPos() + direction * TILE_SIZE > other.getBox().getRight() ||
                    getxPos() + direction * TILE_SIZE < other.getBox().getLeft()) {
                direction = -direction;
            }
            offset += direction * TILE_SIZE;
            timer = 0;
        }
        this.setxPos(other.getxPos() + offset);
        getBox().setX(getxPos());
        timer += delta;

    }
}
