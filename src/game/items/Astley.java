package game.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.InsufficientBalanceException;
import game.actions.ListenAction;

/**
 * Represents an AI Device called Astley, a purchasable and monologuing item in the game.
 */

public class Astley extends Item implements Purchasable, Monologist {

    /**
     * The number of ticks the AI Device has been in the Actor's inventory.
     */
    private int tickCount;

    /**
     * A boolean value indicating whether the Actor is subscribed to the AI Device.
     */
    private boolean subscribed;

    /**
     * Random number generator.
     */
    private final Random rand = new Random();

    /**
     * Constructs an Astley item.
     * Initialises the item with the name "Astley", display character 'z', and sets it to be portable.
     */
    public Astley() {
        super("Astley", 'z', true);
        this.tickCount = 0;
        this.subscribed = true;
    }

    /**
     * Manages the AI Device's subscription status.
     *
     * @param currentLocation The location of the Actor carrying this item.
     * @param actor           The Actor carrying this item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.getItemInventory().contains(this)) {
            this.tickCount++;
            if (this.tickCount % 5 == 0) {
                if (actor.getBalance() >= 1) {
                    actor.deductBalance(1);
                    this.subscribed = true;
                    System.out.println("Subscription to " + this + " has been renewed for 1 credit");
                }
                else {
                    this.subscribed = false;
                    System.out.println("Subscription to " + this + " has expired due to insufficient credits");
                }
            }
        }
    }

    /**
     * Purchases the AI Device.
     *
     * @param actor The Actor purchasing the item.
     * @return A String describing the result of the purchase.
     * @throws InsufficientBalanceException When the Actor does not have enough credits to purchase the item.
     */
    @Override
    public String purchasedBy(Actor actor) throws InsufficientBalanceException {
        String result = "";
        if (actor.getBalance() < 50) {
            throw new InsufficientBalanceException(this, actor.getBalance(), 50);
        } else {
            result += actor + " purchases " + this + " for 50 credits";
            actor.deductBalance(50);
            actor.addItemToInventory(this);
        }
        return result;
    }

    /**
     * Returns a random monologue from the list of available monologues.
     *
     * @param actor The Actor listening to the monologue.
     * @return A String representing the monologue.
     */
    @Override
    public String monologue(Actor actor) {
        List<String> availableMonologues = new ArrayList<>();
        availableMonologues.add("The Factory will never gonna give you up, valuable Intern!");
        availableMonologues.add("We promise we never gonna let you down with a range of staff benefits.");
        availableMonologues.add("We never gonna run around and desert you, dear Intern!");

        if (actor.getItemInventory().size() > 10) {
            availableMonologues.add("We never gonna make you cry with unfair compensation.");
        }

        if (actor.getBalance() > 50) {
            availableMonologues.add("Trust is essential in this business. We promise we never gonna say goodbye to a valuable Intern like you.");
        }

        if (actor.getAttribute(BaseActorAttributes.HEALTH) < 2) {
            availableMonologues.add("Don't worry, we never gonna tell a lie and hurt you, unlike those Hostile Creatures.");
        }

        return availableMonologues.get(this.rand.nextInt(availableMonologues.size()));
    }

    /**
     * Returns a list of Actions that can be performed on the AI Device.
     *
     * @param owner The Actor that owns the AI Device.
     * @return An ActionList that can contain Monologue Action on this AI Device.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();
        if (this.subscribed) {
            actions.add(new ListenAction(this));
        }
        return actions;
    }
}
