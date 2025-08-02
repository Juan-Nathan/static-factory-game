package game.spawners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.AlienBug;

/**
 * A Spawner class responsible for spawning Alien Bugs.
 */

public class AlienBugSpawner implements Spawner {

    /**
     * The Actor to follow.
     */
    private final Actor target;

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs an Alien Bug Spawner.
     *
     * @param target The Actor to follow.
     */
    public AlienBugSpawner(Actor target) {
        this.target = target;
    }

    /**
     * Attempts to spawn an Alien Bug in a random adjacent location with a spawn chance of 10%.
     *
     * @param location The location on the game map where the Alien Bug should be spawned (e.g. the Crater's location).
     */
    @Override
    public void spawn(Location location) {
        AlienBug alienBug = AlienBug.createFeatureAlienBug(this.target, this.rand);
        List<Location> validLocations = new ArrayList<>();

        for (Exit exit : location.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.canActorEnter(alienBug)) {
                validLocations.add(adjacent);
            }
        }

        if (!validLocations.isEmpty() && this.rand.nextInt(100) < 10) {
            Location spawnLocation = validLocations.get(this.rand.nextInt(validLocations.size()));
            spawnLocation.addActor(alienBug);
        }
    }
}
