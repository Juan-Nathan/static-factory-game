package game.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.grounds.Flora;

/**
 * An abstract representation of a Plant in the game.
 * Plants can grow and produce Fruits.
 */

public abstract class Plant {

    /**
     * The age of the Plant.
     */
    protected int age;

    /**
     * The display character of the Plant.
     */
    private final char displayChar;

    /**
     * Random number generator.
     */
    protected final Random rand = new Random();

    /**
     * Constructs a Plant.
     *
     * @param displayChar The display character of the Plant.
     */
    public Plant(char displayChar) {
        this.age = 0;
        this.displayChar = displayChar;
    }

    /**
     * Drops a Fruit item in a random adjacent location.
     *
     * @param location The location of the Plant on the game map.
     * @param fruit    The Fruit item to be dropped.
     */
    protected void dropFruit(Location location, Item fruit) {
        Player testPlayer = new Player("Test Player", 'x', 1);
        List<Location> validLocations = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            Location adjacent = exit.getDestination();
            // Checking whether the Player can enter the adjacent location to determine whether the location is valid or not
            // is inappropriate because a location becomes invalid if there is an Actor in it. Hence, we determine the location's
            // validity by checking whether the Player can enter the location's ground
            Ground groundAtAdjacent = adjacent.getGround();
            // Prevent the possibility of Fruits being dropped in Walls, etc.
            if (groundAtAdjacent.canActorEnter(testPlayer)) {
                validLocations.add(adjacent);
            }
        }

        if (!validLocations.isEmpty()) {
            Location dropLocation = validLocations.get(this.rand.nextInt(validLocations.size()));
            dropLocation.addItem(fruit);
        }
    }

    /**
     * Gets the display character of the Plant.
     *
     * @return The display character of the Plant.
     */
    public char getDisplayChar() {
        return this.displayChar;
    }

    /**
     * Grows the Plant and potentially drops its Fruit.
     *
     * @param location The location of the Plant on the game map.
     * @param flora    The Flora object that contains the Plant.
     */
    public abstract void grow(Location location, Flora flora);
}
