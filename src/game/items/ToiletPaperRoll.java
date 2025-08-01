package game.items;

import java.util.Random;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.FancyMessage;
import game.actions.InsufficientBalanceException;
import game.actions.SellingAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.actors.HumanoidFigure;
import edu.monash.fit2099.engine.displays.Display;

/**
 * Represents a Toilet Paper Roll, a purchasable item in the game.
 */

public class ToiletPaperRoll extends Item implements Purchasable, Sellable {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Toilet Paper Roll item.
     * Initialises the item with the name "Toilet Paper Roll", display character 's', and sets it to be portable.
     */
    public ToiletPaperRoll() {
        super("Toilet Paper Roll", 's', true);
    }

    /**
     * Purchases the Toilet Paper Roll.
     *
     * @param actor The Actor purchasing the item.
     * @return A String describing the result of the purchase.
     * @throws InsufficientBalanceException When the Actor does not have enough credits to purchase the item.
     */
    @Override
    public String purchasedBy(Actor actor) throws InsufficientBalanceException {
        String result = "";
        int cost = this.rand.nextInt(100) < 75 ? 1 : 5;

        if (actor.getBalance() < cost) {
            throw new InsufficientBalanceException(this, actor.getBalance(), cost);
        }
        else {
            result += actor + " purchases a " + this + " for " + cost + " credit(s)";
            actor.deductBalance(cost);
            actor.addItemToInventory(this);
        }
        return result;
    }

    /**
     * Sells the toilet paper roll.
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A statement which displays the item being sold to the buyer.
     */
    @Override
    public String soldTo(Actor buyer, Actor seller, GameMap map) {
        if (rand.nextInt(100) < 50) {
            return seller.unconscious(buyer, map);
        } else {
            seller.addBalance(1);
            seller.removeItemFromInventory(this);
            return seller + " sold " + this + " to " + buyer + " for 1 credit.";
        }
    }

    /**
     * Returns a list of Actions that this item allows the Actor to do to another Actor.
     *
     * @param otherActor The other Actor.
     * @param location   The location of the other Actor.
     * @return An ActionList that can contain a Selling Action on this Toilet Paper Roll and the other Actor.
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
