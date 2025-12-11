package src.main.java.com.cakeshop.singleton;

import src.main.java.com.cakeshop.cakes.Cake;
import src.main.java.com.cakeshop.decorator.*;
import src.main.java.com.cakeshop.factory.CakeType;
import src.main.java.com.cakeshop.factory.CakeFactory;
import src.main.java.com.cakeshop.observer.*;
import src.main.java.com.cakeshop.model.Order;
import src.main.java.com.cakeshop.utils.OrderNumberGenerator;
import java.util.*;

public class CakeOrderingSystem {
    // Singleton instance
    private static CakeOrderingSystem instance;

    // Order processing components
    private List<OrderObserver> observers;
    private Map<CakeType, Integer> salesStatistics;
    private double totalRevenue;
    private Queue<Order> orderQueue;

    // Unique feature: Theme of the day
    private String dailyTheme;
    private Map<String, Double> themeDiscounts;

    private CakeOrderingSystem() {
        observers = new ArrayList<>();
        salesStatistics = new HashMap<>();
        orderQueue = new LinkedList<>();
        totalRevenue = 0.0;
        initializeDailyTheme();

        // Initialize statistics for all cake types
        for (CakeType type : CakeType.values()) {
            salesStatistics.put(type, 0);
        }
    }

    // Double-checked locking for thread safety
    public static CakeOrderingSystem getInstance() {
        if (instance == null) {
            synchronized (CakeOrderingSystem.class) {
                if (instance == null) {
                    instance = new CakeOrderingSystem();
                }
            }
        }
        return instance;
    }

    private void initializeDailyTheme() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayOfWeek) {
            case Calendar.MONDAY:
                dailyTheme = "Chocolate Lovers";
                themeDiscounts = Map.of("CHOCOLATE", 0.1); // 10% discount
                break;
            case Calendar.TUESDAY:
                dailyTheme = "Fruity Delight";
                themeDiscounts = Map.of("APPLE", 0.15, "SEASONAL_SUMMER", 0.15);
                break;
            case Calendar.WEDNESDAY:
                dailyTheme = "Cheese Celebration";
                themeDiscounts = Map.of("CHEESE", 0.2); // 20% discount
                break;
            case Calendar.THURSDAY:
                dailyTheme = "Premium Thursday";
                themeDiscounts = Map.of("SEASONAL_WINTER", 0.12, "SEASONAL_SUMMER", 0.12);
                break;
            case Calendar.FRIDAY:
                dailyTheme = "Weekend Special";
                themeDiscounts = Map.of("ALL", 0.05); // 5% on all
                break;
            case Calendar.SATURDAY:
                dailyTheme = "Family Day";
                themeDiscounts = Map.of("CHOCOLATE", 0.1, "CHEESE", 0.1);
                break;
            case Calendar.SUNDAY:
                dailyTheme = "Sweet Sunday";
                themeDiscounts = Map.of("ALL", 0.08);
                break;
            default:
                dailyTheme = "Daily Special";
                themeDiscounts = new HashMap<>();
        }
    }

    // Observer management
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Order order) {
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }

    // Core order processing
    public Order placeOrder(CakeType cakeType, List<String> decorations,
            String customerName, boolean isPriority) {

        System.out.println("\n" + "=".repeat(50));
        System.out.println("Processing new order...");
        System.out.println("Daily Theme: " + dailyTheme);

        // Create base cake
        Cake cake = CakeFactory.createCake(cakeType);

        // Apply decorations
        for (String decoration : decorations) {
            switch (decoration.toLowerCase()) {
                case "chocolate chips":
                    cake = new ChocolateChipsDecorator(cake);
                    break;
                case "cream":
                    cake = new CreamDecorator(cake);
                    break;
                case "skittles":
                    cake = new SkittlesDecorator(cake);
                    break;
            }
        }

        // Apply daily theme discount
        double finalPrice = applyThemeDiscount(cake, cakeType);

        // Generate order
        String orderNumber = OrderNumberGenerator.generateOrderNumber();
        Order order = new Order(orderNumber, cake, customerName, finalPrice, isPriority);

        // Update statistics
        updateSalesStatistics(cakeType, finalPrice);

        // Add to queue
        if (isPriority) {
            // Add to front of queue
            ((LinkedList<Order>) orderQueue).addFirst(order);
        } else {
            orderQueue.add(order);
        }

        // Process order (simulated)
        processOrder(order);

        // Notify observers
        notifyObservers(order);

        return order;
    }

    private double applyThemeDiscount(Cake cake, CakeType cakeType) {
        double originalPrice = cake.getCost();
        double discount = 0.0;

        if (themeDiscounts.containsKey("ALL")) {
            discount = themeDiscounts.get("ALL");
        } else if (themeDiscounts.containsKey(cakeType.name())) {
            discount = themeDiscounts.get(cakeType.name());
        }

        if (discount > 0) {
            double discountedPrice = originalPrice * (1 - discount);
            System.out.printf("Applied %d%% discount for %s theme%n",
                    (int) (discount * 100), dailyTheme);
            return discountedPrice;
        }

        return originalPrice;
    }

    private void updateSalesStatistics(CakeType cakeType, double price) {
        salesStatistics.put(cakeType, salesStatistics.get(cakeType) + 1);
        totalRevenue += price;
    }

    private void processOrder(Order order) {
        System.out.println("\n--- Order Processing Started ---");
        System.out.println(order.getCake().prepare());

        try {
            // Simulate processing time
            Thread.sleep(order.isPriority() ? 500 : 1000);

            order.setStatus("READY");
            System.out.println("✅ Order #" + order.getOrderNumber() + " is READY!");
            System.out.println("Customer: " + order.getCustomerName());
            System.out.println("Total: $" + String.format("%.2f", order.getTotalPrice()));

            // Remove from queue
            orderQueue.poll();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Getters
    public Map<CakeType, Integer> getSalesStatistics() {
        return Collections.unmodifiableMap(salesStatistics);
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public String getDailyTheme() {
        return dailyTheme;
    }

    public int getQueueSize() {
        return orderQueue.size();
    }

    public List<Order> getCurrentOrders() {
        return new ArrayList<>(orderQueue);
    }

    // Prevent cloning
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton cannot be cloned");
    }
}
