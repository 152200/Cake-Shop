package src.main.java.com.cakeshop.observer;

import src.main.java.com.cakeshop.model.Order;

public interface OrderObserver {
    void update(Order order);

    String getObserverName();
}
