import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


/**
 * The type Player.
 */
public class Player extends Sprite{

    private final static int PLAYER_INITIAL_X = 512;
    private final static int PLAYER_INITIAL_Y = 720;

    private int lives = 3;
    private boolean isRiding = false;
    private BoundingBox[] movableDirections = new BoundingBox[4];
    private Image alternate1 = new Image("assets/jesterfrog.png");
    private Image temp = getSprite();

    /**
     * Instantiates a new Player.
     *
     * @throws SlickException the slick exception
     */
    public Player() throws SlickException {
        super("assets/frog.png", PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
    }

    /**
     * Update.
     *
     * @param input     the input
     * @param validMove the valid move
     */
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

        if(input.isKeyDown(Input.KEY_D)){
            setSprite(alternate1);
        }

        if(input.isKeyDown(Input.KEY_A)){
            setSprite(temp);
        }

        setRiding(false);
        updateBox();
    }

    /**
     * Contact sprite boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean contactSprite(Sprite other) {
        return(getBox().intersects(other.getBox()));
    }

    /**
     * Increase life.
     */
    public void increaseLife(){
        this.lives += 1;
    }

    /**
     * Decrease life.
     */
    public void decreaseLife(){
        if(this.lives > 0) {
            resetPosition();
            this.lives -= 1;
        } else {
            System.exit(0);
        }
    }

    /**
     * Add extra life boolean.
     *
     * @param extralife the extralife
     * @return the boolean
     */
    public boolean addExtraLife(ExtraLife extralife){
        if(contactSprite(extralife)){
            increaseLife();
            return true;
        }
        return false;
    }

    /**
     * Reset position.
     */
    public void resetPosition(){
        this.setxPos(PLAYER_INITIAL_X);
        this.setyPos(PLAYER_INITIAL_Y);
        updateBox();
    }

    /**
     * Update box.
     */
    public void updateBox(){
        getBox().setX(getxPos());
        getBox().setY(getyPos());
    }

    /**
     * Will crash int.
     *
     * @param other the other
     * @return the int
     */
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

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Is riding boolean.
     *
     * @return boolean indicator of whether Player is riding a log
     */
    public boolean isRiding() {
        return isRiding;
    }

    /**
     * Sets riding.
     *
     * @param ride the ride
     */
    public void setRiding(Boolean ride) {
        this.isRiding = ride;
    }

}
