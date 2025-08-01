package game.capabilities;

/**
 * Enumerates different statuses that can be possessed by Actors or other entities within the game.
 * Example #1: If the Player is sleeping, you can attach Status.SLEEP to the Player class.
 */

public enum Status {

    /**
     * Status that represents hostility towards enemies.
     */
    HOSTILE_TO_ENEMY,

    /**
     * Status that represents hostility towards the Player.
     */
    HOSTILE_TO_PLAYER,

}
