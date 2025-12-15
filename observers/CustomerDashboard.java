package observers;

import cakes.Cake;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer-facing dashboard that displays completed orders.
 * 
 * <p>This class implements the {@link OrderObserver} interface to receive notifications
 * whenever a cake order is completed. It maintains a list of all completed orders and
 * displays them in a customer-friendly format showing the order number and full cake
 * description.
 * 
 * <p>The dashboard displays orders using the cake's description method,
 * which includes the order number and full cake details.
 * 
 * <p>Example output:
 * <pre>
 * Order #APP-L-001: Apple Cake (Large) with Cream
 * Order #CHO-M-002: Chocolate Cake (Medium) with Chocolate Chips and Skittles
 * </pre>
 * 
 * <p>This dashboard is designed to be registered with the {@link ordering.CakeOrderingSystem}
 * singleton to automatically receive updates as orders are processed.
 * 
 * @see OrderObserver
 * @see ordering.CakeOrderingSystem
 * @author Amer Abuyaqob
 * @version 1.0
 */
public class CustomerDashboard implements OrderObserver {
    
    /**
     * List of completed cake orders displayed on this dashboard.
     */
    private List<Cake> completedOrders;
    
    /**
     * Constructs a new CustomerDashboard instance.
     * 
     * <p>Initializes an empty list to store completed orders as they are received.
     */
    public CustomerDashboard() {
        this.completedOrders = new ArrayList<>();
    }
    
    /**
     * Updates the dashboard when a new cake order is completed.
     * 
     * <p>This method is called by the {@link ordering.CakeOrderingSystem} to notify
     * the dashboard of a completed order. The order is added to the internal list and
     * displayed immediately.
     * 
     * @param cake The completed cake order to display
     */
    @Override
    public void update(Cake cake) {
        if (cake == null) {
            return;
        }
        
        completedOrders.add(cake);
        displayOrder(cake);
    }
    
    /**
     * Displays a single completed order on the dashboard.
     * 
     * <p>The display format is the cake's description, which already includes
     * the order number and full cake details (e.g., "Order #APP-L-001: Apple Cake (Large) with Cream").
     * 
     * @param cake The cake order to display
     */
    private void displayOrder(Cake cake) {
        System.out.println(cake.describe());
    }
    
    /**
     * Retrieves all completed orders currently displayed on this dashboard.
     * 
     * @return An unmodifiable list of completed cake orders
     */
    public List<Cake> getCompletedOrders() {
        return new ArrayList<>(completedOrders);
    }
    
    /**
     * Clears all orders from the dashboard.
     * 
     * <p>This method resets the dashboard to an empty state, removing all
     * previously completed orders from display.
     */
    public void clear() {
        completedOrders.clear();
    }
    
    /**
     * Gets the total number of orders displayed on this dashboard.
     * 
     * @return The number of completed orders
     */
    public int getOrderCount() {
        return completedOrders.size();
    }
}

