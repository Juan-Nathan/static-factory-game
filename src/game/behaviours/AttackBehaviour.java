package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.capabilities.Status;
import game.actions.AttackAction;

/**
 * A behaviour that enables a hostile NPC to perform an Attack Action against
 * a target that is in an adjacent location. The target cannot be an Actor that
 * is also hostile towards the Player. The attack can be performed with or without
 * a specified Weapon.
 */

public class AttackBehaviour implements Behaviour {

    /**
     * The weapon to be used for the attack. If null, the Actor's Intrinsic Weapon is used.
     */
    private Weapon weapon;

    /**
     * Constructs an Attack Behaviour with no specified Weapon.
     * Attacks performed with this behaviour will use the Actor's Intrinsic Weapon.
     */
    public AttackBehaviour() {
        this.weapon = null;
    }

    /**
     * Constructs an Attack Behaviour with a specified Weapon.
     * The Weapon provided will be used to attack.
     *
     * @param weapon The Weapon to use for the attack.
     */
    public AttackBehaviour(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Determines the Action for attacking an adjacent Actor.
     * If an adjacent Actor without the Status.HOSTILE_TO_PLAYER capability is found,
     * an Attack Action is returned to attack that Actor. The attack may use the specified
     * Weapon or the Actor's Intrinsic Weapon if no weapon was specified.
     *
     * @param actor The Actor enacting this behaviour.
     * @param map   The map that the Actor is on.
     * @return An AttackAction targeting an adjacent Actor if applicable, or null if no such Action is possible.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        for (Exit exit : currentLocation.getExits()) {
            Location adjacent = exit.getDestination();
            if (adjacent.containsAnActor()) {
                Actor target = adjacent.getActor();
                if (!target.hasCapability(Status.HOSTILE_TO_PLAYER)) {
                    if (this.weapon != null) {
                        return new AttackAction(target, exit.getName(), this.weapon);
                    } else {
                        return new AttackAction(target, exit.getName());
                    }
                }
            }
        }
        return null;
    }
}
