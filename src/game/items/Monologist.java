package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for implementing Monologists in the game.
 */

public interface Monologist {

    /**
     * Returns a monologue.
     *
     * @param actor The Actor listening to the monologue.
     * @return A String representing the monologue.
     */
    String monologue(Actor actor);
}
