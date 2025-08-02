package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.FancyMessage;
import game.capabilities.*;

/**
 * Represents the Player in the game.
 */

public class Player extends Actor {

    /**
     * Constructs a Player character with specified name, display character, and initial hit points.
     * The Player is initialised with the capability to be hostile towards enemies.
     *
     * @param name        The name of the Player.
     * @param displayChar The character to represent the Player in the game display.
     * @param hitPoints   The initial hit points of the Player.
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.ENTER_FLOOR);
    }

    /**
     * Determines and returns the Player's next Action to perform on each turn.
     * This method handles multi-turn Actions and displays the console menu of Actions.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with 'Action.getNextAction()'.
     * @param map        The map that the Actor is on.
     * @param display    The I/O object to which messages may be written.
     * @return The Action selected by the Player.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        System.out.println("------- " + this.name + " Status -------");
        System.out.println("Health: " + this.getAttribute(BaseActorAttributes.HEALTH) + "/" + this.getAttributeMaximum(BaseActorAttributes.HEALTH) + " hit points");
        System.out.println("Balance: " + this.getBalance() + " credits");
        System.out.println("-----------------------------");
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Defines the Intrinsic Weapon of the Player.
     * This method overrides the 'getIntrinsicWeapon()' method in Actor.
     *
     * @return An IntrinsicWeapon object representing the Player's intrinsic weapon.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(1, "punches", 5);
    }

    /**
     * Adds a Fancy Message to the 'result' String when the Player becomes unconscious due to another Actor.
     *
     * @param actor The Actor causing the Player to become unconscious.
     * @param map   Where the Player fell unconscious on the map.
     * @return A String detailing the outcome of the Player becoming unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        String result = super.unconscious(actor, map);
        result += "\n" + FancyMessage.YOU_ARE_FIRED;
        return result;
    }

    /**
     * Adds a Fancy Message to the 'result' String when the Player becomes unconscious due to natural causes or accidents.
     *
     * @param map Where the Player fell unconscious on the map.
     * @return A String detailing the outcome of the Player becoming unconscious.
     */
    @Override
    public String unconscious(GameMap map) {
        String result = super.unconscious(map);
        result += "\n" + FancyMessage.YOU_ARE_FIRED;
        return result;
    }
}
