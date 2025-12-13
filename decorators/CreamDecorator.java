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
 * @version 2.0
 */
public class CreamDecorator extends CakeDecorator {
    
    /** The cost of adding cream to a cake */
    private static double CREAM_COST = 2.0;
    
    /** The name of this decoration */
    private static String CREAM_NAME = "Cream";

    /**
     * Constructs a new CreamDecorator wrapping the given cake.
     * 
     * <p>The current values of CREAM_COST and CREAM_NAME are captured as snapshots
     * at construction time, so this object will maintain these values even if the
     * static fields are changed later.
     * 
     * @param decoratedCake The cake instance to be decorated with cream
     */
    public CreamDecorator(Cake decoratedCake) {
        super(decoratedCake, CREAM_COST, CREAM_NAME);
    }
    
    //TODO: needs some constraints
    /**
     * Sets the cost of adding cream to a cake.
     * 
     * <p>Changing this value only affects new CreamDecorator instances created after
     * this call. Existing instances maintain their original cost snapshot.
     * 
     * @param cost The new cost for cream decoration
     */
    public static void setCreamCost(double cost) {
        CREAM_COST = cost;
    }
    
    //TODO: needs some constraints
    /**
     * Sets the name of this decoration.
     * 
     * <p>Changing this value only affects new CreamDecorator instances created after
     * this call. Existing instances maintain their original name snapshot.
     * 
     * @param name The new name for cream decoration
     */
    public static void setCreamName(String name) {
        CREAM_NAME = name;
    }
    
    /**
     * Gets the current cost of adding cream to a cake.
     * 
     * @return The current cost of cream decoration
     */
    public static double getCreamCost() {
        return CREAM_COST;
    }
    
    /**
     * Gets the current name of this decoration.
     * 
     * @return The current name of cream decoration
     */
    public static String getCreamName() {
        return CREAM_NAME;
    }

}
