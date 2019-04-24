import org.newdawn.slick.SlickException;

/**
 * The type Rideable.
 */
public abstract class Rideable extends Obstacle {

    /**
     * Instantiates a new Rideable.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param speed     the speed
     * @param direction the direction
     * @throws SlickException the slick exception
     */
    public Rideable(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y, speed, direction);
    }

    /**
     * Move player.
     *
     * @param player the player
     * @param delta  the delta
     */
    public void movePlayer(Player player, int delta) {
        if ((player.getxPos() + player.getSprite().getWidth() / 2 <= App.SCREEN_WIDTH && this.getDirection()) ||
                (player.getxPos() - player.getSprite().getWidth() / 2 >= 0 && !this.getDirection())) {
            player.setxPos(player.getxPos() + getSign() * getSpeed() * delta);
        }
    }

    /**
     * Move sprite.
     *
     * @param sprite the sprite
     * @param delta  the delta
     */
    public void moveSprite(Sprite sprite, int delta) {
        sprite.setxPos(this.getxPos() + getSign() * getSpeed() * delta);
    }

}
