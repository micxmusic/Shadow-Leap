import org.newdawn.slick.SlickException;

public class Bulldozer extends Vehicle {

    public Bulldozer(float x, float y, boolean direction) throws SlickException {
        super("assets/bulldozer.png", x, y, 0.05f, direction);
    }

    public void pushPlayer(Player player, int delta){
        player.setxPos(player.getxPos() + getSign() * getSpeed() * delta);
        if(player.getxPos() - player.getSprite().getWidth()/2 > App.SCREEN_WIDTH ||
                player.getxPos() + player.getSprite().getWidth()/2 < 0){
            player.decreaseLife();
        }
    }
}
