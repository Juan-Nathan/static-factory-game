package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.plants.Plant;

/**
 * Represents a Flora.
 * Flora is a unique Ground type that can contain a Plant.
 */

public class Flora extends Ground {

    /**
     * The type of Plant contained in the Flora.
     */
    private Plant plant;

    /**
     * Constructs a Flora.
     * The Flora is initialised with a display character of the Plant it contains, representing its appearance on the game map.
     *
     * @param plant The type of Plant to be contained in the Flora.
     */
    public Flora(Plant plant) {
        super(plant.getDisplayChar());
        this.plant = plant;
    }

    /**
     * Sets the Plant contained in the Flora.
     *
     * @param plant The type of Plant to be contained in the Flora.
     */
    public void setPlant(Plant plant) {
        this.setDisplayChar(plant.getDisplayChar());
        this.plant = plant;
    }

    /**
     * Grows the Plant drops its Fruit in a random adjacent location on each game tick
     * if the conditions are met.
     *
     * @param location The location of the Flora on the game map.
     */
    @Override
    public void tick(Location location) {
        this.plant.grow(location, this);
    }
}
