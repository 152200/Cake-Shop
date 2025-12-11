package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class CreamDecorator extends CakeDecorator {
    public CreamDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return decoratedCake.getDescription() + ", topped with whipped cream";
    }

    @Override
    public double getCost() {
        return decoratedCake.getCost() + 3.00;
    }

    @Override
    public String prepare() {
        return decoratedCake.prepare() + " Adding whipped cream...";
    }

    @Override
    public String getName() {
        return decoratedCake.getName() + " with Cream";
    }
}
