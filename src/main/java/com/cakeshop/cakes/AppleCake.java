package src.main.java.com.cakeshop.cakes;

public class AppleCake extends Cake {
    public AppleCake() {
        super("Apple Cake", 25.99);
        this.flavorProfile = "Sweet apple cinnamon flavor";
        this.dietaryInfo = "Vegetarian";
    }

    @Override
    public String getDescription() {
        return "Delicious apple cake with cinnamon and caramel notes";
    }

    @Override
    public double getCost() {
        return basePrice;
    }

    @Override
    public String prepare() {
        return "Baking apple cake with fresh apples and spices...";
    }
}
