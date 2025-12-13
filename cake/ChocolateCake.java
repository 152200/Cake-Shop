/**
 * Concrete implementation of {@link Cake} representing a chocolate cake.
 *
 * <p>This class provides a simple base cake with a name of "Chocolate Cake",
 * a size, and a base price. It is intended to be used with the Decorator
 * pattern where decorators wrap a `Cake` instance to add additional features
 * and costs (for example, chocolate chips or cream).
 *
 * <p>Example usage:
 * <pre>
 * ChocolateCake base = new ChocolateCake(1, "Large", 12.50);
 * </pre>
 *
 * @author Mustafa Abu Saffaqa
 * @version 1.0
 */
public class ChocolateCake extends Cake {

	/**
	 * Constructs a new ChocolateCake with the given order id, size and base price.
	 *
	 * @param orderID   the unique order identifier for this cake
	 * @param size      the size of the cake (e.g. "Small", "Medium", "Large")
	 * @param basePrice the base price of the cake before decorations
	 */
	public ChocolateCake(int orderID, String size, double basePrice) {
		super(orderID, "Chocolate Cake", size, basePrice);
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
		return "Order #" + getOrderID() + ": " + getBaseName() + " (" + getSize() + ")";
	}

	/**
	 * Returns the total cost for this base cake. Decorators add their own cost
	 * on top of this value when calculating the full order cost.
	 *
	 * @return the base price of this chocolate cake
	 */
	@Override
	public double getCost() {
		return getBasePrice();
	}
}

