import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {

    private Player frog;
    private Bus buses[][];
    private Tile grass[][];
    private Tile water[][];
    private int num_tiles;
    private float grassPosition[] = {672f, 384f};
    private float busPosition[] = {432f, 480f, 528f, 576f, 624f};
    private float separation[] = {6.5f, 5f, 12f, 5f, 6.5f};
    private float offset[] = {48f, 0f, 64f, 128f, 250f};

	public World() throws SlickException {
		// Perform initialisation logic
        frog = new Player ("assets/frog.png", 512, 720);

        buses = new Bus[5][];
        for(int i = 0; i < 5; i++){
            int numBuses = (int)((App.SCREEN_WIDTH - offset[i])/(separation[i] * Sprite.TILE_SIZE) + 1);
            buses[i] = new Bus[numBuses];
            float xPos;
            for(int j = 0; j < numBuses; j++){
                xPos = offset[i] + j* separation[i] * Sprite.TILE_SIZE;
                buses[i][j] = new Bus(xPos, busPosition[i]);
            }
        }

        num_tiles = App.SCREEN_WIDTH/Sprite.TILE_SIZE + 1;
        grass = new Tile[2][num_tiles];
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < num_tiles; j++){
                grass[i][j] = new Tile("assets/grass.png", j*Sprite.TILE_SIZE, grassPosition[i]);
            }
        }

        water = new Tile[6][num_tiles];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < num_tiles; j++){
                water[i][j] = new Tile("assets/water.png", j*Sprite.TILE_SIZE,
                                         2f * Sprite.TILE_SIZE + Sprite.TILE_SIZE * i);
            }
        }
	}

	public void update(Input input, int delta) {
	    // Update all of the sprites in the game
		frog.update(input);

		for(int i = 0; i < 5; i++){
		    for(Bus bus: buses[i]){
		        //assign -1 to move left and 1 to move right
		        int direction = (i%2 == 0)? -1 : 1;
                bus.update(direction, delta);

                if(frog.contactSprite(bus)){
		            System.exit(0);
                }
            }
        }

        for(Tile line[]: water){
            for(Tile waterTile: line){
                if(frog.contactSprite(waterTile)){
                    System.exit(0);
                }
            }
        }
	}
	
	public void render(Graphics g) {
		// Draw all of the sprites in the game

        for(Bus line[]: buses){
            for(Bus bus: line){
                bus.render();
            }
        }

        for(Tile line[]: grass){
            for(Tile grassTile: line){
                grassTile.render();
            }
        }

        for(Tile line[]: water){
            for(Tile waterTile: line){
                waterTile.render();
            }
        }

        frog.render();
	}
}
