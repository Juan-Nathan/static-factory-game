package game.actions;

import java.util.Random;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * An Action that enables an Actor to attack another Actor in a specified direction.
 * The attack can be performed using a specified weapon or the Actor's intrinsic weapon.
 * The outcome of the attack is determined by the weapon's accuracy and the amount of damage it deals.
 */

public class AttackAction extends Action {

    /**
     * The Actor to be attacked.
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction;

    /**
     * Random number generator.
     */
    private Random rand = new Random();

    /**
     * Weapon used for the attack.
     */
    private Weapon weapon;

    /**
     * Constructs an Attack Action using a specified weapon.
     *
     * @param target    The Actor to attack.
     * @param direction The direction from which the attack is performed (only used for display purposes).
     */
    public AttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * Constructs an Attack Action using the Actor's intrinsic weapon.
     *
     * @param target    The Actor to attack.
     * @param direction The direction from which the attack is performed (only used for display purposes).
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Executes the attack.
     * This method determines if the attack hits based on the weapon's accuracy. If the attack is successful,
     * it applies damage to the target. If the target becomes unconscious as a result of the attack,
     * the target is removed from the map.
     *
     * @param actor The Actor performing the Action.
     * @param map   The map that the Actor is on.
     * @return A String describing the result of the attack Action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.weapon == null) {
            this.weapon = actor.getIntrinsicWeapon();
        }

        if (!(rand.nextInt(100) <= this.weapon.chanceToHit())) {
            return actor + " misses " + this.target;
        }

        int damage = this.weapon.damage();
        String result = actor + " " + this.weapon.verb() + " " + this.target + " at " + this.direction + " for " + damage + " damage";
        this.target.hurt(damage);
        if (!this.target.isConscious()) {
            result += "\n" + this.target.unconscious(actor, map);
        }

        return result;
    }

    /**
     * Generates a description of the Attack Action to be displayed in the menu.
     * The description includes the Actor performing the attack, the target of the attack,
     * the direction of the attack, and the weapon used.
     *
     * @param actor The Actor performing the Action.
     * @return A String representing the menu description of the attack Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + this.target + " at " + this.direction + " with " + (this.weapon != null ? this.weapon : "Intrinsic Weapon");
    }
}
