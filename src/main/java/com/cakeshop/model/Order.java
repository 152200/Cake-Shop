package src.main.java.com.cakeshop.model;

import src.main.java.com.cakeshop.cakes.Cake;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String orderNumber;
    private Cake cake;
    private String customerName;
    private LocalDateTime orderTime;
    private String status;
    private double totalPrice;
    private boolean priority;
    private String specialInstructions;

    public Order(String orderNumber, Cake cake, String customerName,
            double totalPrice, boolean priority) {
        this.orderNumber = orderNumber;
        this.cake = cake;
        this.customerName = customerName;
        this.orderTime = LocalDateTime.now();
        this.status = "PROCESSING";
        this.totalPrice = totalPrice;
        this.priority = priority;
        this.specialInstructions = "";
    }

    // Getters and Setters
    public String getOrderNumber() {
        return orderNumber;
    }

    public Cake getCake() {
        return cake;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPriority() {
        return priority;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String instructions) {
        this.specialInstructions = instructions;
    }

    public String getFormattedOrderTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return orderTime.format(formatter);
    }

    @Override
    public String toString() {
        return String.format("Order #%s: %s for %s - $%.2f [%s]",
                orderNumber, cake.getName(), customerName, totalPrice, status);
    }
}
