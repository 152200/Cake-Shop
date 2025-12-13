package src.main.java.com.cakeshop.observer;

import src.main.java.com.cakeshop.model.Order;
import src.main.java.com.cakeshop.factory.CakeType;
import src.main.java.com.cakeshop.singleton.CakeOrderingSystem;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerDashboard implements OrderObserver {
    private Map<CakeType, Integer> cakeSales;
    private Map<CakeType, Double> revenueByCake;
    private List<Order> allOrders;
    private LocalDateTime lastUpdate;

    public ManagerDashboard() {
        cakeSales = new HashMap<>();
        revenueByCake = new HashMap<>();
        allOrders = new ArrayList<>();
        lastUpdate = LocalDateTime.now();

        // Initialize counters
        for (CakeType type : CakeType.values()) {
            cakeSales.put(type, 0);
            revenueByCake.put(type, 0.0);
        }
    }

    @Override
    public void update(Order order) {
        CakeType cakeType = determineCakeType(order.getCake().getName());

        // Update sales count
        cakeSales.put(cakeType, cakeSales.get(cakeType) + 1);

        // Update revenue
        revenueByCake.put(cakeType, revenueByCake.get(cakeType) + order.getTotalPrice());

        allOrders.add(order);
        lastUpdate = LocalDateTime.now();

        // Auto-display on significant events
        if (allOrders.size() % 3 == 0) {
            displayDashboard();
        }
    }

    private CakeType determineCakeType(String cakeName) {
        if (cakeName.contains("Apple"))
            return CakeType.APPLE;
        if (cakeName.contains("Cheese"))
            return CakeType.CHEESE;
        if (cakeName.contains("Chocolate"))
            return CakeType.CHOCOLATE;
        return CakeType.APPLE;
    }

    public void displayDashboard() {
        CakeOrderingSystem orderingSystem = CakeOrderingSystem.getInstance();

        System.out.println("\n" + "=".repeat(70));
        System.out.println("MANAGER DASHBOARD - REAL-TIME ANALYTICS");
        System.out.println("=".repeat(70));

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("Last Updated: " + dtf.format(lastUpdate));
        System.out.println("Daily Theme: " + orderingSystem.getDailyTheme());
        System.out.println("Total Revenue: $" + String.format("%.2f", orderingSystem.getTotalRevenue()));
        System.out.println("Active Orders in Queue: " + orderingSystem.getQueueSize());

        System.out.println("\nCAKE SALES BREAKDOWN:");
        System.out.printf("%-25s %-15s %-15s %-15s%n",
                "Cake Type", "Quantity", "Revenue", "Avg Price");
        System.out.println("-".repeat(70));

        for (Map.Entry<CakeType, Integer> entry : cakeSales.entrySet()) {
            CakeType type = entry.getKey();
            int quantity = entry.getValue();
            double revenue = revenueByCake.get(type);
            double avgPrice = quantity > 0 ? revenue / quantity : 0;

            if (quantity > 0) {
                System.out.printf("%-25s %-15d $%-14.2f $%-14.2f%n",
                        type,
                        quantity,
                        revenue,
                        avgPrice);
            }
        }

        System.out.println("\nTOP PERFORMING CAKE:");
        CakeType topSeller = getTopSellingCake();
        System.out.println("   " + topSeller + " - " + cakeSales.get(topSeller) + " sold");

        System.out.println("\nREVENUE STATISTICS:");
        System.out.println("   Total Orders: " + allOrders.size());
        System.out.println("   Average Order Value: $" +
                String.format("%.2f", calculateAverageOrderValue()));

        System.out.println("=".repeat(70));
    }

    private CakeType getTopSellingCake() {
        return Collections.max(cakeSales.entrySet(),
                Map.Entry.comparingByValue()).getKey();
    }

    private double calculateAverageOrderValue() {
        if (allOrders.isEmpty())
            return 0.0;
        double total = allOrders.stream().mapToDouble(Order::getTotalPrice).sum();
        return total / allOrders.size();
    }

    @Override
    public String getObserverName() {
        return "ManagerDashboard";
    }
}
