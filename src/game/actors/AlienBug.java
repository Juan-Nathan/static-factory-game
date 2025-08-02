package game.actors;

import java.util.ArrayList;
import java.util.Random;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.FollowBehaviour;
import game.behaviours.PickUpBehaviour;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Ability;

/**
 * Represents an Alien Bug, a type of Hostile Creature within the game.
 * This Hostile Creature can pick up items and follow an Actor.
 */

public class AlienBug extends HostileCreature {

    /**
     * Constructs an Alien Bug.
     * Initialises the creature with the specified name, display character 'a', and hit points of 2.
     *
     * @param name   The name of the Alien Bug.
     * @param target The Actor to be followed by the Alien Bug.
     */
    public AlienBug(String name, Actor target) {
        super(name, 'a', 2);
        this.behaviours.put(0, new PickUpBehaviour());
        this.behaviours.put(1, new FollowBehaviour(target));
        this.addCapability(Ability.ENTER_FLOOR);
    }

    /**
     * Creates a new Alien Bug with a random feature number.
     *
     * @param target The Actor to be followed by the Alien Bug.
     * @param rand   Random number generator.
     * @return A new Alien Bug with a random feature number.
     */
    public static AlienBug createFeatureAlienBug(Actor target, Random rand) {
        int featureNumber = rand.nextInt(900) + 100;
        String name = "Feature-" + featureNumber;
        return new AlienBug(name, target);
    }

    /**
     * Causes the Alien Bug to drop all items in its inventory when it becomes unconscious.
     *
     * @param actor The Actor causing the Alien Bug to become unconscious.
     * @param map   Where the Alien Bug fell unconscious on the map.
     * @return A String detailing the outcome of the Alien Bug becoming unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        String result = super.unconscious(actor, map);
        for (Item item : new ArrayList<>(this.getItemInventory())) {
            this.removeItemFromInventory(item);
            currentLocation.addItem(item);
        }
        return result;
    }
}