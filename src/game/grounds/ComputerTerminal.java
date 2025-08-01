package game.grounds;

import java.util.Map;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PurchaseAction;
import game.actions.TravelAction;
import game.items.*;

/**
 * Represents a Computer Terminal.
 * Computer Terminal is a unique Ground type that allows an Actor to purchase items and travel to different places.
 */

public class ComputerTerminal extends Ground {

    /**
     * A map of location names as keys and their corresponding locations as values.
     */
    private Map<String, Location> travelLocations;

    /**
     * Constructs a Computer Terminal.
     * The Computer Terminal is initialised with a display character '=', representing its appearance on the game map.
     */
    public ComputerTerminal(Map<String, Location> travelLocations) {
        super('=');
        this.travelLocations = travelLocations;
    }

    /**
     * Generates a list of Actions for the Actor to perform at the Computer Terminal.
     *
     * @param actor     The Actor acting.
     * @param location  The current location.
     * @param direction The direction of the ground from the Actor.
     * @return An ActionList can can contain Purchase Actions and Travel Actions at this Computer Terminal.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (direction.isEmpty()) {
            actions.add(new PurchaseAction(new EnergyDrink()));
            actions.add(new PurchaseAction(new DragonSlayerSword()));
            actions.add(new PurchaseAction(new ToiletPaperRoll()));
            actions.add(new PurchaseAction(new Theseus()));
            actions.add(new PurchaseAction(new Astley()));
            for (Map.Entry<String, Location> entry : this.travelLocations.entrySet()) {
                if (entry.getValue().map() != location.map()) {
                    actions.add(new TravelAction(entry.getValue(), entry.getKey()));
                }
            }
        }
        return actions;
    }
}
