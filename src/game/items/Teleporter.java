package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for implementing Teleporters in the game.
 */

public interface Teleporter {

    /**
     * Teleports the Actor to a different location on the game map.
     *
     * @param actor The Actor to be teleported.
     * @param map   The current Game Map.
     * @return A String describing the result of teleporting the Actor.
     */
    String teleport(Actor actor, GameMap map);
}

