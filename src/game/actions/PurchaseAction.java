package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Purchasable;

/**
 * An Action that enables an Actor to purchase a purchasable item.
 */

public class PurchaseAction extends Action {

    /**
     * The purchasable item to be purchased.
     */
    private Purchasable item;

    /**
     * Constructs a Purchase Action for the specified purchasable item.
     *
     * @param item The item to be purchased.
     */
    public PurchaseAction(Purchasable item) {
        this.item = item;
    }

    /**
     * Executes the purchase of the item by the specified Actor.
     *
     * @param actor The Actor performing the Action.
     * @param map   The map the Actor is on.
     * @return A String describing the Actor's purchase of the item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        try {
            return this.item.purchasedBy(actor);
        } catch (InsufficientBalanceException e) {
            return e.getMessage();
        }
    }

    /**
     * Generates a description of the Purchase Action to be displayed in the menu.
     *
     * @param actor The Actor performing the Action.
     * @return A String representing the menu description of the Purchase Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + this.item;
    }
}
