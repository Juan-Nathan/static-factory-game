package game.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Flora;

/**
 * Represents a Young Tree.
 * Young Tree is a type of Plant that can grow into a Mature Tree.
 */

public class YoungTree extends Plant {

    /**
     * Constructs a Young Tree.
     * The Young Tree is initialised with a display character 'y', representing its appearance on the game map.
     */
    public YoungTree() {
        super('y');
    }

    /**
     * Grows the Young Tree.
     *
     * @param location The location of the Plant on the game map.
     * @param flora    The Flora object that contains the Plant.
     */
    @Override
    public void grow(Location location, Flora flora) {
        this.age++;
        // If the Young Tree reaches the age of 5, it grows into a Mature Tree
        if (this.age == 5) {
            flora.setPlant(new MatureTree());
        }
    }
}
