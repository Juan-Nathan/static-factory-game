package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for items that can be consumed by Actors in the game.
 */

public interface Consumable {

    /**
     * Defines what happens when an item is consumed by an Actor.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of the consumption.
     */
    String consumedBy(Actor actor);
}
