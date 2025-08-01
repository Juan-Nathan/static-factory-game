package game.items;

import java.util.Random;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.InsufficientBalanceException;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * Represents a Dragon Slayer Sword, a purchasable weapon item in the game.
 */

public class DragonSlayerSword extends WeaponItem implements Purchasable {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Dragon Slayer Sword weapon item.
     * Initialises the weapon item with the name "Dragon Slayer Sword", display character 'x', attack damage 50,
     * attack verb "slashes", and an accuracy of 75%.
     */
    public DragonSlayerSword() {
        super("Dragon Slayer Sword", 'x', 50, "slashes", 75);
    }

    /**
     * Purchases the Dragon Slayer Sword.
     *
     * @param actor The Actor purchasing the item.
     * @return A String describing the result of the purchase.
     * @throws InsufficientBalanceException When the Actor does not have enough credits to purchase the item.
     */
    @Override
    public String purchasedBy(Actor actor) throws InsufficientBalanceException {
        String result = "";
        if (actor.getBalance() < 100) {
            throw new InsufficientBalanceException(this, actor.getBalance(), 100);
        }
        else if (this.rand.nextInt(100) < 50) {
            result += "Error: failed to print " + this;
            actor.deductBalance(100);
        }
        else {
            result += actor + " purchases a " + this + " for 100 credits";
            actor.deductBalance(100);
            actor.addItemToInventory(this);
        }
        return result;
    }

    /**
     * Returns a list of Actions that this weapon item allows its owner to do to another Actor.
     *
     * @param otherActor The other Actor.
     * @param location The location of the other Actor.
     * @return An ActionList can can contain an Attack Action on the other Actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }
}
