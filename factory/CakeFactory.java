package factory;

import cakes.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Simple factory class for creating cake instances in the Cake Shop ordering system.
 * 
 * <p>This factory centralizes cake creation logic and implements the Simple Factory pattern,
 * allowing client code to create cakes without directly instantiating concrete classes.
 * The factory automatically generates unique order IDs and calculates base prices based
 * on the cake type and size combination.
 * 
 * <p>The factory maintains a pricing table that maps (CakeType, CakeSize) combinations
 * to their corresponding base prices:
 * <ul>
 *   <li>Apple Cake: Small $8.00, Medium $10.00, Large $12.00</li>
 *   <li>Cheese Cake: Small $10.50, Medium $12.50, Large $15.00</li>
 *   <li>Chocolate Cake: Small $10.50, Medium $12.50, Large $15.00</li>
 * </ul>
 * 
 * <p>Order IDs are automatically generated in the format: [3 letters for cake type]-[1 letter for size]-[sequential number].
 * Examples: APP-L-001, CHE-M-002, CHO-S-003. The counter increments with each cake creation, starting from 1.
 * 
 * <p>Example usage:
 * <pre>
 * Cake appleCake = CakeFactory.createCake(CakeType.APPLE, CakeSize.MEDIUM);
 * Cake cheeseCake = CakeFactory.createCake(CakeType.CHEESE, CakeSize.LARGE);
 * </pre>
 * 
 * @author Amer Abuyaqob
 * @version 1.1
 */
public class CakeFactory {
    
    /**
     * Static counter for generating unique order IDs.
     * Starts at 1 and increments with each cake creation.
     */
    public static int orderIDCounter = 1;

    /**
     * Generates a formatted order ID in the format: [3 letters for cake type]-[1 letter for size]-[sequential number]
     * 
     * <p>Format examples:
     * <ul>
     *   <li>APP-L-001 (Apple Cake, Large, order 1)</li>
     *   <li>CHE-M-002 (Cheese Cake, Medium, order 2)</li>
     *   <li>CHO-S-003 (Chocolate Cake, Small, order 3)</li>
     * </ul>
     * 
     * @param type The cake type
     * @param size The cake size
     * @param orderNumber The sequential order number
     * @return A formatted order ID string
     */
    private static String generateOrderID(CakeType type, CakeSize size, int orderNumber) {
        // Get 3-letter code for cake type (first 3 letters, uppercase)
        String typeCode = getTypeCode(type);
        
        // Get 1-letter code for size (L, M, S)
        String sizeCode = getSizeCode(size);
        
        // Format order number with leading zeros (001, 002, etc.)
        String orderNum = String.format("%03d", orderNumber);
        
        return typeCode + "-" + sizeCode + "-" + orderNum;
    }

    /**
     * Gets the 3-letter code for a cake type.
     * 
     * @param type The cake type
     * @return 3-letter uppercase code (APP, CHE, CHO)
     */
    private static String getTypeCode(CakeType type) {
        switch (type) {
            case APPLE:
                return "APP";
            case CHEESE:
                return "CHE";
            case CHOCOLATE:
                return "CHO";
            default:
                throw new IllegalArgumentException("Unknown cake type: " + type);
        }
    }

    /**
     * Gets the 1-letter code for a cake size.
     * 
     * @param size The cake size
     * @return 1-letter code (L for Large, M for Medium, S for Small)
     */
    private static String getSizeCode(CakeSize size) {
        switch (size) {
            case SMALL:
                return "S";
            case MEDIUM:
                return "M";
            case LARGE:
                return "L";
            default:
                throw new IllegalArgumentException("Unknown cake size: " + size);
        }
    }

    /**
     * Pricing table that maps cake types to their size-based prices.
     * Structure: Map<CakeType, Map<CakeSize, Double>>
     * 
     * <p>This data structure allows for easy retrieval and modification of prices
     * for any cake type and size combination.
     */
    private static Map<CakeType, Map<CakeSize, Double>> pricingTable;

    // Static initializer to populate the pricing table with default values
    static {
        initializeDefaultPrices();
    }

    /**
     * Initializes the pricing table with default prices.
     * 
     * <p>Default pricing:
     * <ul>
     *   <li>Apple Cake: Small $8.00, Medium $10.00, Large $12.00</li>
     *   <li>Cheese Cake: Small $10.50, Medium $12.50, Large $15.00</li>
     *   <li>Chocolate Cake: Small $10.50, Medium $12.50, Large $15.00</li>
     * </ul>
     */
    private static void initializeDefaultPrices() {
        pricingTable = new HashMap<>();

        // Apple Cake prices
        Map<CakeSize, Double> applePrices = new HashMap<>();
        applePrices.put(CakeSize.SMALL, 8.00);
        applePrices.put(CakeSize.MEDIUM, 10.00);
        applePrices.put(CakeSize.LARGE, 12.00);
        pricingTable.put(CakeType.APPLE, applePrices);

        // Cheese Cake prices
        Map<CakeSize, Double> cheesePrices = new HashMap<>();
        cheesePrices.put(CakeSize.SMALL, 10.50);
        cheesePrices.put(CakeSize.MEDIUM, 12.50);
        cheesePrices.put(CakeSize.LARGE, 15.00);
        pricingTable.put(CakeType.CHEESE, cheesePrices);

        // Chocolate Cake prices
        Map<CakeSize, Double> chocolatePrices = new HashMap<>();
        chocolatePrices.put(CakeSize.SMALL, 10.50);
        chocolatePrices.put(CakeSize.MEDIUM, 12.50);
        chocolatePrices.put(CakeSize.LARGE, 15.00);
        pricingTable.put(CakeType.CHOCOLATE, chocolatePrices);
    }

    /**
     * Creates a new cake instance based on the specified type and size.
     * 
     * <p>This method automatically:
     * <ul>
     *   <li>Generates a unique order ID using the static counter</li>
     *   <li>Calculates the base price from the embedded pricing table</li>
     *   <li>Returns the appropriate concrete cake subclass</li>
     * </ul>
     * 
     * <p>The order ID counter is incremented after each cake creation to ensure
     * unique identifiers for subsequent orders.
     * 
     * @param type The type of cake to create (APPLE, CHEESE, or CHOCOLATE)
     * @param size The size of the cake (SMALL, MEDIUM, or LARGE)
     * @return A new Cake instance of the specified type and size with auto-generated order ID
     * @throws IllegalArgumentException if type or size is null
     */
    public static Cake createCake(CakeType type, CakeSize size) {
        if (type == null) {
            throw new IllegalArgumentException("Cake type cannot be null");
        }
        if (size == null) {
            throw new IllegalArgumentException("Cake size cannot be null");
        }

        // Generate order ID and increment counter
        String orderID = generateOrderID(type, size, orderIDCounter++);
        
        // Calculate base price from pricing table
        double basePrice = getBasePrice(type, size);
        
        // Create and return appropriate cake instance
        switch (type) {
            case APPLE:
                return new AppleCake(orderID, size, basePrice);
            case CHEESE:
                return new CheeseCake(orderID, size, basePrice);
            case CHOCOLATE:
                return new ChocolateCake(orderID, size, basePrice);
            default:
                throw new IllegalArgumentException("Unknown cake type: " + type);
        }
    }

    /**
     * Retrieves the base price for a given cake type and size combination.
     * 
     * <p>This method reads from the pricing table data structure. If the price
     * is not found in the table, an exception is thrown.
     * 
     * @param type The cake type
     * @param size The cake size
     * @return The base price for the specified type and size combination
     * @throws IllegalArgumentException if type or size is null, or if the price is not found
     */
    private static double getBasePrice(CakeType type, CakeSize size) {
        if (type == null) {
            throw new IllegalArgumentException("Cake type cannot be null");
        }
        if (size == null) {
            throw new IllegalArgumentException("Cake size cannot be null");
        }

        Map<CakeSize, Double> sizePrices = pricingTable.get(type);
        if (sizePrices == null) {
            throw new IllegalArgumentException("Unknown cake type: " + type);
        }

        Double price = sizePrices.get(size);
        if (price == null) {
            throw new IllegalArgumentException("Unknown size " + size + " for cake type: " + type);
        }

        return price;
    }

    /**
     * Sets the base price for a specific cake type and size combination.
     * 
     * <p>This method allows dynamic price updates at runtime. If the price
     * is negative, an exception is thrown.
     * 
     * @param type The cake type
     * @param size The cake size
     * @param price The new price to set (must be non-negative)
     * @throws IllegalArgumentException if type or size is null, or if price is negative
     */
    public static void setBasePrice(CakeType type, CakeSize size, double price) {
        if (type == null) {
            throw new IllegalArgumentException("Cake type cannot be null");
        }
        if (size == null) {
            throw new IllegalArgumentException("Cake size cannot be null");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative: " + price);
        }

        Map<CakeSize, Double> sizePrices = pricingTable.get(type);
        if (sizePrices == null) {
            throw new IllegalArgumentException("Unknown cake type: " + type);
        }

        sizePrices.put(size, price);
    }

    /**
     * Retrieves the base price for a given cake type and size combination.
     * 
     * <p>This is a public method that allows external code to query prices
     * without creating a cake instance.
     * 
     * @param type The cake type
     * @param size The cake size
     * @return The base price for the specified type and size combination
     * @throws IllegalArgumentException if type or size is null, or if the price is not found
     */
    public static double getPrice(CakeType type, CakeSize size) {
        return getBasePrice(type, size);
    }

    /**
     * Resets all prices to their default values.
     * 
     * <p>This method reinitializes the pricing table with the original default prices:
     * <ul>
     *   <li>Apple Cake: Small $8.00, Medium $10.00, Large $12.00</li>
     *   <li>Cheese Cake: Small $10.50, Medium $12.50, Large $15.00</li>
     *   <li>Chocolate Cake: Small $10.50, Medium $12.50, Large $15.00</li>
     * </ul>
     */
    public static void resetPricesToDefault() {
        initializeDefaultPrices();
    }
}

