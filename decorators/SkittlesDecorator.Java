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
 * @version 1.0
 */
public class SkittlesDecorator extends CakeDecorator {
    
    /** The cost of adding skittles to a cake */
    private static double SKITTLES_COST = 1.5;
    
    /** The name of this decoration */
    private static String SKITTLES_NAME = "Skittles";

    /**
     * Constructs a new SkittlesDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with skittles
     */
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }
    
    /**
     * Gets the cost of adding skittles to a cake.
     * 
     * @return The current cost of skittles decoration
     */
    @Override
    protected double getDecorationCost() {
        return SKITTLES_COST;
    }
    
    /**
     * Gets the name of this decoration.
     * 
     * @return The current name of skittles decoration
     */
    @Override
    protected String getDecorationName() {
        return SKITTLES_NAME;
    }
    
    //TODO: needs some constraints
    /**
     * Sets the cost of adding skittles to a cake.
     * Changing this value affects all instances of SkittlesDecorator.
     * 
     * @param cost The new cost for skittles decoration
     */
    public static void setSkittlesCost(double cost) {
        SKITTLES_COST = cost;
    }
    
    //TODO: needs some constraints
    /**
     * Sets the name of this decoration.
     * Changing this value affects all instances of SkittlesDecorator.
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
