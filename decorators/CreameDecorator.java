
package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds cream to a cake.
 *
 * <p>This decorator wraps a {@link Cake} instance and enhances its description
 * by appending "with Cream" and increases the cost by a fixed amount.
 * It follows the Decorator pattern to add features without modifying the base cake.
 *
 * <p>Example usage:
 * <pre>
 * Cake base = new ChocolateCake(1, "Large", 12.50);
 * Cake decorated = new CreameDecorator(base);
 * System.out.println(decorated.describe()); // "Order #1: Chocolate Cake (Large) with Cream"
 * System.out.println(decorated.getCost()); // 14.50
 * </pre>
 *
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class CreameDecorator extends CakeDecorator {

    /**
     * The additional cost for adding cream to the cake.
     */
    private static final double CREAM_COST = 2.0;

    /**
     * Constructs a new CreameDecorator wrapping the given cake.
     *
     * @param decoratedCake The cake instance to be decorated with cream
     */
    public CreameDecorator(Cake decoratedCake) {
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
     * Returns the total cost of the decorated cake including the cream cost.
     *
     * @return The total cost including the cream addition
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + CREAM_COST;
    }
}
