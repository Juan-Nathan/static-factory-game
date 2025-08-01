package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Sellable;

public class SellingAction extends Action {

    /**
     * Item which can be sold
     */
    private Sellable item;

    /**
     * Actor which is the buyer
     */
    private Actor buyer;

    /**
     * Constructor for SellingAction to sell an item to the buyer
     *
     * @param item The item to be sold
     * @param buyer The buyer
     */
    public SellingAction(Sellable item, Actor buyer) {
        this.item = item;
        this.buyer = buyer;
    }

    /**
     * Executes selling the item to the buyer
     *
     * @param actor The Actor performing the Action.
     * @param map The map the Actor is on.
     * @return A String describing the Actor's purchase of the item.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.item.soldTo(buyer, actor, map);
    }

    /**
     * Generates a description of the Selling Action to be displayed in the menu.
     *
     * @param actor The Actor performing the Action.
     * @return A String representing the menu description of the Selling Action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item;
    }

}

