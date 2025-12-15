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
 * @version 2.0
 */
public class ChocolateChipsDecorator extends CakeDecorator {
    
    /** The cost of adding chocolate chips to a cake */
    private static double CHOCOLATE_CHIPS_COST = 2.50;
    
    /** The name of this decoration */
    private static String CHOCOLATE_CHIPS_NAME = "Chocolate Chips";

    /**
     * Constructs a new ChocolateChipsDecorator wrapping the given cake.
     * 
     * <p>The current values of CHOCOLATE_CHIPS_COST and CHOCOLATE_CHIPS_NAME are
     * captured as snapshots at construction time, so this object will maintain these
     * values even if the static fields are changed later.
     * 
     * @param decoratedCake The cake instance to be decorated with chocolate chips
     */
    public ChocolateChipsDecorator(Cake decoratedCake) {
        super(decoratedCake, CHOCOLATE_CHIPS_COST, CHOCOLATE_CHIPS_NAME);
    }
    
    //TODO: needs some constraints
    /**
     * Sets the cost of adding chocolate chips to a cake.
     * 
     * <p>Changing this value only affects new ChocolateChipsDecorator instances created
     * after this call. Existing instances maintain their original cost snapshot.
     * 
     * @param cost The new cost for chocolate chips decoration
     */
    public static void setChocolateChipsCost(double cost) {
        CHOCOLATE_CHIPS_COST = cost;
    }
    
    //TODO: needs some constraints
    /**
     * Sets the name of this decoration.
     * 
     * <p>Changing this value only affects new ChocolateChipsDecorator instances created
     * after this call. Existing instances maintain their original name snapshot.
     * 
     * @param name The new name for chocolate chips decoration
     */
    public static void setChocolateChipsName(String name) {
        CHOCOLATE_CHIPS_NAME = name;
    }
    
    /**
     * Gets the current cost of adding chocolate chips to a cake.
     * 
     * @return The current cost of chocolate chips decoration
     */
    public static double getChocolateChipsCost() {
        return CHOCOLATE_CHIPS_COST;
    }
    
    /**
     * Gets the current name of this decoration.
     * 
     * @return The current name of chocolate chips decoration
     */
    public static String getChocolateChipsName() {
        return CHOCOLATE_CHIPS_NAME;
    }

}
