package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds skittles to a cake.
 *
 * <p>This decorator wraps a {@link Cake} instance and enhances its description
 * by appending " with Skittles" and increases the cost by a fixed amount.
 * It follows the Decorator pattern to add features without modifying the base cake.
 *
 * <p>Example usage:
 * <pre>
 * Cake base = new AppleCake(2, "Medium", 10.00);
 * Cake decorated = new SkittlesDecorator(base);
 * System.out.println(decorated.describe()); // "Order #2: Apple Cake (Medium) with Skittles"
 * System.out.println(decorated.getCost()); // 11.5
 * </pre>
 *
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class SkittlesDecorator extends CakeDecorator {

    /**
     * The additional cost for adding skittles to the cake.
     */
    private static final double SKITTLES_COST = 1.5;

    /**
     * Constructs a new SkittlesDecorator wrapping the given cake.
     *
     * @param decoratedCake The cake instance to be decorated with skittles
     */
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    /**
     * Returns the description of the decorated cake including the skittles addition.
     *
     * @return A description of the cake with skittles added
     */
    @Override
    public String describe() {
        return this.decoratedCake.describe() + " with Skittles";
    }

    /**
     * Returns the total cost of the decorated cake including the skittles cost.
     *
     * @return The total cost including the skittles addition
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + SKITTLES_COST;
    }
}
