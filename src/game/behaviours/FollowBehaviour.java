package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A behaviour that enables an NPC to follow another Actor.
 */

public class FollowBehaviour implements Behaviour {

    /**
     * The Actor to follow.
     */
    private final Actor target;

    /**
     * A boolean flag to indicate if the Actor has started following the target.
     */
    private boolean hasStartedFollowing = false;

    /**
     * Constructs a Follow Behaviour.
     *
     * @param target The Actor to follow.
     */
    public FollowBehaviour(Actor target) {
        this.target = target;
    }

    /**
     * Determines the Action for following the target Actor.
     *
     * @param actor The Actor enacting this behaviour.
     * @param map   The map that the Actor is on.
     * @return A MoveActorAction to move towards the target Actor, or null if the Actor has not started following the target.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor)) {
            return null;
        }

        Location actorLocation = map.locationOf(actor);
        Location targetLocation = map.locationOf(target);

        int currentDistance = this.computeDistance(actorLocation, targetLocation);

        if (!hasStartedFollowing && currentDistance == 1) {
            hasStartedFollowing = true;
        }

        if (hasStartedFollowing) {
            Action bestAction = null;
            int bestDistance = currentDistance;

            for (Exit exit : actorLocation.getExits()) {
                Location destination = exit.getDestination();
                if (destination.canActorEnter(actor)) {
                    int newDistance = this.computeDistance(destination, targetLocation);
                    if (newDistance < bestDistance) {
                        bestDistance = newDistance;
                        bestAction = new MoveActorAction(destination, exit.getName());
                    } else if (newDistance == bestDistance && bestAction == null) {
                        bestAction = new MoveActorAction(destination, exit.getName());
                    }
                }
            }
            return bestAction;
        }
        return null;
    }

    /**
     * Computes the Chebyshev distance between two locations.
     *
     * @param a The first location.
     * @param b The second location.
     * @return The Chebyshev distance between the two locations.
     */
    private int computeDistance(Location a, Location b) {
        return Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y()));
    }
}
