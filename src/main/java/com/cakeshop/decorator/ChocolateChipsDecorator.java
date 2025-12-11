package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class ChocolateChipsDecorator extends CakeDecorator {
    public ChocolateChipsDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return decoratedCake.getDescription() + ", with chocolate chips";
    }

    @Override
    public double getCost() {
        return decoratedCake.getCost() + 2.50;
    }

    @Override
    public String prepare() {
        return decoratedCake.prepare() + " Adding chocolate chips...";
    }

    @Override
    public String getName() {
        return decoratedCake.getName() + " with Chocolate Chips";
    }
}
