package game.items;

import java.util.Random;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.InsufficientBalanceException;
import game.actions.TeleportAction;

/**
 * Represents a Theseus, a purchasable item and a teleporter in the game.
 */

public class Theseus extends Item implements Purchasable, Teleporter {

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs a Theseus item.
     * Initialises the item with the name "THESEUS", display character '^', and sets it to be portable.
     */
    public Theseus() {
        super("THESEUS", '^', true);
    }

    /**
     * Purchases the Theseus.
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
        } else {
            result += actor + " purchases " + this + " for 100 credits";
            actor.deductBalance(100);
            actor.addItemToInventory(this);
        }
        return result;
    }

    /**
     * Teleports the Actor to a random location on the game map.
     *
     * @param actor The Actor to be teleported.
     * @param map   The current game map.
     * @return A String describing the result of teleporting the Actor.
     */
    @Override
    public String teleport(Actor actor, GameMap map) {
        String result = "";
        Location destination = this.getRandomLocation(map);
        if (destination.containsAnActor()) {
            result += "Teleport fails";
        }
        else {
            map.removeActor(actor);
            destination.addActor(actor);
            result += actor + " teleports to " + destination;
        }
        return result;
    }

    /**
     * Returns a random location on the game map.
     *
     * @param map The current game map.
     * @return A random location on the game map.
     */
    private Location getRandomLocation(GameMap map) {
        int x = this.rand.nextInt(map.getXRange().max());
        int y = this.rand.nextInt(map.getYRange().max());
        return map.at(x, y);
    }

    /**
     * Returns a list of Actions that can be performed on the Theseus.
     *
     * @param location The location of the item on the ground.
     * @return An ActionList containing a Teleport Action on this Theseus.
     */
    @Override
    public ActionList allowableActions(Location location) {
        ActionList actions = new ActionList();
        actions.add(new TeleportAction(this));
        return actions;
    }
}
