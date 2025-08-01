package game.items;

import java.util.Random;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellingAction;
import game.capabilities.Ability;

/**
 * Represents a Jar of Pickles, a consumable item in the game.
 */

public class JarOfPickles extends Item implements Consumable, Sellable {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Jar of Pickles item.
     * Initialises the item with the name "Jar of Pickles", display character 'n', and sets it to be portable.
     */
    public JarOfPickles() {
        super("Jar of Pickles", 'n', true);
    }

    /**
     * Consumes the Jar of Pickles, healing or hurting the Actor for 1 hit point.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = "";
        if (this.rand.nextInt(100) < 50) {
            result += actor + " bites into an expired " + this + " and gets poisoned, losing 1 hit point";
            actor.hurt(1);
        }
        else {
            result += actor + " enjoys a fresh " + this + " and gets refueled, healing for 1 hit point";
            actor.heal(1);
        }
        actor.removeItemFromInventory(this);
        return result;
    }

    /**
     * Sells the Jar of Pickles.
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        int payment = this.rand.nextInt(100) < 50 ? 50 : 25;
        seller.addBalance(payment);
        seller.removeItemFromInventory(this);
        return seller + " sold " + this + " to " + buyer + " for " + payment + " credits.";
    }

    /**
     * Returns a list of Actions that can be performed on the Jar of Pickles.
     *
     * @param owner The Actor that owns the Jar of Pickles.
     * @return An ActionList containing a Consume Action on this Jar of Pickles.
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
     * @return An ActionList that can contain a Selling Action on this Jar of Pickles and the other Actor.
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
