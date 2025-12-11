package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public class SkittlesDecorator extends CakeDecorator {
    public SkittlesDecorator(Cake decoratedCake) {
        super(decoratedCake);
    }

    @Override
    public String getDescription() {
        return decoratedCake.getDescription() + ", decorated with colorful Skittles";
    }

    @Override
    public double getCost() {
        return decoratedCake.getCost() + 4.00;
    }

    @Override
    public String prepare() {
        return decoratedCake.prepare() + " Decorating with Skittles...";
    }

    @Override
    public String getName() {
        return decoratedCake.getName() + " with Skittles";
    }
}
