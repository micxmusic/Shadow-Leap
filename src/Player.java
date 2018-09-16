import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


public class Player extends Sprite{

    private int lives = 3;
    private boolean isRiding = false;
    private BoundingBox[] movableDirections = new BoundingBox[4];

    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    public void update(Input input, boolean[] validMove) {
        
        if(input.isKeyPressed(Input.KEY_DOWN)){
            if(getyPos() + TILE_SIZE <= App.SCREEN_HEIGHT - TILE_SIZE/2 && validMove[3]) {
                setyPos(getyPos() + TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_UP)){
            if(getyPos() - TILE_SIZE >= TILE_SIZE/2  && validMove[2]){
                setyPos(getyPos() - TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_LEFT)){
            if(getxPos() - TILE_SIZE >= 0 && validMove[0]){
                setxPos(getxPos() - TILE_SIZE);
            }
        }

        if(input.isKeyPressed(Input.KEY_RIGHT)){
            if(getxPos() + TILE_SIZE <= App.SCREEN_WIDTH && validMove[1]) {
                setxPos(getxPos() + TILE_SIZE);
            }
        }

        setRiding(false);
        getBox().setX(getxPos());
        getBox().setY(getyPos());
    }

    public boolean contactSprite(Sprite other) {
        return(getBox().intersects(other.getBox()));
    }

    public void increaseLife(){
        this.lives += 1;
    }

    public void decreaseLife(){
        if(this.lives > 0) {
            this.lives -= 1;
            this.setxPos(512);
            this.setyPos(720);
        } else {
            System.exit(0);
        }
    }

    public int willCrash(Sprite other){
        movableDirections[0] = new BoundingBox(getxPos() - TILE_SIZE, getyPos(), TILE_SIZE, TILE_SIZE);
        movableDirections[1] = new BoundingBox(getxPos() + TILE_SIZE, getyPos(), TILE_SIZE, TILE_SIZE);
        movableDirections[2] = new BoundingBox(getxPos(), getyPos() - TILE_SIZE, TILE_SIZE, TILE_SIZE);
        movableDirections[3] = new BoundingBox(getxPos(), getyPos() + TILE_SIZE, TILE_SIZE, TILE_SIZE);
        for(int i = 0; i < 4; i++){
            if(movableDirections[i].intersects(other.getBox())){
                return i;
            }
        }
        return -1;
    }

    public int getLives() {
        return lives;
    }

    public boolean getRiding() {
        return isRiding;
    }

    public void setRiding(Boolean ride) {
        this.isRiding = ride;
    }

}
