package src.main.java.com.cakeshop.cakes;

public abstract class Cake {
    protected String name;
    protected double basePrice;
    protected String flavorProfile;
    protected String dietaryInfo;

    public Cake(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public abstract String getDescription();

    public abstract double getCost();

    public abstract String prepare();

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    // Unique feature: Cake rating system
    public String getQualityRating() {
        double price = getCost();
        if (price > 50)
            return "PREMIUM";
        else if (price > 30)
            return "DELUXE";
        else
            return "STANDARD";
    }
}