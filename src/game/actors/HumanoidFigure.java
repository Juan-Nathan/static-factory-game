package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Ability;

/**
 * Represents a Humanoid Figure, an Actor that can buy scrap items.
 */

public class HumanoidFigure extends Actor {

    /**
     * Constructs a Humanoid Figure.
     * The Humanoid Figure is initialised with the name "Humanoid Figure", display character 'H', and hit points of 1.
     */
    public HumanoidFigure() {
        super("Humanoid Figure", 'H', 1);
        // Add the capability to buy scrap items
        this.addCapability(Ability.BUY_SCRAPS);
    }

    /**
     * Returns a Do Nothing Action for the Humanoid Figure to perform on each turn.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with 'Action.getNextAction()'.
     * @param map        The map that the Actor is on.
     * @param display    The I/O object to which messages may be written.
     * @return A Do Nothing Action.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}