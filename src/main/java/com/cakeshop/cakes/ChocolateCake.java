package src.main.java.com.cakeshop.cakes;

public class ChocolateCake extends Cake {
    public ChocolateCake() {
        super("Chocolate Cake", 28.75);
        this.flavorProfile = "Rich chocolate with velvety texture";
        this.dietaryInfo = "Contains dairy, eggs";
    }

    @Override
    public String getDescription() {
        return "Decadent chocolate cake with chocolate ganache";
    }

    @Override
    public double getCost() {
        return basePrice;
    }

    @Override
    public String prepare() {
        return "Baking chocolate cake with premium cocoa...";
    }
}
