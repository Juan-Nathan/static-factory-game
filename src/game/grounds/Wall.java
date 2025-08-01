package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Represents a Wall.
 */

public class Wall extends Ground {

    /**
     * Constructs a Wall.
     * The Wall is initialised with a display character '#', representing its appearance on the game map.
     */
    public Wall() {
        super('#');
    }

    /**
     * Determines that no Actors can enter a Wall.
     *
     * @param actor The Actor to check.
     * @return False.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
