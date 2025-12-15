package cakes;

	/**
	 * Concrete implementation of {@link Cake} representing a cheese cake.
	 *
	 * <p>This class provides a simple base cake with a name of "Cheese Cake",
	 * a size, and a base price. It is intended to be used with the Decorator
	 * pattern where decorators wrap a `Cake` instance to add additional features
	 * and costs (for example, chocolate chips or cream).
	 *
	 * <p>Example usage:
	 * <pre>
	 * CheeseCake base = new CheeseCake(1, CakeSize.LARGE, 12.50);
	 * </pre>
	 *
	 * @author Amer Abuyaqob
	 * @version 1.1
	 */
public class CheeseCake extends Cake {

	/**
	 * Constructs a new CheeseCake with the given order id, size and base price.
	 *
	 * @param orderID   the unique order identifier for this cake (format: XXX-S-###)
	 * @param size      the size of the cake (CakeSize enum: SMALL, MEDIUM, or LARGE)
	 * @param basePrice the base price of the cake before decorations
	 */
	public CheeseCake(String orderID, CakeSize size, double basePrice) {
		super(orderID, "Cheese Cake", size, basePrice);
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
	 * @return the base price of this cheese cake
	 */
	@Override
	public double getCost() {
		return getBasePrice();
	}
}

