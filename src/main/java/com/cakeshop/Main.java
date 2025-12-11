package src.main.java.com.cakeshop;

import src.main.java.com.cakeshop.singleton.CakeOrderingSystem;
import src.main.java.com.cakeshop.observer.*;
import src.main.java.com.cakeshop.factory.CakeType;
import src.main.java.com.cakeshop.payment.*;
import src.main.java.com.cakeshop.utils.CakeQualityChecker;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("🍰 WELCOME TO THE UNIQUE CAKE SHOP SYSTEM 🍰");
        System.out.println("=".repeat(60));

        // Get singleton instance
        CakeOrderingSystem orderingSystem = CakeOrderingSystem.getInstance();

        // Create and register observers
        CustomerDashboard customer1Dashboard = new CustomerDashboard("John");
        CustomerDashboard customer2Dashboard = new CustomerDashboard("Sarah");
        ManagerDashboard managerDashboard = new ManagerDashboard();

        orderingSystem.registerObserver(customer1Dashboard);
        orderingSystem.registerObserver(customer2Dashboard);
        orderingSystem.registerObserver(managerDashboard);

        // Simulate orders with unique combinations
        simulateOrders(orderingSystem);

        // Display dashboards
        displayAllDashboards(customer1Dashboard, customer2Dashboard, managerDashboard);

        // Show unique features
        demonstrateUniqueFeatures(orderingSystem);
    }

    private static void simulateOrders(CakeOrderingSystem system) {
        System.out.println("\n📦 SIMULATING ORDERS...");

        // Order 1: Simple Apple Cake
        system.placeOrder(
                CakeType.APPLE,
                Arrays.asList("chocolate chips"),
                "John",
                false);

        // Order 2: Premium Cheese Cake with multiple decorations
        system.placeOrder(
                CakeType.CHEESE,
                Arrays.asList("cream", "skittles", "premium"),
                "Sarah",
                true // Priority order
        );

        // Order 3: Chocolate Cake with gold decoration
        system.placeOrder(
                CakeType.CHOCOLATE,
                Arrays.asList("gold", "cream"),
                "VIP_Client",
                true);

        // Order 5: Another customer order
        system.placeOrder(
                CakeType.CHOCOLATE,
                Arrays.asList("skittles"),
                "Mike",
                false);
    }

    private static void displayAllDashboards(CustomerDashboard c1, CustomerDashboard c2,
            ManagerDashboard manager) {

        // Wait a moment for all processing
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n\n📊 FINAL DASHBOARD STATUS");
        System.out.println("=".repeat(60));

        c1.displayDashboard();
        c2.displayDashboard();
        manager.displayDashboard();
    }

    private static void demonstrateUniqueFeatures(CakeOrderingSystem system) {
        System.out.println("\n🌟 UNIQUE FEATURES DEMONSTRATION 🌟");
        System.out.println("=".repeat(60));

        // 1. Daily Theme Feature
        System.out.println("\n1. DAILY THEME SYSTEM:");
        System.out.println("   Today's Theme: " + system.getDailyTheme());
        System.out.println("   Queue Size: " + system.getQueueSize());

        // 2. Quality Checker
        System.out.println("\n2. CAKE QUALITY CHECKER:");
        try {
            var cake = system.placeOrder(
                    CakeType.CHOCOLATE,
                    Arrays.asList("gold"),
                    "Test",
                    false);
            System.out.println(CakeQualityChecker.checkQuality(cake.getCake()));
        } catch (Exception e) {
            System.out.println("   Quality check demonstration completed.");
        }

        // 3. Payment Strategies
        System.out.println("\n3. PAYMENT STRATEGIES:");
        // PaymentStrategy creditCard = new CreditCardPayment("4111111111111111", "John Doe");
        // PaymentStrategy paypal = new PayPalPayment("john@example.com");
        // PaymentStrategy loyalty = new LoyaltyPointsPayment("JOHN001", 1500);

        System.out.println("   Available payment methods:");
        // System.out.println("   - " + creditCard.getPaymentMethod());
        // System.out.println("   - " + paypal.getPaymentMethod());
        // System.out.println("   - " + loyalty.getPaymentMethod());

        System.out.println("\n🎉 SYSTEM DEMONSTRATION COMPLETE! 🎉");
    }
}