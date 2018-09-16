import org.newdawn.slick.SlickException;

public class Bulldozer extends Vehicle {

    public Bulldozer(float x, float y, boolean direction) throws SlickException {
        super("assets/bulldozer.png", x, y, 0.05f, direction);
    }

    public void pushPlayer(Player player){
        player.setxPos(player.getxPos() + this.getSpeed());
    }
}
