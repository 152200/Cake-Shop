package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds cream to a cake.
 * 
 * <p>This decorator wraps a {@link Cake} instance and enhances it by adding
 * cream to the description and adding the cream cost to the total price.
 * 
 * <p>Example usage:
 * <pre>
 * Cake base = new ChocolateCake(1, "Large", 12.50);
 * Cake decorated = new CreamDecorator(base);
 * // Result: "Order #1: Chocolate Cake (Large) with Cream"
 * // Cost: 12.50 + 2.00 = 14.50
 * </pre>
 *
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class CreamDecorator extends CakeDecorator {
    
    /** The cost of adding cream to a cake */
    private static final double CREAM_COST = 2.0;

    /**
     * Constructs a new CreamDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with cream
     */
    public CreamDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    /**
     * Returns the description of the decorated cake including the cream addition.
     *
     * @return A description of the cake with cream added
     */
    @Override
    public String describe() {
        return this.decoratedCake.describe() + " with Cream";
    }

    /**
     * Returns the total cost of the cake including cream.
     * 
     * <p>Adds the cream cost to the wrapped cake's total cost.
     * 
     * @return The total cost including the base cake and cream
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + CREAM_COST;
    }
}
