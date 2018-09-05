import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Player extends Sprite{

    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    public void update(Input input) {

        if(input.isKeyPressed(Input.KEY_DOWN)){
            if(getyPos() + TILE_SIZE <= App.SCREEN_HEIGHT - TILE_SIZE/2) {
                setyPos(getyPos() + TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_UP)){
            if(getyPos() - TILE_SIZE >= TILE_SIZE/2){
                setyPos(getyPos() - TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_LEFT)){
            if(getxPos() - TILE_SIZE >= TILE_SIZE/2){
                setxPos(getxPos() - TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_RIGHT)){
            if(getxPos() + TILE_SIZE <= App.SCREEN_WIDTH - TILE_SIZE/2) {
                setxPos(getxPos() + TILE_SIZE);
            }
        }

        getBox().setX(getxPos());
        getBox().setY(getyPos());
    }

    public boolean contactSprite(Sprite other) {
        return(getBox().intersects(other.getBox()));
    }
}
