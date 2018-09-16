import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.io.FileNotFoundException;

public class World {

    private Player frog;
    private Level level;
    private int numLevel = 1;
    private Image lives = new Image("assets/lives.png");

	public World() throws SlickException {
		// Perform initialisation logic
        frog = new Player ("assets/frog.png", 512, 720);
        try {
            level = new Level("assets/levels/" + numLevel + ".lvl");
        }
        catch (FileNotFoundException e){
            System.exit(0);
        }

	}

	public void update(Input input, int delta) {
	    // Update all of the sprites in the game
		for(Vehicle vehicle: level.getVehicles()){
		    vehicle.update(delta);
		    if(frog.contactSprite(vehicle)){
                if(vehicle instanceof Bulldozer){

                } else {
                    frog.decreaseLife();
                }
            }
        }

        for(Rideable rideable: level.getRideables()){
		    rideable.update(delta);
		    if(rideable.getBox() != null) {
                if (frog.contactSprite(rideable)) {
                    frog.setRiding(true);
                    rideable.movePlayer(frog, delta);
                }
            }
        }

        for(Tile tile: level.getTiles()){
		    if(frog.contactSprite(tile) && tile.isDangerous && !frog.getRiding()){
		        frog.decreaseLife();
		        break;
            }
        }

        frog.update(input);

	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game
        for(Tile tile: level.getTiles()){
            tile.render();
        }

        for(Vehicle vehicle: level.getVehicles()){
            vehicle.render();
        }

        for(Rideable rideable: level.getRideables()){
            rideable.render();
        }

        for(int i = 0; i < frog.getLives(); i++){
            renderLives(24 + i * 32);
        }

        frog.render();
	}

	public void renderLives(float xPos){
	    float yPos = 744;
	    lives.drawCentered(xPos, yPos);
    }
}
