package src.main.java.com.cakeshop.decorator;

import src.main.java.com.cakeshop.cakes.Cake;

public abstract class CakeDecorator extends Cake {
    protected Cake decoratedCake;

    public CakeDecorator(Cake decoratedCake) {
        super(decoratedCake.getName(), decoratedCake.getBasePrice());
        this.decoratedCake = decoratedCake;
    }

    @Override
    public String getDescription() {
        return decoratedCake.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCake.getCost();
    }

    @Override
    public String prepare() {
        return decoratedCake.prepare();
    }
}
