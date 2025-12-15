package observers;

import cakes.Cake;
import cakes.CakeType;
import factory.CakeFactory;

/**
 * Manager-facing dashboard that displays the latest sold cake type and its current count.
 * 
 * <p>This class implements the {@link OrderObserver} interface to receive notifications
 * whenever a cake order is completed. It displays the cake type that was just sold along
 * with its up-to-date total count, querying the counters maintained by the {@link CakeFactory}.
 * 
 * <p>The dashboard displays statistics in the format:
 * <pre>
 * [Cake Type Display Name] – [Count]
 * </pre>
 * 
 * <p>Example output:
 * <pre>
 * Cheese Cake – 123
 * </pre>
 * 
 * <p>This dashboard reads the count directly from the CakeFactory's type-specific counter
 * for the cake type that was just sold, ensuring that the displayed count always reflects
 * the current total number of cakes of that type that have been produced.
 * 
 * <p>This dashboard is designed to be registered with the {@link ordering.CakeOrderingSystem}
 * singleton to automatically receive updates as orders are processed.
 * 
 * @see OrderObserver
 * @see ordering.CakeOrderingSystem
 * @see factory.CakeFactory
 * @author Amer Abuyaqob
 * @version 1.0
 */
public class ManagerDashboard implements OrderObserver {
    
    /**
     * Updates the dashboard when a new cake order is completed.
     * 
     * <p>This method is called by the {@link ordering.CakeOrderingSystem} to notify
     * the dashboard of a completed order. The dashboard extracts the cake type from
     * the completed cake and displays only that cake type with its current count.
     * 
     * @param cake The completed cake order containing the cake type information
     */
    @Override
    public void update(Cake cake) {
        if (cake == null) {
            return;
        }
        
        displayLatestSale(cake);
    }
    
    /**
     * Displays the latest sold cake type with its current count.
     * 
     * <p>Extracts the cake type from the cake's base name, maps it to a CakeType enum,
     * queries the factory for the current count of that type, and displays it in the
     * format: "[Cake Type Display Name] – [Count]"
     * 
     * @param cake The completed cake order to display statistics for
     */
    private void displayLatestSale(Cake cake) {
        CakeType cakeType = getCakeTypeFromBaseName(cake.getBaseName());
        
        if (cakeType == null) {
            // Unknown cake type, skip display
            return;
        }
        
        int count = CakeFactory.getCountForType(cakeType);
        String displayName = cakeType.getDisplayName();
        
        System.out.println(displayName + " – " + count);
    }
    
    /**
     * Maps a cake base name string to its corresponding CakeType enum value.
     * 
     * <p>This method converts the base name (e.g., "Apple Cake", "Cheese Cake")
     * to the corresponding CakeType enum value by matching against the display names.
     * 
     * @param baseName The base name of the cake (e.g., "Apple Cake", "Cheese Cake", "Chocolate Cake")
     * @return The corresponding CakeType enum value, or null if the base name doesn't match any known type
     */
    private CakeType getCakeTypeFromBaseName(String baseName) {
        if (baseName == null) {
            return null;
        }
        
        // Match base name against each cake type's display name
        for (CakeType type : CakeType.values()) {
            if (type.getDisplayName().equals(baseName)) {
                return type;
            }
        }
        
        return null;
    }
}

