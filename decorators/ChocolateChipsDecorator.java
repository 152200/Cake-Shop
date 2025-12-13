package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds chocolate chips to a cake.
 * 
 * <p>This decorator wraps a {@link Cake} instance and enhances it by adding
 * chocolate chips to the description and adding the chocolate chips cost
 * to the total price.
 * 
 * <p>Example usage:
 * <pre>
 * Cake base = new ChocolateCake(1, "Large", 12.50);
 * Cake decorated = new ChocolateChipsDecorator(base);
 * // Result: "Order #1: Chocolate Cake (Large) with Chocolate Chips"
 * // Cost: 12.50 + 2.50 = 15.00
 * </pre>
 * 
 * @author Amer Abuyaqob
 * @version 1.0
 */
public class ChocolateChipsDecorator extends CakeDecorator {
    
    /** The cost of adding chocolate chips to a cake */
    private static final double CHOCOLATE_CHIPS_COST = 2.50;

    /**
     * Constructs a new ChocolateChipsDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with chocolate chips
     */
    public ChocolateChipsDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    /**
     * Returns the description of the cake with chocolate chips added.
     * 
     * <p>Appends " with Chocolate Chips" or " and Chocolate Chips" to the wrapped cake's description
     * depending on whether the cake already has decorations. If the cake already
     * has decorations (contains "with"), it uses "and" to chain decorations.
     * 
     * @return A description of the cake including chocolate chips
     */
    @Override
    public String describe() {
        String baseDescription = this.decoratedCake.describe();
        // Check if description already contains "with" to determine connector
        if (baseDescription.contains("with")) {
            return baseDescription + " and Chocolate Chips";
        } else {
            return baseDescription + " with Chocolate Chips";
        }
    }

    /**
     * Returns the total cost of the cake including chocolate chips.
     * 
     * <p>Adds the chocolate chips cost to the wrapped cake's total cost.
     * 
     * @return The total cost including the base cake and chocolate chips
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + CHOCOLATE_CHIPS_COST;
    }
}
