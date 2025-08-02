package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A behaviour that enables an NPC to pick up an item from the current location.
 */

public class PickUpBehaviour implements Behaviour {

    /**
     * Determines the Action for picking up an item from the current location.
     *
     * @param actor The Actor enacting this behaviour.
     * @param map   The map that the Actor is on.
     * @return  A PickUpAction to pick up an item from the current location, or null if no item is available to pick up.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        for (Item item : currentLocation.getItems()) {
            Action pickUpAction = item.getPickUpAction(actor);
            if (pickUpAction != null) {
                return pickUpAction;
            }
        }
        return null;
    }
}
