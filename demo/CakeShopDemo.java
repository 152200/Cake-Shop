package demo;

import cakes.CakeSize;
import cakes.CakeType;
import decorators.Decoration;
import ordering.CakeOrderingSystem;
import observers.CustomerDashboard;
import observers.ManagerDashboard;

import java.util.Arrays;
import java.util.List;

/**
 * Demo class to test-drive the Cake Shop ordering system.
 *
 * <p>This class demonstrates the complete integration of all design patterns:
 * <ul>
 *   <li>Factory Pattern: Cake creation via CakeFactory</li>
 *   <li>Decorator Pattern: Applying decorations to cakes</li>
 *   <li>Singleton Pattern: Single CakeOrderingSystem instance</li>
 *   <li>Observer Pattern: Customer and Manager dashboards receiving notifications</li>
 * </ul>
 *
 * <p>The demo places multiple orders with various cake types, sizes, and decorations,
 * and demonstrates how both dashboards update automatically as orders are completed.
 *
 * @author Amer Abuyaqob
 * @version 1.0
 */
public class CakeShopDemo {

    /**
     * Main method to run the Cake Shop ordering system demonstration.
     *
     * <p>Places several sample orders and shows how the observer pattern
     * keeps both dashboards synchronized in real-time.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("================================================================");
        System.out.println("          Cake Shop Ordering System Demo");
        System.out.println("================================================================");
        System.out.println();

        // Get singleton instance
        CakeOrderingSystem orderingSystem = CakeOrderingSystem.getInstance();

        // Create and register dashboards
        CustomerDashboard customerDashboard = new CustomerDashboard();
        ManagerDashboard managerDashboard = new ManagerDashboard();

        orderingSystem.registerObserver(customerDashboard);
        orderingSystem.registerObserver(managerDashboard);

        System.out.println("Dashboards registered. Starting order processing...\n");

        // Order 1: Apple Cake with Cream
        System.out.println(">>> Order 1");
        List<Decoration> order1Decorations = Arrays.asList(Decoration.CREAM);
        orderingSystem.placeOrder(
            CakeType.APPLE,
            CakeSize.LARGE,
            order1Decorations,
            "Alice Smith"
        );

        // Order 2: Chocolate Cake with multiple decorations
        System.out.println("\n>>> Order 2");
        List<Decoration> order2Decorations = Arrays.asList(
            Decoration.CREAM,
            Decoration.CHOCOLATE_CHIPS,
            Decoration.SKITTLES
        );
        orderingSystem.placeOrder(
            CakeType.CHOCOLATE,
            CakeSize.MEDIUM,
            order2Decorations,
            "Bob Johnson"
        );

        // Order 3: Cheese Cake with Chocolate Chips
        System.out.println("\n>>> Order 3");
        List<Decoration> order3Decorations = Arrays.asList(Decoration.CHOCOLATE_CHIPS);
        orderingSystem.placeOrder(
            CakeType.CHEESE,
            CakeSize.SMALL,
            order3Decorations,
            "Carol Williams"
        );

        // Order 4: Apple Cake with no decorations
        System.out.println("\n>>> Order 4");
        List<Decoration> order4Decorations = Arrays.asList();
        orderingSystem.placeOrder(
            CakeType.APPLE,
            CakeSize.MEDIUM,
            order4Decorations,
            "David Brown"
        );

        // Order 5: Chocolate Cake with Skittles
        System.out.println("\n>>> Order 5");
        List<Decoration> order5Decorations = Arrays.asList(Decoration.SKITTLES);
        orderingSystem.placeOrder(
            CakeType.CHOCOLATE,
            CakeSize.LARGE,
            order5Decorations,
            "Eva Davis"
        );

        // Final summary
        System.out.println("\n" + "=".repeat(60));
        System.out.println("FINAL SUMMARY");
        System.out.println("=".repeat(60));

        System.out.println("\nTotal orders placed: " + customerDashboard.getOrderCount());

        System.out.println("================================================================");
        System.out.println("              Demo Completed Successfully!");
        System.out.println("================================================================");
    }
}

