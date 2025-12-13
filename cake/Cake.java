/**
 * Abstract base class representing a cake in the Cake Shop ordering system.
 * 
 * <p>This class serves as the foundation for the Decorator pattern implementation.
 * Concrete cake types (AppleCake, CheeseCake, ChocolateCake) extend this class,
 * and decorators (CakeDecorator) wrap instances of this class to add features
 * like chocolate chips, cream, and skittles without modifying the base cake.
 * 
 * <p>Each cake maintains order information including a unique order ID, base name,
 * size, and base price. The price and description can be enhanced by decorators.
 * 
 * @see decorators.CakeDecorator
 * @author Amer Abuyaqob
 * @version 1.0
 */
public abstract class Cake{
    //TODO: make orderID auto generated
    protected int orderID;
    protected String baseName;
    protected String size;
    protected double basePrice;

    /**
     * Constructor for Cake class.
     * Initializes all fields for a cake order.
     * 
     * @param orderID The unique identifier for this order
     * @param baseName The name of the base cake type (e.g., "Apple Cake", "Cheese Cake")
     * @param size The size of the cake
     * @param basePrice The base price of the cake before decorations
     */
    public Cake(int orderID, String baseName, String size, double basePrice) {
        this.orderID = orderID;
        this.baseName = baseName;
        this.size = size;
        this.basePrice = basePrice;
    }

    /**
     * Retrieves the unique order identifier for this cake.
     * 
     * @return The order ID associated with this cake
     */
    public int getOrderID() {
        return orderID;
    }
    //TODO: needs to be removed when auto generated orderId's are implemented
    /**
     * Sets the unique order identifier for this cake.
     * 
     * @param orderID The order ID to assign to this cake
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Retrieves the base name of the cake type (e.g., "Apple Cake", "Cheese Cake", "Chocolate Cake").
     * 
     * @return The name of the base cake type
     */
    public String getBaseName() {
        return baseName;
    }

    //TODO: needs some constraints
    /**
     * Sets the base name of the cake type.
     * 
     * @param baseName The name of the base cake type to assign
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }
    
    /**
     * Retrieves the size of the cake.
     * 
     * @return The size of the cake
     */
    public String getSize() {
        return size;
    }


    //TODO: needs some constraints
    /**
     * Sets the size of the cake.
     * 
     * @param size The size to assign to the cake
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Retrieves the base price of the cake before any decorations are applied.
     * 
     * <p>Note: This returns only the base price. For the total cost including
     * decorations, use the {@link #getCost()} method which should be implemented
     * by subclasses and decorators.
     * 
     * @return The base price of the cake
     * @see #getCost()
     */
    public double getBasePrice() {
        return basePrice;
    }


    //TODO: needs some constraints
    /**
     * Sets the base price of the cake before decorations.
     * 
     * @param basePrice The base price to assign to the cake
     */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    /**
     * Provides a description of the cake, including its base type and any decorations.
     * 
     * <p>Concrete cake classes must implement this to describe the base cake.
     * Decorators will enhance this description to include their added features.
     * 
     * <p>Example output: "Order #7: Chocolate Cake (Large) with Chocolate Chips and Cream"
     * 
     * @return A string description of the cake with all its features
     */
    public abstract String describe();

    /**
     * Calculates the total cost of the cake, including the base price and all decorations.
     * 
     * <p>Concrete cake classes must implement this to return the base price.
     * Decorators will add their decoration costs to this value.
     * 
     * <p>This method follows the Decorator pattern: each decorator adds its cost
     * to the cost of the wrapped cake object.
     * 
     * @return The total cost of the cake including all decorations
     */
    public abstract double getCost();
}
