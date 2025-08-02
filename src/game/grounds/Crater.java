package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawners.Spawner;

/**
 * Represents a Crater.
 * Crater is a unique Ground type that can spawn creatures.
 */

public class Crater extends Ground {

    /**
     * The type of Spawner to be used for generating creatures in the Crater.
     */
    private Spawner spawner;

    /**
     * Constructs a Crater.
     * The Crater is initialised with a display character 'u', representing its appearance on the game map.
     *
     * @param spawner The type of Spawner to be used for generating creatures in the Crater.
     */
    public Crater(Spawner spawner) {
        super('u');
        this.spawner = spawner;
    }

    /**
     * Spawns a creature in a random adjacent location on each game tick if the conditions are met.
     *
     * @param location The location of the Crater on the game map.
     */
    @Override
    public void tick(Location location) {
        this.spawner.spawn(location);
    }
}