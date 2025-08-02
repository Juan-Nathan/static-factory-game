package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action that enables an Actor to travel to different places (e.g., a location on a different game map).
 */

public class TravelAction extends Action {

    /**
     * The destination location to travel to.
     */
    private Location destination;

    /**
     * The name of the destination location.
     */
    private String destinationName;

    /**
     * Constructs a Travel Action to the specified destination location.
     *
     * @param destination     The destination location to travel to.
     * @param destinationName The name of the destination location.
     */
    public TravelAction(Location destination, String destinationName) {
        this.destination = destination;
        this.destinationName = destinationName;
    }

    /**
     * Executes the travel by removing the Actor from the current game map and adding them to the destination location.
     * @param actor The actor performing the Action.
     * @param map   The map the Actor is on.
     * @return A String describing the Actor's travel to the destination location.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (this.destination.containsAnActor()) {
            result += "Travel fails";
        }
        else {
            map.removeActor(actor);
            this.destination.addActor(actor);
            result += actor + " travels to " + this.destinationName + ", landing at " + this.destination;
        }
        return result;
    }

    /**
     * Generates a description of the Travel Action to be displayed in the menu.
     * The description includes the performing Actor and the destination location.
     *
     * @param actor The Actor performing the Action.
     * @return A String representing the menu description of the Travel Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + this.destinationName;
    }
}
