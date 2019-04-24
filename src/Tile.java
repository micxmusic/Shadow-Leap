import org.newdawn.slick.SlickException;

/**
 * The type Tile.
 */
public class Tile extends Sprite {

    /**
     * The Is dangerous.
     */
    boolean isDangerous;
    /**
     * The Is solid.
     */
    boolean isSolid;

    /**
     * Instantiates a new Tile.
     *
     * @param imageSrc    the image src
     * @param x           the x
     * @param y           the y
     * @param isDangerous the is dangerous
     * @param isSolid     the is solid
     * @throws SlickException the slick exception
     */
    public Tile(String imageSrc, float x, float y, boolean isDangerous, boolean isSolid) throws SlickException {
        super(imageSrc, x, y);
        this.isDangerous = isDangerous;
        this.isSolid = isSolid;
    }

    /**
     * Sets dangerous.
     *
     * @param dangerous the dangerous
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }
}
