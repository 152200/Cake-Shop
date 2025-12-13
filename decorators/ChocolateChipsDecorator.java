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
 * @version 1.0
 */
public class ChocolateChipsDecorator extends CakeDecorator {
    
    /** The cost of adding chocolate chips to a cake */
    private static final double CHOCOLATE_CHIPS_COST = 2.50;

    /**
     * Constructs a new ChocolateChipsDecorator wrapping the given cake.
     * 
     * @param decoratedCake The cake instance to be decorated with chocolate chips
     */
    public ChocolateChipsDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    /**
     * Returns the description of the cake with chocolate chips added.
     * 
     * <p>Handles grammar properly:
     * <ul>
     *   <li>1st decoration: "Cake with Chocolate Chips" (only "with")</li>
     *   <li>2nd decoration: "Cake with Cream and Chocolate Chips" (one "with", one "and")</li>
     *   <li>3+ decorations: "Cake with Cream, Skittles, and Chocolate Chips" (Oxford comma style)</li>
     * </ul>
     * 
     * @return A description of the cake including chocolate chips
     */
    @Override
    public String describe() {
        String baseDescription = this.decoratedCake.describe();
        
        // If no "with" exists, this is the first decoration
        if (!baseDescription.contains("with")) {
            return baseDescription + " with Chocolate Chips";
        }
        
        // If "with" exists but no ", " or " and ", this is the second decoration
        if (!baseDescription.contains(", ") && !baseDescription.contains(" and ")) {
            return baseDescription + " and Chocolate Chips";
        }
        
        // If commas or "and" exist, this is third or later decoration (convert to Oxford comma style)
        int withIndex = baseDescription.indexOf(" with ");
        String beforeWith = baseDescription.substring(0, withIndex);
        String decorations = baseDescription.substring(withIndex + 6); // Everything after " with "
        
        // If it contains ", and ", replace it to add the new decoration
        if (decorations.contains(", and ")) {
            // Replace ", and X" with ", X, and Chocolate Chips"
            int lastCommaAndIndex = decorations.lastIndexOf(", and ");
            String beforeLast = decorations.substring(0, lastCommaAndIndex);
            String afterLast = decorations.substring(lastCommaAndIndex + 6); // Skip ", and "
            return beforeWith + " with " + beforeLast + ", " + afterLast + ", and Chocolate Chips";
        }
        
        // If it only has " and " (2nd decoration becoming 3rd), convert to Oxford comma style
        if (decorations.contains(" and ")) {
            // Replace "X and Y" with "X, Y, and Chocolate Chips"
            int andIndex = decorations.lastIndexOf(" and ");
            String first = decorations.substring(0, andIndex);
            String second = decorations.substring(andIndex + 5); // Skip " and "
            return beforeWith + " with " + first + ", " + second + ", and Chocolate Chips";
        }
        
        // Fallback: just add ", and Chocolate Chips"
        return baseDescription + ", and Chocolate Chips";
    }

    /**
     * Returns the total cost of the cake including chocolate chips.
     * 
     * <p>Adds the chocolate chips cost to the wrapped cake's total cost.
     * 
     * @return The total cost including the base cake and chocolate chips
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + CHOCOLATE_CHIPS_COST;
    }
}
