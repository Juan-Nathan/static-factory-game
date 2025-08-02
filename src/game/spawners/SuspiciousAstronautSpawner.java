package game.spawners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.SuspiciousAstronaut;

/**
 * A Spawner class responsible for spawning Suspicious Astronauts.
 */

public class SuspiciousAstronautSpawner implements Spawner{

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Attempts to spawn a Suspicious Astronaut in a random adjacent location with a spawn chance of 5%.
     *
     * @param location The location on the game map where the Suspicious Astronaut should be spawned (e.g. the Crater's location).
     */
    @Override
    public void spawn(Location location) {
        SuspiciousAstronaut suspiciousAstronaut = new SuspiciousAstronaut();
        List<Location> validLocations = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.canActorEnter(suspiciousAstronaut)) {
                validLocations.add(adjacent);
            }
        }

        if (!validLocations.isEmpty() && this.rand.nextInt(100) < 5) {
            Location spawnLocation = validLocations.get(this.rand.nextInt(validLocations.size()));
            spawnLocation.addActor(suspiciousAstronaut);
        }
    }
}



