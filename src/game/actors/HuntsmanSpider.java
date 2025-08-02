package game.actors;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;

/**
 * Represents a Huntsman Spider, a type of Hostile Creature within the game.
 * This Hostile Creature can attack other Actors using its Intrinsic Weapon.
 */

public class HuntsmanSpider extends HostileCreature {

    /**
     * Constructs a Huntsman Spider.
     * Initialises the creature with the name "Huntsman Spider", display character '8', and hit points of 1.
     */
    public HuntsmanSpider() {
        super("Huntsman Spider", '8', 1);
        this.behaviours.put(0, new AttackBehaviour());
    }

    /**
     * Defines the Intrinsic Weapon of the Huntsman Spider.
     * This method overrides the abstract 'getIntrinsicWeapon()' method in HostileCreature.
     *
     * @return An IntrinsicWeapon object representing the creature's Intrinsic Weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1, "kicks", 25);
    }
}