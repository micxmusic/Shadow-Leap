import org.newdawn.slick.SlickException;

public class Tile extends Sprite{

    boolean isDangerous;
    boolean isSolid;

    public Tile(String imageSrc, float x, float y, boolean isDangerous, boolean isSolid) throws SlickException {
        super(imageSrc, x, y);
        this.isDangerous = isDangerous;
        this.isSolid = isSolid;
    }

}
