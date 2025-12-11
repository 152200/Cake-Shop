package src.main.java.com.cakeshop.payment;

public interface PaymentStrategy {
    boolean pay(double amount);

    String getPaymentMethod();
}

// Concrete implementations
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("Processing credit card payment of $%.2f for %s%n",
                amount, cardHolder);
        // Simulate payment processing
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean pay(double amount) {
        System.out.printf("Processing PayPal payment of $%.2f to %s%n", amount, email);
        return true;
    }

    @Override
    public String getPaymentMethod() {
        return "PayPal (" + email + ")";
    }
}

class LoyaltyPointsPayment implements PaymentStrategy {
    private int points;
    private String customerId;

    public LoyaltyPointsPayment(String customerId, int points) {
        this.customerId = customerId;
        this.points = points;
    }

    @Override
    public boolean pay(double amount) {
        int pointsNeeded = (int) (amount * 100); // 100 points per dollar
        if (points >= pointsNeeded) {
            points -= pointsNeeded;
            System.out.printf("Paid $%.2f using %d loyalty points for customer %s%n",
                    amount, pointsNeeded, customerId);
            return true;
        }
        return false;
    }

    @Override
    public String getPaymentMethod() {
        return String.format("Loyalty Points (%d available)", points);
    }
}
