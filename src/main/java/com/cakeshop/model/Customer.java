package src.main.java.com.cakeshop.model;

import java.util.*;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String phone;
    private int loyaltyPoints;
    private List<Order> orderHistory;

    public Customer(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.loyaltyPoints = 0;
        this.orderHistory = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orderHistory.add(order);
        loyaltyPoints += (int) (order.getTotalPrice() * 10); // 10 points per dollar
    }

    public String getCustomerTier() {
        if (loyaltyPoints >= 1000)
            return "GOLD";
        else if (loyaltyPoints >= 500)
            return "SILVER";
        else
            return "BRONZE";
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s Member - %d points",
                name, id, getCustomerTier(), loyaltyPoints);
    }
}
