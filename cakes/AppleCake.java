package cakes;

    /**
     * Concrete implementation of {@link Cake} representing an apple cake.
     *
     * <p>This class provides a simple base cake with a name of "Apple Cake",
     * a size, and a base price. It is intended to be used with the Decorator
     * pattern where decorators wrap a `Cake` instance to add additional features
     * and costs (for example, fruit toppings or cream).
     *
     * <p>Example usage:
     * <pre>
     * AppleCake base = new AppleCake(2, CakeSize.MEDIUM, 10.00);
     * </pre>
     *
     * @author Mustafa Abu Saffaqa
     * @version 1.1
     */
public class AppleCake extends Cake {

    /**
     * Constructs a new AppleCake with the given order id, size and base price.
     *
     * @param orderID   the unique order identifier for this cake (format: XXX-S-###)
     * @param size      the size of the cake (CakeSize enum: SMALL, MEDIUM, or LARGE)
     * @param basePrice the base price of the cake before decorations
     */
    public AppleCake(String orderID, CakeSize size, double basePrice) {
        super(orderID, "Apple Cake", size, basePrice);
    }

    /**
     * Returns a human-readable description of this cake including order id,
     * base name and size. Decorators will append their details to this
     * description when wrapping this object.
     *
     * @return a description of the cake and its base features
     */
    @Override
    public String describe() {
        return "Order #" + getOrderID() + ": " + getBaseName() + " (" + getSize().getDisplayName() + ")";
    }

    /**
     * Returns the total cost for this base cake. Decorators add their own cost
     * on top of this value when calculating the full order cost.
     *
     * @return the base price of this apple cake
     */
    @Override
    public double getCost() {
        return getBasePrice();
    }
}

