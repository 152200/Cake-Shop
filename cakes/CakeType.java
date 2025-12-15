package cakes;

/**
 * Enumeration representing the different types of cakes available in the Cake Shop.
 * 
 * <p>This enum provides type-safe cake type identifiers that map to their display names.
 * It is used by the factory pattern to create the appropriate concrete cake instances
 * without exposing the concrete classes to client code.
 * 
 * <p>Example usage:
 * <pre>
 * CakeType type = CakeType.CHOCOLATE;
 * String displayName = type.getDisplayName(); // Returns "Chocolate Cake"
 * </pre>
 * 
 * @author Amer Abuyaqob
 * @version 1.0
 */
public enum CakeType {
    /**
     * Represents an Apple Cake.
     */
    APPLE("Apple Cake"),
    
    /**
     * Represents a Cheese Cake.
     */
    CHEESE("Cheese Cake"),
    
    /**
     * Represents a Chocolate Cake.
     */
    CHOCOLATE("Chocolate Cake");

    /**
     * The display name of the cake type.
     */
    private final String displayName;

    /**
     * Constructs a CakeType enum value with the specified display name.
     * 
     * @param displayName The human-readable name of the cake type
     */
    CakeType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of this cake type.
     * 
     * <p>This method returns the human-readable name that should be used
     * when displaying the cake type to users or in descriptions.
     * 
     * @return The display name of the cake type (e.g., "Apple Cake", "Cheese Cake")
     */
    public String getDisplayName() {
        return displayName;
    }
}

