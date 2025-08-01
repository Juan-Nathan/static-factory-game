package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Monologist;

/**
 * An Action that enables an Actor to listen to a monologist's monologue.
 */

public class ListenAction extends Action {

    /**
     * The monologist giving the monologue.
     */
    private Monologist monologist;

    /**
     * Constructs a Monologue Action for the specified monologist.
     *
     * @param monologist The monologist giving the monologue.
     */
    public ListenAction(Monologist monologist) {
        this.monologist = monologist;
    }

    /**
     * Executes the listening of the monologue by the specified Actor.
     *
     * @param actor The actor performing the Action.
     * @param map The map the Actor is on.
     * @return A String representing the monologue.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.monologist + ": \"" + this.monologist.monologue(actor) + "\"";
    }

    /**
     * Generates a description of the Monologue Action to be displayed in the menu.
     * The description includes the performing Actor and the monologist giving the monologue.
     *
     * @param actor The actor performing the Action.
     * @return A String representing the menu description of the Monologue Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " listens to " + this.monologist;
    }
}
