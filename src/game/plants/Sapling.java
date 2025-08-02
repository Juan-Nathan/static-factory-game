package game.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Flora;
import game.items.SmallFruit;

/**
 * Represents a Sapling.
 * Sapling is a type of Plant that can grow into a Young Tree and produce Small Fruits.
 */

public class Sapling extends Plant {

    /**
     * Constructs a Sapling.
     * The Sapling is initialised with a display character 't', representing its appearance on the game map.
     */
    public Sapling() {
        super('t');
    }

    /**
     * Grows the Sapling and attempts to drop a Small Fruit in a random adjacent location
     * with a drop chance of 30%.
     *
     * @param location The location of the Sapling on the game map.
     * @param flora    The Flora object that contains the Sapling.
     */
    @Override
    public void grow(Location location, Flora flora) {
        this.age++;
        // If the Sapling reaches the age of 6, it grows into a Young Tree
        if (this.age == 6) {
            flora.setPlant(new YoungTree());
        } else
        if (this.rand.nextInt(100) < 30) {
            this.dropFruit(location, new SmallFruit());
        }
    }
}
