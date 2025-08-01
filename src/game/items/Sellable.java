package game.items;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Actor;

public interface Sellable {

    /**
     * Defines what happens when an item is sold to an Actor.
     *
     * @param buyer The Actor buying the item.
     * @param seller The Actor selling the item.
     * @return A String describing the result of the sale.
     */
    String soldTo(Actor buyer, Actor seller, GameMap map);
}
