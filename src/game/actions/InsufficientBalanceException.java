package game.actions;

import game.items.Purchasable;

/**
 * Represents an exception that is thrown when an Actor does not have enough credits to purchase an item.
 */

public class InsufficientBalanceException extends Exception {

    /**
     * Constructs an InsufficientBalanceException with a message indicating the Actor does not have enough credits to purchase an item.
     *
     * @param item The item that the Actor is trying to purchase.
     * @param balance The balance of the Actor.
     * @param cost The cost of the item.
     */
    public InsufficientBalanceException(Purchasable item, int balance, int cost) {
        super("Insufficient balance to purchase " + item + " (balance: " + balance + ", required: " + cost + ")");
    }
}
