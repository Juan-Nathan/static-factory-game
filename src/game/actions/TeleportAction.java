package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Teleporter;

/**
 * An Action that enables an Actor to teleport to another location on the same game map using a teleporter.
 */

public class TeleportAction extends Action {

    /**
     * The teleporter to teleport with.
     */
    private Teleporter teleporter;

    /**
     * Constructs a Teleport Action for the specified teleporter.
     *
     * @param teleporter The teleporter to teleport with.
     */
    public TeleportAction(Teleporter teleporter) {
        this.teleporter = teleporter;
    }

    /**
     * Executes the teleportation of the Actor with the specified teleporter.
     *
     * @param actor The Actor performing the Action.
     * @param map   The map the Actor is on.
     * @return A String describing the Actor's teleportation with the teleporter.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.teleporter.teleport(actor, map);
    }

    /**
     * Generates a description of the Teleport Action to be displayed in the menu.
     * The description includes the performing Actor and the teleporter to teleport with.
     *
     * @param actor The Actor performing the Action.
     * @return A String representing the menu description of the Teleport Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports with " + this.teleporter;
    }
}
