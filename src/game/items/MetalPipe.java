package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellingAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actions.AttackAction;
import game.actors.HumanoidFigure;


/**
 * Represents a Metal Pipe, a weapon item in the game.
 */

public class MetalPipe extends WeaponItem implements Sellable {

    /**
     * Constructs a Metal Pipe weapon item.
     * Initialises the weapon item with the name "Metal Pipe", display character '!', attack damage 1,
     * attack verb "whacks", and an accuracy of 20%.
     */
    public MetalPipe() {
        super("Metal Pipe", '!', 1, "whacks", 20);
    }


    /**
     * Sells the Metal Pipe.
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer.
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        seller.addBalance(35);
        seller.removeItemFromInventory(this);
        return seller + " sold " + this + " to " + buyer + " for 35 credits.";
    }

    /**
     * Returns a list of Actions that this weapon item allows its owner to do to another Actor.
     *
     * @param otherActor The other Actor.
     * @param location   The location of the other Actor on the game map.
     * @return An ActionList can can contain an Attack Action on the other Actor and a Selling Action on this Metal Pipe and the other Actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        if (otherActor.hasCapability(Ability.BUY_SCRAPS)) {
            actions.add(new SellingAction(this, otherActor));
        }
        return actions;
    }


}
