package game.items;

import java.util.Random;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.InsufficientBalanceException;
import game.actions.ConsumeAction;

/**
 * Represents an Energy Drink, a consumable and purchasable item in the game.
 */

public class EnergyDrink extends Item implements Consumable, Purchasable {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs an Energy Drink item.
     * Initialises the item with the name "Energy Drink", display character '*', and sets it to be portable.
     */
    public EnergyDrink() {
        super("Energy Drink", '*', true);
    }

    /**
     * Purchases the Energy Drink.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = actor + " chugs down the " + this + " and gets re-energised, healing for 1 hit point";
        actor.heal(1);
        actor.removeItemFromInventory(this);
        return result;
    }

    /**
     * Defines what happens when the Energy Drink is purchased by an Actor.
     *
     * @param actor The Actor purchasing the item.
     * @return A String describing the result of the purchase.
     * @throws InsufficientBalanceException When the Actor does not have enough credits to purchase the item.
     */
    @Override
    public String purchasedBy(Actor actor) throws InsufficientBalanceException {
        String result = "";
        int cost = this.rand.nextInt(100) < 20 ? 20 : 10;

        if (actor.getBalance() < cost) {
            throw new InsufficientBalanceException(this, actor.getBalance(), cost);
        }
        else {
            result += actor + " purchases an " + this + " for " + cost + " credits";
            actor.deductBalance(cost);
            actor.addItemToInventory(this);
        }
        return result;
    }

    /**
     * Returns a list of Actions that can be performed on the Energy Drink.
     *
     * @param owner The Actor that owns the Energy Drink.
     * @return An ActionList containing a Consume Action on this Energy Drink.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAction(this));
        return actions;
    }
}
