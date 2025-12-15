package decorators;

import cakes.Cake;

/**
 * Concrete decorator that adds skittles to a cake.
 * 
 * <p>This decorator wraps a {@link Cake} instance and enhances it by adding
 * skittles to the description and adding the skittles cost to the total price.
 * 
 * <p>Example usage:
 * <pre>
 * Cake base = new AppleCake(2, "Medium", 10.00);
 * Cake decorated = new SkittlesDecorator(base);
 * // Result: "Order #2: Apple Cake (Medium) with Skittles"
 * // Cost: 10.00 + 1.50 = 11.50
 * </pre>
 * 
 * @author Mustafa Abu Saffaqa
 * @version 2.0
 */
public class SkittlesDecorator extends CakeDecorator {
    
    /** The cost of adding skittles to a cake */
    private static double SKITTLES_COST = 1.5;
    
    /** The name of this decoration */
    private static String SKITTLES_NAME = "Skittles";

    /**
     * Constructs a new SkittlesDecorator wrapping the given cake.
     * 
     * <p>The current values of SKITTLES_COST and SKITTLES_NAME are captured as snapshots
     * at construction time, so this object will maintain these values even if the static
     * fields are changed later.
     * 
     * @param decoratedCake The cake instance to be decorated with skittles
     */
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake, SKITTLES_COST, SKITTLES_NAME);
    }
    
    //TODO: needs some constraints
    /**
     * Sets the cost of adding skittles to a cake.
     * 
     * <p>Changing this value only affects new SkittlesDecorator instances created after
     * this call. Existing instances maintain their original cost snapshot.
     * 
     * @param cost The new cost for skittles decoration
     */
    public static void setSkittlesCost(double cost) {
        SKITTLES_COST = cost;
    }
    
    //TODO: needs some constraints
    /**
     * Sets the name of this decoration.
     * 
     * <p>Changing this value only affects new SkittlesDecorator instances created after
     * this call. Existing instances maintain their original name snapshot.
     * 
     * @param name The new name for skittles decoration
     */
    public static void setSkittlesName(String name) {
        SKITTLES_NAME = name;
    }
    
    /**
     * Gets the current cost of adding skittles to a cake.
     * 
     * @return The current cost of skittles decoration
     */
    public static double getSkittlesCost() {
        return SKITTLES_COST;
    }
    
    /**
     * Gets the current name of this decoration.
     * 
     * @return The current name of skittles decoration
     */
    public static String getSkittlesName() {
        return SKITTLES_NAME;
    }

}
