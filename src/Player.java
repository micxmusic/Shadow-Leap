import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


/**
 * The type Player.
 */
public class Player extends Sprite {

    //initial player positions
    private final static int PLAYER_INITIAL_X = 512;
    private final static int PLAYER_INITIAL_Y = 720;

    //other attributes of the player
    private int lives = 3;
    private DirectionBoxes movableDirections;
    private boolean[] validMove = {true, true, true, true};
    private boolean isRiding = false;
    private boolean isAlive = true;

    /**
     * Instantiates a new Player.
     *
     * @throws SlickException the slick exception
     */
    public Player() throws SlickException {
        super("assets/frog.png", PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
        movableDirections = new DirectionBoxes(this, 4);
    }

    /**
     * Update.
     *
     * @param input keyboard input
     */
    public void update(Input input) {

        //updating player position only when movement is valid
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (getyPos() + World.TILE_SIZE <= App.SCREEN_HEIGHT - World.TILE_SIZE / 2 && downValid()) {
                setyPos(getyPos() + World.TILE_SIZE);
            }
        }

        if (input.isKeyPressed(Input.KEY_UP)) {
            if (getyPos() - World.TILE_SIZE >= World.TILE_SIZE / 2 && upValid()) {
                setyPos(getyPos() - World.TILE_SIZE);
            }
        }

        if (input.isKeyPressed(Input.KEY_LEFT)) {
            if (getxPos() - World.TILE_SIZE >= 0 && leftValid()) {
                setxPos(getxPos() - World.TILE_SIZE);
            }
        }

        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            if (getxPos() + World.TILE_SIZE <= App.SCREEN_WIDTH && rightValid()) {
                setxPos(getxPos() + World.TILE_SIZE);
            }
        }

        setRiding(false);
        updateBox();
    }

    /**
     * Contact sprite boolean.
     *
     * @param other other sprite to check
     * @return boolean of whether sprite contacts other
     */
    public boolean contactSprite(Sprite other) {
        return (getBox().intersects(other.getBox()));
    }

    /**
     * Increase life.
     */
    public void increaseLife() {
        this.lives += 1;
    }

    /**
     * Decrease life and sets isAlive boolean to false if player has died.
     */
    public void decreaseLife() {
        if (this.lives > 0) {
            resetPosition();
            this.lives -= 1;
        } else {
            isAlive = false;
        }
    }

    /**
     * Add extra life if player contacts extra life object.
     *
     * @param extralife the extralife
     * @return the boolean
     */
    public boolean addExtraLife(ExtraLife extralife) {
        if (contactSprite(extralife)) {
            increaseLife();
            return true;
        }
        return false;
    }

    /**
     * Reset position of player.
     */
    public void resetPosition() {
        this.setxPos(PLAYER_INITIAL_X);
        this.setyPos(PLAYER_INITIAL_Y);
        updateBox();
    }

    /**
     * Update position of player bounding box.
     */
    public void updateBox() {
        getBox().setX(getxPos());
        getBox().setY(getyPos());
    }

    /**
     * Check direction boxes to see if player will hit another sprite.
     *
     * @param other the other sprite
     * @return the index of the box that intersects or sentinal if no intersection
     */
    public int willCrash(Sprite other) {
        movableDirections.initialise();
        for (int i = 0; i < 4; i++) {
            if (movableDirections.getBox(i).intersects(other.getBox())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Set the direction to false.
     *
     * @param i the direction to set to false
     */
    public void setFalse(int i) {
        if (i < movableDirections.length()) {
            validMove[i] = false;
        }
    }

    /**
     * Reset the validity of moves to be all true.
     */
    public void resetMoves() {
        for (int i = 0; i < validMove.length; i++) {
            validMove[i] = true;
        }
    }

    /**
     * Checks if moving left is valid.
     *
     * @return the validity
     */
    private boolean leftValid() {
        return validMove[0];
    }

    /**
     * Checks if moving right is valid.
     *
     * @return the validity
     */
    private boolean rightValid() {
        return validMove[1];
    }

    /**
     * Checks if moving up is valid.
     *
     * @return the validity
     */
    private boolean upValid() {
        return validMove[2];
    }

    /**
     * Checks if moving down is valid.
     *
     * @return the validity
     */
    private boolean downValid() {
        return validMove[3];
    }

    /**
     * Gets the remaining lives of player.
     *
     * @return the lives
     */
    public int getLives() {
        return lives;
    }

    /**
     * Is riding boolean to indicate if player is on a rideable.
     *
     * @return boolean indicator of whether Player is riding
     */
    public boolean isRiding() {
        return isRiding;
    }

    /**
     * Sets riding boolean to true or false.
     *
     * @param ride the boolean value to set it to
     */
    public void setRiding(Boolean ride) {
        this.isRiding = ride;
    }

    /**
     * Is alive boolean.
     *
     * @return the boolean indicating if player is alive
     */
    public boolean isAlive() {
        return isAlive;
    }
}
