package game.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Flora;

/**
 * Represents a Sprout.
 * Sprout is a type of Plant that can grow into a Sapling.
 */

public class Sprout extends Plant {

    /**
     * Constructs a Sprout.
     * The Sprout is initialised with a display character ',', representing its appearance on the game map.
     */
    public Sprout() {
        super(',');
    }

    /**
     * Grows the Sprout.
     *
     * @param location The location of the Plant on the game map.
     * @param flora    The Flora object that contains the Plant.
     */
    @Override
    public void grow(Location location, Flora flora) {
        this.age++;
        // If the Sprout reaches the age of 3, it grows into a Sapling
        if (this.age == 3) {
            flora.setPlant(new Sapling());
        }
    }
}
