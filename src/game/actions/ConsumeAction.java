package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * An Action that enables an Actor to consume a consumable item.
 * When performed, this Action consumes the item, applying its effects to the Actor.
 */

public class ConsumeAction extends Action {

    /**
     * The consumable item to be consumed.
     */
    private Consumable item;

    /**
     * Constructs a Consume Action for the specified consumable item.
     *
     * @param item The item to be consumed.
     */
    public ConsumeAction(Consumable item) {
        this.item = item;
    }

    /**
     * Executes the consumption of the item by the specified Actor.
     * The specific effects of consumption are defined by the consumable item's 'consumedBy()' method.
     *
     * @param actor The Actor performing the action.
     * @param map   The map that the Actor is on.
     * @return A String describing the actor's consumption of the item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.item.consumedBy(actor);
    }

    /**
     * Generates a description of the Consume Action to be displayed in the menu.
     * The description includes the performing Actor and the item to be consumed.
     *
     * @param actor The Actor performing the action.
     * @return A String representing the menu description of the Consume Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.item;
    }
}
