import org.newdawn.slick.SlickException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Level {

    private int previousTreeXPos = -1;
    private int goalsFilled;
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Rideable> logs = new ArrayList<>();
    private ArrayList<Rideable> rideables = new ArrayList<>();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Tile> goals = new ArrayList<>();


    public Level(String csvSource) throws FileNotFoundException, SlickException {
        Scanner scanner = new Scanner(new FileReader(csvSource));
        goalsFilled = 0;
        while(scanner.hasNext()) {
            String elements[] = scanner.nextLine().split(",");
            int xPos = Integer.parseInt(elements[1]);
            int yPos = Integer.parseInt(elements[2]);
            if(elements.length == 4){
                boolean direction = Boolean.parseBoolean(elements[3]);
                switch(elements[0]){
                    case "bus":
                        vehicles.add(new Bus(xPos, yPos, direction));
                        break;
                    case "racecar":
                        vehicles.add(new Racecar(xPos, yPos, direction));
                        break;
                    case "bike":
                        vehicles.add(new Bike(xPos, yPos, direction));
                        break;
                    case "bulldozer":
                        vehicles.add(new Bulldozer(xPos, yPos, direction));
                        break;
                    case "log":
                        Rideable newLog = new Log(xPos, yPos, direction);
                        logs.add(newLog);
                        rideables.add(newLog);
                        break;
                    case "longLog":
                        Rideable newLongLog = new Longlog(xPos, yPos, direction);
                        logs.add(newLongLog);
                        rideables.add(newLongLog);
                        break;
                    case "turtle":
                        rideables.add(new Turtles(xPos, yPos, direction));
                        break;
                    default:
                        System.exit(1);
                }

            } else {
                switch(elements[0]){
                    case "water":
                        tiles.add(new Tile("assets/water.png", xPos, yPos, true, false));
                        break;
                    case "grass":
                        tiles.add(new Tile("assets/grass.png", xPos, yPos, false, false));
                        break;
                    case "tree":
                        tiles.add(new Tile("assets/tree.png", xPos, yPos, false, true));
                        if(previousTreeXPos == -1){
                            previousTreeXPos = xPos;
                        } else {
                            if(xPos - previousTreeXPos > Sprite.TILE_SIZE){
                                Tile goal = new Tile("assets/frog.png",
                                        (xPos + previousTreeXPos)/2, yPos, false, false);
                                goal.getSprite().setAlpha(0);
                                goals.add(goal);
                            }
                            previousTreeXPos = xPos;
                        }
                        break;
                    default:
                        System.exit(1);

                }
            }
        }

    }

    public boolean Completed(){
        return (goalsFilled == goals.size());
    }

    public void increaseGoalsFilled(){
        goalsFilled += 1;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    public ArrayList<Rideable> getLogs() {
        return logs;
    }

    public ArrayList<Rideable> getRideables() {return rideables; }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Tile> getGoals() {
        return goals;
    }
}
