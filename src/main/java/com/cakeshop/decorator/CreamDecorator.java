package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class CreamDecorator extends CakeDecorator {
    public CreamDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return this.decoratedCake.getDescription() + ", topped with whipped cream";
    }

    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + 3.00;
    }

    @Override
    public String prepare() {
        return this.decoratedCake.prepare() + " Adding whipped cream...";
    }

    @Override
    public String getName() {
        return this.decoratedCake.getName() + " with Cream";
    }
}
