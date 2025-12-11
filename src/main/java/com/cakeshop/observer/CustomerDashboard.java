package src.main.java.com.cakeshop.observer;

import src.main.java.com.cakeshop.model.Order;
import java.util.*;

public class CustomerDashboard implements OrderObserver {
    private String customerId;
    private List<Order> customerOrders;
    private Map<String, String> notifications;

    public CustomerDashboard(String customerId) {
        this.customerId = customerId;
        this.customerOrders = new ArrayList<>();
        this.notifications = new LinkedHashMap<>();
    }

    @Override
    public void update(Order order) {
        if (order.getCustomerName().equals(customerId) ||
                order.getCustomerName().contains(customerId)) {

            customerOrders.add(order);
            String notification = String.format(
                    "🎂 Order #%s is READY!\n" +
                            "   Cake: %s\n" +
                            "   Status: %s\n" +
                            "   Pickup Code: %s",
                    order.getOrderNumber(),
                    order.getCake().getName(),
                    order.getStatus(),
                    generatePickupCode(order));

            notifications.put(order.getOrderNumber(), notification);
            displayDashboard();
        }
    }

    private String generatePickupCode(Order order) {
        return "PICKUP-" + order.getOrderNumber().substring(4) + "-" +
                order.getCustomerName().substring(0, 3).toUpperCase();
    }

    public void displayDashboard() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📱 CUSTOMER DASHBOARD - " + customerId.toUpperCase());
        System.out.println("=".repeat(60));

        if (notifications.isEmpty()) {
            System.out.println("No orders yet. Place your first order!");
        } else {
            System.out.println("🔔 RECENT NOTIFICATIONS:");
            for (Map.Entry<String, String> entry : notifications.entrySet()) {
                System.out.println("\n" + entry.getValue());
            }

            System.out.println("\n📊 YOUR ORDER HISTORY:");
            System.out.printf("%-12s %-25s %-10s %-10s%n",
                    "Order #", "Cake", "Status", "Amount");
            System.out.println("-".repeat(60));

            for (Order order : customerOrders) {
                System.out.printf("%-12s %-25s %-10s $%-9.2f%n",
                        order.getOrderNumber(),
                        order.getCake().getName(),
                        order.getStatus(),
                        order.getTotalPrice());
            }
        }
        System.out.println("=".repeat(60));
    }

    @Override
    public String getObserverName() {
        return "CustomerDashboard-" + customerId;
    }

    public List<Order> getCustomerOrders() {
        return Collections.unmodifiableList(customerOrders);
    }
}
