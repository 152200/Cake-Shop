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
        return this.decoratedCake.getDescription();
    }

    @Override
    public double getCost() {
        return this.decoratedCake.getCost();
    }

    @Override
    public String prepare() {
        return this.decoratedCake.prepare();
    }
}
