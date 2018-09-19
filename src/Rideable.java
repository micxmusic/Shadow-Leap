import org.newdawn.slick.SlickException;

public class Rideable extends Obstacle{

    public Rideable(String imageSrc, float x, float y, float speed, boolean direction) throws SlickException {
        super(imageSrc, x, y, speed, direction);
    }

    public void movePlayer(Player player, int delta){
        if(player.getxPos() + player.getSprite().getWidth()/2 < App.SCREEN_WIDTH &&
                player.getxPos() - player.getSprite().getWidth()/2 > 0) {
            player.setxPos(player.getxPos() + getSign() * getSpeed() * delta);
        }
    }

    public void moveSprite(Sprite sprite, int delta){
        sprite.setxPos(this.getxPos() + getSign() * getSpeed() * delta);
    }

}
