package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * Represents a Small Fruit, a consumable item in the game.
 */

public class SmallFruit extends Item implements Consumable {

    /**
     * Constructs a Small Fruit item.
     * Initialises the item with the name "Small Fruit", display character 'o', and sets it to be portable.
     */
    public SmallFruit() {
        super("Small Fruit", 'o', true);
    }

    /**
     * Consumes the Small Fruit, healing the Actor for 1 hit point.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = actor + " consumes the " + this + ", healing for 1 hit point";
        actor.heal(1);
        actor.removeItemFromInventory(this);
        return result;
    }

    /**
     * Returns a list of Actions that can be performed on the Small Fruit.
     *
     * @param owner The Actor that owns the Small Fruit.
     * @return An ActionList containing a Consume Action on this Small Fruit.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
