package game.behaviours;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.actors.Behaviour;


/**
 * A behaviour that allows an NPC to wander around its current location on the map.
 * This behaviour selects a random adjacent location that the Actor can enter and generates
 * a Move Action to move the Actor to that location. If no adjacent locations are accessible,
 * no Action is generated, and the method returns null.
 */
public class WanderBehaviour implements Behaviour {

    private final Random random = new Random();

    /**
     * Determines an Action for wandering to a random adjacent location.
     * Iterates through the exits from the Actor's current location to find accessible locations.
     * If one or more accessible locations are found, one is selected randomly, and a Move Action
     * to that location is returned. If no movement is possible, returns null.
     *
     * @param actor The Actor enacting this behaviour.
     * @param map   The map that the Actor is on.
     * @return An Action to move the Actor to a random adjacent location, or null if no such move is possible.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

    }
}
