package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellingAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actors.HumanoidFigure;


/**
 * Represents a Large Fruit, a consumable item in the game.
 */

public class LargeFruit extends Item implements Consumable, Sellable {


    /**
     * Constructs a Large Fruit item.
     * Initialises the item with the name "Large Fruit", display character 'O', and sets it to be portable.
     */
    public LargeFruit() {
        super("Large Fruit", 'O', true);
    }

    /**
     * Consumes the Large Fruit, healing the Actor for 2 hit points.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = actor + " consumes the " + this + ", healing for 2 hit points";
        actor.heal(2);
        actor.removeItemFromInventory(this);
        return result;
    }

    /**
     * Sells the large fruit.
     *  @param buyer The HumanoidFigure buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer.
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        seller.addBalance(30);
        seller.removeItemFromInventory(this);
        return seller + " sold " + this + " to " + buyer + " for 30 credits.";
    }

    /**
     * Returns a list of Actions that can be performed on the Large Fruit.
     *
     * @param owner The Actor that owns the Large Fruit.
     * @return An ActionList containing a Consume Action on this Large Fruit.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Returns a list of Actions that this item allows the Actor to do to another Actor.
     *
     * @param otherActor The other Actor.
     * @param location   The location of the other Actor.
     * @return An ActionList that can contain a Selling Action on this Large Fruit and the other Actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Ability.BUY_SCRAPS)) {
            actions.add(new SellingAction(this, otherActor));
        }
        return actions;
    }
}

