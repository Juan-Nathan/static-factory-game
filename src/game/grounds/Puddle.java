package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.items.Consumable;

/**
 * Represents a Puddle on the map.
 * Puddle is a unique Ground type can be consumed by an Actor.
 */

public class Puddle extends Ground implements Consumable {

    /**
     * Constructs a Puddle.
     * The Puddle is initialised with a display character '~', representing its appearance on the game map.
     */
    public Puddle() {
        super('~');
    }

    /**
     * Consumes the Puddle, increasing the Actor's maximum hit points by 1.
     *
     * @param actor The Actor consuming the item.
     * @return A String describing the result of consuming the item.
     */
    @Override
    public String consumedBy(Actor actor) {
        String result = actor + " slurps the " + this + " and gets refreshed, increasing maximum hit points by 1";
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 1);
        return result;
    }

    /**
     * Generates a list of Actions for the Actor to perform on the Puddle.
     *
     * @param actor     The Actor acting.
     * @param location  The current location.
     * @param direction The direction of the Ground from the Actor.
     * @return An ActionList can can contain a Consume Action on this Puddle.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (direction.isEmpty()) {
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Returns a String representation of the Puddle.
     *
     * @return A String representation of the Puddle.
     */
    @Override
    public String toString() {
        return "Puddle of Water";
    }
}
