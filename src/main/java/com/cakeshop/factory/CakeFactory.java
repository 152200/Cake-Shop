package src.main.java.com.cakeshop.factory;

import src.main.java.com.cakeshop.cakes.*;

public class CakeFactory {
    public static Cake createCake(CakeType type) {
        switch (type) {
            case APPLE:
                return new AppleCake();
            case CHEESE:
                return new CheeseCake();
            case CHOCOLATE:
                return new ChocolateCake();
            default:
                throw new IllegalArgumentException("Unknown cake type: " + type);
        }
    }
}
