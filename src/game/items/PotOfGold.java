package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellingAction;
import game.capabilities.Ability;

import java.util.Random;

/**
 * Represents a Pot of Gold, a consumable item in the game.
 */

public class PotOfGold extends Item implements Consumable, Sellable {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Pot of Gold item.
     * Initialises the item with the name "Pot of Gold", display character '$', and sets it to be portable.
     */
    public PotOfGold() {
        super("Pot of Gold", '$', true);
    }

    /**
     * Consumes the Pot of Gold, granting the Actor 10 credits.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = actor + " clears out the " + this + ", gaining 10 credits";
        actor.addBalance(10);
        actor.removeItemFromInventory(this);
        return result;
    }

    /**
     * Sells the Pot of Gold.
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer.
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        if (this.rand.nextInt(100) < 25) {
            seller.removeItemFromInventory(this);
            return seller + " sold " + this + " to " + buyer + " without getting paid.";
        }
        else {
            seller.addBalance(500);
            seller.removeItemFromInventory(this);
            return seller + " sold " + this + " to " + buyer + " for 500 credits.";
        }
    }

    /**
     * Returns a list of Actions that can be performed on the Pot of Gold.
     *
     * @param owner The Actor that owns the Pot of Gold.
     * @return An ActionList containing a Consume Action on this Pot of Gold.
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
     * @return An ActionList that can contain a Selling Action on this Pot of Gold and the other Actor.
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
