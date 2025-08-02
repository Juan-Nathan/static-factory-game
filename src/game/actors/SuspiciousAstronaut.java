package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;

/**
 * Represents a Suspicious Astronaut, a type of Hostile Creature within the game.
 * This Hostile Creature can attack other Actors using its Intrinsic Weapon.
 */
public class SuspiciousAstronaut extends HostileCreature {

    /**
     * Constructs a Suspicious Astronaut.
     * Initialises the creature with the name "Suspicious Astronaut", display character 'ඞ', and hit points of 99.
     */
    public SuspiciousAstronaut() {
        super("Suspicious Astronaut", 'ඞ', 99);
        this.behaviours.put(0, new AttackBehaviour());
    }

    /**
     * Defines the Intrinsic Weapon of the Suspicious Astronaut.
     *
     * @return An IntrinsicWeapon object representing the creature's Intrinsic Weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(Integer.MAX_VALUE, "obliterates", 100);
    }
}
