/**
 * Sample Project for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 * <p>
 * Game implemented by Michelle Anggana, 931735
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Color;

import java.awt.Font;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 */
public class App extends BasicGame {
    /**
     * screen width, in pixels
     */
    public static final int SCREEN_WIDTH = 1024;

    /**
     * screen height, in pixels
     */
    public static final int SCREEN_HEIGHT = 768;

    private World world;
    private TrueTypeFont trueType;
    private int respect = 0;
    private String[] gameComplete = {"Congratulations!", "Press Enter to restart"};
    private String[] gameOver = {"Game Over", "Press F to pay respects",
            "Respects paid = " + Integer.toString(respect), "Press Enter to restart"};


    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        world = new World();
        Font font = new Font("Consolas", Font.BOLD, 32);
        trueType = new TrueTypeFont(font, true);

    }

    /**
     * Update the game state for a frame.
     *
     * @param gc    The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();

        // Check if the world needs to be updated or a screen is to be rendered
        if (!world.isGameComplete() && !world.isGameOver()) {
            world.update(input, delta);
        } else if (world.isGameOver() || world.isGameComplete()) {
            if (world.isGameOver()) {
                if (input.isKeyPressed(Input.KEY_F)) {
                    gameOver[2] = "Respects paid = " + Integer.toString(++respect);
                }
            }
            if (input.isKeyPressed(Input.KEY_ENTER)) {
                world = new World();
            }
        }
    }

    /**
     * Render the entire screen, so it reflects the current game state.
     *
     * @param gc The Slick game container object.
     * @param g  The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {

        //renders according to the game state
        if (!world.isGameComplete() && !world.isGameOver()) {
            world.render(g);
        } else if (world.isGameComplete()) {
            for (int i = 0; i < gameComplete.length; i++) {
                trueType.drawString(SCREEN_WIDTH / 2 - trueType.getWidth(gameComplete[i]) / 2,
                        SCREEN_HEIGHT / 2 - (gameComplete.length / 2 - i) * World.TILE_SIZE,
                        gameComplete[i], Color.white);
            }
        } else if (world.isGameOver()) {
            for (int i = 0; i < gameOver.length; i++) {
                trueType.drawString(SCREEN_WIDTH / 2 - trueType.getWidth(gameOver[i]) / 2,
                        SCREEN_HEIGHT / 2 - (gameOver.length / 2 - i) * World.TILE_SIZE,
                        gameOver[i], Color.white);
            }
        } else {
            gc.exit();
        }

    }

    /**
     * Start-up method. Creates the game and runs it.
     *
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(false);
        app.setTargetFrameRate(60);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

}