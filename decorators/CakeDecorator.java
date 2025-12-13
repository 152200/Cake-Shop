package decorators;

import cakes.Cake;

/**
 * Abstract base class for cake decorators following the Decorator pattern.
 * 
 * <p>This class wraps a {@link Cake} instance and delegates method calls to it,
 * allowing concrete decorators to enhance the cake's description and cost without
 * modifying the original cake object.
 * 
 * <p>Concrete decorators (e.g., ChocolateChips, Cream, Skittles) extend this
 * class and pass their decoration name and cost via the constructor. Both the
 * description grammar logic and cost calculation are centralized here.
 * 
 * <p>The decoration cost and name are stored as snapshots at construction time,
 * ensuring that each decorator object maintains the price at which it was created.
 * Changes to static fields in concrete decorator classes only affect objects
 * created after the change, preserving the original cost for previously created objects.
 * 
 * @author Amer Abuyaqob
 * @version 2.0
 */
public abstract class CakeDecorator extends Cake {
    protected Cake decoratedCake;
    protected double decorationCost;
    protected String decorationName;

    /**
     * Constructs a new CakeDecorator wrapping the given cake.
     * 
     * <p>The decoration cost and name are stored as snapshots at construction time,
     * so each object maintains the values that were current when it was created.
     * 
     * @param decoratedCake The cake instance to be decorated
     * @param decorationCost The cost of this decoration (snapshot of static field value)
     * @param decorationName The name of this decoration (snapshot of static field value)
     */
    public CakeDecorator(Cake decoratedCake, double decorationCost, String decorationName) {
        super(decoratedCake.getOrderID(), decoratedCake.getBaseName(), 
              decoratedCake.getSize(), decoratedCake.getBasePrice());
        this.decoratedCake = decoratedCake;
        this.decorationCost = decorationCost;
        this.decorationName = decorationName;
    }

    /**
     * Returns the description of the decorated cake.
     * 
     * <p>This method is centralized in the base class and handles grammar properly:
     * <ul>
     *   <li>1st decoration: "Cake with [Decoration]" (only "with")</li>
     *   <li>2nd decoration: "Cake with X and [Decoration]" (one "with", one "and")</li>
     *   <li>3rd+ decorations: "Cake with X, Y, and [Decoration]" (Oxford comma style)</li>
     * </ul>
     * 
     * <p>This method uses the decoration name stored at construction time, ensuring
     * it always reflects the name that was current when this decorator was created.
     * 
     * @return A description of the cake with all decorations
     */
    @Override
    public String describe() {
        String baseDescription = this.decoratedCake.describe();
        String decorationName = this.decorationName;  // Use snapshot stored at construction
        
        // If no "with" exists, this is the first decoration
        if (!baseDescription.contains("with")) {
            return baseDescription + " with " + decorationName;
        }
        
        // If "with" exists but no ", " or " and ", this is the second decoration
        if (!baseDescription.contains(", ") && !baseDescription.contains(" and ")) {
            return baseDescription + " and " + decorationName;
        }
        
        // If commas or "and" exist, this is third or later decoration (convert to Oxford comma style)
        int withIndex = baseDescription.indexOf(" with ");
        String beforeWith = baseDescription.substring(0, withIndex);
        String decorations = baseDescription.substring(withIndex + 6); // Everything after " with "
        
        // If it contains ", and ", replace it to add the new decoration
        if (decorations.contains(", and ")) {
            // Replace ", and X" with ", X, and [decorationName]"
            int lastCommaAndIndex = decorations.lastIndexOf(", and ");
            String beforeLast = decorations.substring(0, lastCommaAndIndex);
            String afterLast = decorations.substring(lastCommaAndIndex + 6); // Skip ", and "
            return beforeWith + " with " + beforeLast + ", " + afterLast + ", and " + decorationName;
        }
        
        // If it only has " and " (2nd decoration becoming 3rd), convert to Oxford comma style
        if (decorations.contains(" and ")) {
            // Replace "X and Y" with "X, Y, and [decorationName]"
            int andIndex = decorations.lastIndexOf(" and ");
            String first = decorations.substring(0, andIndex);
            String second = decorations.substring(andIndex + 5); // Skip " and "
            return beforeWith + " with " + first + ", " + second + ", and " + decorationName;
        }
        
        // Fallback: just add ", and [decorationName]"
        return baseDescription + ", and " + decorationName;
    }

    /**
     * Returns the total cost of the decorated cake.
     * 
     * <p>This method is centralized in the base class and adds the decoration cost
     * to the wrapped cake's total cost. It uses the decoration cost stored at construction
     * time, ensuring it always reflects the price that was current when this decorator
     * was created. This preserves the original purchase price even if static field values
     * are changed later.
     * 
     * @return The total cost including all decorations
     */
    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + this.decorationCost;  // Use snapshot stored at construction
    }
}
