import org.newdawn.slick.SlickException;

/**
 * The type Bulldozer.
 */
public class Bulldozer extends Vehicle {

    /**
     * Instantiates a new Bulldozer.
     *
     * @param x         the x
     * @param y         the y
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Bulldozer(float x, float y, boolean direction) throws SlickException {
        super("assets/bulldozer.png", x, y, 0.05f, direction);
    }

    /**
     * Bulldozer pushes player and decreases the life count of the player if the sprite is pushed against the edge.
     *
     * @param player the player
     * @param delta  the delta
     */
    public void pushPlayer(Player player, int delta) {
        player.setxPos(player.getxPos() + getSign() * getSpeed() * delta);

        //decrease player life if being pushed out of bounds
        if (player.getxPos() + player.getSprite().getWidth() / 2 > App.SCREEN_WIDTH ||
                player.getxPos() - player.getSprite().getWidth() / 2 < 0) {
            player.decreaseLife();
        }
    }
}
