package src.main.java.com.cakeshop.cakes;

public class CheeseCake extends Cake {
    public CheeseCake() {
        super("Cheese Cake", 32.50);
        this.flavorProfile = "Creamy cheese with graham cracker crust";
        this.dietaryInfo = "Contains dairy, gluten";
    }

    @Override
    public String getDescription() {
        return "Creamy New York style cheese cake";
    }

    @Override
    public double getCost() {
        return basePrice;
    }

    @Override
    public String prepare() {
        return "Preparing cheese cake with cream cheese and sour cream...";
    }
}
