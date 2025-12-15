package observers;

import cakes.Cake;

/**
 * Observer interface for the Observer pattern implementation in the Cake Shop ordering system.
 * 
 * <p>This interface defines the contract for objects that need to be notified when a cake order
 * is completed. Implementing classes (such as CustomerDashboard and ManagerDashboard) can register
 * themselves with the {@link ordering.CakeOrderingSystem} to receive updates whenever a new cake
 * is finalized.
 * 
 * <p>The observer receives the complete {@link Cake} object, which contains all relevant information
 * including the order ID, cake description, size, and total cost. This eliminates the need for a
 * separate Order class, as the Cake object already encapsulates all necessary order details.
 * 
 * <p>Example usage:
 * <pre>
 * OrderObserver dashboard = new CustomerDashboard();
 * CakeOrderingSystem.getInstance().registerObserver(dashboard);
 * </pre>
 * 
 * @see ordering.CakeOrderingSystem
 * @see observers.CustomerDashboard
 * @see observers.ManagerDashboard
 * @author Amer Abuyaqob
 * @version 1.0
 */
public interface OrderObserver {
    
    /**
     * Called by the subject ({@link CakeOrderingSystem}) to notify this observer that a new cake order
     * has been completed.
     * 
     * <p>The observer should update its internal state or display based on the information
     * contained in the provided Cake object. The Cake object includes:
     * <ul>
     *   <li>Order ID (via {@link Cake#getOrderID()})</li>
     *   <li>Full description including decorations (via {@link Cake#describe()})</li>
     *   <li>Total cost (via {@link Cake#getCost()})</li>
     *   <li>Cake type and size information</li>
     * </ul>
     * 
     * @param cake The completed cake order that triggers this notification
     */
    void update(Cake cake);
}

