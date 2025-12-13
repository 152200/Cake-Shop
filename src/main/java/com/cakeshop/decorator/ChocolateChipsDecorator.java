package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class ChocolateChipsDecorator extends CakeDecorator {

    public ChocolateChipsDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return this.decoratedCake.getDescription() + ", with chocolate chips";
    }

    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + 2.50;
    }

    @Override
    public String prepare() {
        return this.decoratedCake.prepare() + " Adding chocolate chips...";
    }

    @Override
    public String getName() {
        // it could be this.getName() + " with Chocolate Chips";
        return this.decoratedCake.getName() + " with Chocolate Chips";
    }
}
