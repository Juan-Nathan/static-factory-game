package game.plants;

import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Flora;
import game.items.LargeFruit;

/**
 * Represents a Mature Tree.
 * Mature Tree is a type of Plant that can produce Large Fruits.
 */

public class MatureTree extends Plant {

    /**
     * Constructs a Mature Tree.
     * The Mature Tree is initialised with a display character 'T', representing its appearance on the game map.
     */
    public MatureTree() {
        super('T');
    }

    /**
     * Grows the Mature Tree and attempts to drop a Large Fruit in a random adjacent location
     * with a drop chance of 20%.
     *
     * @param location The location of the Mature Tree on the game map.
     * @param flora    The Flora object that contains the Mature Tree.
     */
    @Override
    public void grow(Location location, Flora flora) {
        this.age++;
        if (this.rand.nextInt(100) < 20) {
            this.dropFruit(location, new LargeFruit());
        }
    }
}
