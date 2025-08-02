package game.spawners;

import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for implementing Spawners in the game.
 */

public interface Spawner {

    /**
     * Spawns entities based on the conditions defined in the implementing class.
     *
     * @param location The location on the game map where entities should be spawned (e.g. the Crater's location).
     */
    void spawn(Location location);
}
