import utilities.BoundingBox;

/**
 * The type Direction boxes.
 */
public class DirectionBoxes {

    private BoundingBox[] boxes;
    private Sprite player;

    /**
     * Instantiates new Direction boxes.
     *
     * @param player the player
     * @param num    the number of boxes
     */
    public DirectionBoxes(Sprite player, int num) {
        boxes = new BoundingBox[num];
        this.player = player;
    }

    /**
     * Initialise the boxes to check.
     */
    public void initialise() {
        boxes[0] = new BoundingBox(player.getxPos() - World.TILE_SIZE, player.getyPos(),
                World.TILE_SIZE, World.TILE_SIZE);
        boxes[1] = new BoundingBox(player.getxPos() + World.TILE_SIZE, player.getyPos(),
                World.TILE_SIZE, World.TILE_SIZE);
        boxes[2] = new BoundingBox(player.getxPos(), player.getyPos() - World.TILE_SIZE,
                World.TILE_SIZE, World.TILE_SIZE);
        boxes[3] = new BoundingBox(player.getxPos(), player.getyPos() + World.TILE_SIZE,
                World.TILE_SIZE, World.TILE_SIZE);
    }

    /**
     * Get box bounding box.
     *
     * @param index the index
     * @return the bounding box
     */
    public BoundingBox getBox(int index) {
        return boxes[index];
    }

    /**
     * The number of boxes in the array.
     *
     * @return the length of the array
     */
    public int length() {
        return boxes.length;
    }
}
