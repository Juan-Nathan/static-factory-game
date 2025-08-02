package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actions.InsufficientBalanceException;

/**
 * Interface for items that can be purchased by Actors in the game.
 */

public interface Purchasable {

    /**
     * Defines what happens when an item is purchased by an Actor.
     *
     * @param actor The Actor purchasing the item.
     * @return A String describing the result of the purchase.
     * @throws InsufficientBalanceException When the Actor does not have enough credits to purchase the item.
     */
    String purchasedBy(Actor actor) throws InsufficientBalanceException;
}
