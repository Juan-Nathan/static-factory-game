package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SellingAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actors.HumanoidFigure;
import edu.monash.fit2099.engine.actors.Actor;


/**
 * Represents a Large Bolt, an item in the game.
 */

public class LargeBolt extends Item implements Sellable{

    /**
     * Constructs a Large Bolt item.
     * Initialises the item with the name "Large Bolt", display character '+', and sets it to be portable.
     */
    public LargeBolt() {
        super("Large Bolt", '+', true);
    }


    /**
     * Sell the large bolt.
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer.
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        seller.addBalance(25);
        seller.removeItemFromInventory(this);
        return seller + " sold " + this + " to " + buyer + " for 25 credits.";
    }

    /**
     * Returns a list of Actions that this item allows the Actor to do to another Actor.
     *
     * @param otherActor The other Actor.
     * @param location   The location of the other Actor.
     * @return An ActionList that can contain a Selling Action on this Large Bolt and the other Actor.
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
