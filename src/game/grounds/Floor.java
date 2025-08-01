package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.capabilities.Ability;

/**
 * Represents a Floor.
 *
 */
public class Floor extends Ground {

    /**
     * Constructs a Floor.
     * The Floor is initialised with a display character '_', representing its appearance on the game map.
     */
    public Floor() {
        super('_');
    }

    /**
     * Determines whether a specific Actor can enter this Floor tile.
     *
     * @param actor The Actor to check.
     * @return True if the Actor can enter, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Ability.ENTER_FLOOR);
    }
}
