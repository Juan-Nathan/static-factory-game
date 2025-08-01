package game.actors;

import java.util.Map;
import java.util.TreeMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.behaviours.WanderBehaviour;
import game.actions.AttackAction;

/**
 * An abstract representation of a Hostile Creature in the game.
 * Hostile Creatures are Actors with aggressive behaviours towards the Player
 * and possibly other Actors. This class defines the common functionalities
 * and behaviours shared among different types of Hostile Creatures.
 */

public abstract class HostileCreature extends Actor {

    /**
     * A map of priorities to behaviours for determining Actions during the game turn.
     */
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * Constructs a Hostile Creature with specified name, display character, and hit points.
     * The hostile creature is initialised with the capability to be hostile towards the Player.
     *
     * @param name        The name of the creature.
     * @param displayChar The character to represent the creature in the game display.
     * @param hitPoints   The initial hit points of the creature.
     */
    public HostileCreature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     * Determines and returns the Hostile Creature's next Action to perform on each turn.
     * Iterates through the creature's behaviors based on priority to find
     * an actionable behaviour. If no Actions are found, returns a Do Nothing Action.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with 'Action.getNextAction()'.
     * @param map        The map that the Actor is on.
     * @param display    The I/O object to which messages may be written.
     * @return An Action that the creature can perform on this turn, or a Do Nothing Action if there are no valid Actions.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Determines the Actions that can be performed by other Actors on this Hostile Creature.
     * If the other Actor has the capability of being hostile towards enemies, they can attack
     * this creature.
     *
     * @param otherActor The Actor that might attack this creature.
     * @param direction  A String representing the direction of the other Actor.
     * @param map        The map that the Actor is on.
     * @return An ActionList containing all allowable Actions that the other Actor can perform on this creature.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
