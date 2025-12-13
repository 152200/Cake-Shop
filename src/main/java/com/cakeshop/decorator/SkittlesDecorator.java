package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class SkittlesDecorator extends CakeDecorator {
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return this.decoratedCake.getDescription() + ", decorated with colorful Skittles";
    }

    @Override
    public double getCost() {
        return this.decoratedCake.getCost() + 4.00;
    }

    @Override
    public String prepare() {
        return this.decoratedCake.prepare() + " Decorating with Skittles...";
    }

    @Override
    public String getName() {
        return this.decoratedCake.getName() + " with Skittles";
    }
}
