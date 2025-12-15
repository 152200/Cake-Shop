package cakes;

/**
 * Enumeration representing the different sizes of cakes available in the Cake Shop.
 * 
 * <p>This enum provides type-safe cake size identifiers that map to their display names.
 * It replaces string-based size representation to ensure consistency and prevent
 * invalid size values throughout the system.
 * 
 * <p>Example usage:
 * <pre>
 * CakeSize size = CakeSize.LARGE;
 * String displayName = size.getDisplayName(); // Returns "Large"
 * </pre>
 * 
 * @author Amer Abuyaqob
 * @version 1.0
 */
public enum CakeSize {
    /**
     * Represents a Small-sized cake.
     */
    SMALL("Small"),
    
    /**
     * Represents a Medium-sized cake.
     */
    MEDIUM("Medium"),
    
    /**
     * Represents a Large-sized cake.
     */
    LARGE("Large");

    /**
     * The display name of the cake size.
     */
    private final String displayName;

    /**
     * Constructs a CakeSize enum value with the specified display name.
     * 
     * @param displayName The human-readable name of the cake size
     */
    CakeSize(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of this cake size.
     * 
     * <p>This method returns the human-readable name that should be used
     * when displaying the cake size to users or in descriptions.
     * 
     * @return The display name of the cake size (e.g., "Small", "Medium", "Large")
     */
    public String getDisplayName() {
        return displayName;
    }
}

