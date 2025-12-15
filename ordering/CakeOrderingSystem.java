package ordering;

import cakes.Cake;
import cakes.CakeSize;
import cakes.CakeType;
import decorators.Decoration;
import decorators.ChocolateChipsDecorator;
import decorators.CreamDecorator;
import decorators.SkittlesDecorator;
import factory.CakeFactory;
import observers.OrderObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that serves as the central ordering system for the Cake Shop.
 * 
 * <p>This class implements the Singleton pattern to ensure only one instance of the
 * ordering system exists throughout the application. It orchestrates the cake ordering
 * process by:
 * <ul>
 *   <li>Creating base cakes using the {@link CakeFactory}</li>
 *   <li>Applying decorations using the Decorator pattern</li>
 *   <li>Notifying registered observers when orders are completed</li>
 * </ul>
 * 
 * <p>The class uses double-checked locking for thread-safe singleton instantiation,
 * making it safe for use in multi-threaded environments.
 * 
 * <p>Example usage:
 * <pre>
 * CakeOrderingSystem system = CakeOrderingSystem.getInstance();
 * 
 * // Register observers
 * system.registerObserver(new CustomerDashboard());
 * system.registerObserver(new ManagerDashboard());
 * 
 * // Place an order
 * List&lt;Decoration&gt; decorations = Arrays.asList(Decoration.CREAM, Decoration.CHOCOLATE_CHIPS);
 * Cake order = system.placeOrder(CakeType.CHOCOLATE, CakeSize.LARGE, decorations, "John Doe");
 * </pre>
 * 
 * @see CakeFactory
 * @see OrderObserver
 * @see Decoration
 * @author Amer Abuyaqob
 * @version 1.0
 */
public class CakeOrderingSystem {
    
    /**
     * The single instance of CakeOrderingSystem (Singleton pattern).
     */
    private static volatile CakeOrderingSystem instance;
    
    /**
     * List of observers registered to receive order completion notifications.
     */
    private List<OrderObserver> observers;
    
    /**
     * Private constructor to prevent direct instantiation (Singleton pattern).
     * 
     * <p>Initializes the observers list to an empty ArrayList. All access to this
     * class must go through {@link #getInstance()}.
     */
    private CakeOrderingSystem() {
        this.observers = new ArrayList<>();
    }
    
    /**
     * Returns the single instance of CakeOrderingSystem (Singleton pattern).
     * 
     * <p>Uses double-checked locking to ensure thread safety while minimizing
     * synchronization overhead. The volatile keyword ensures that changes to the
     * instance variable are immediately visible to all threads.
     * 
     * @return The singleton instance of CakeOrderingSystem
     */
    public static CakeOrderingSystem getInstance() {
        if (instance == null) {
            synchronized (CakeOrderingSystem.class) {
                if (instance == null) {
                    instance = new CakeOrderingSystem();
                }
            }
        }
        return instance;
    }
    
    /**
     * Registers an observer to receive notifications when orders are completed.
     * 
     * <p>Once registered, the observer will be notified via its {@link OrderObserver#update(Cake)}
     * method every time a new cake order is completed through {@link #placeOrder(CakeType, CakeSize, List, String)}.
     * 
     * <p>If the observer is already registered, this method has no effect (duplicates are not added).
     * 
     * @param observer The observer to register for order completion notifications
     * @throws IllegalArgumentException if observer is null
     */
    public void registerObserver(OrderObserver observer) {
        if (observer == null) {
            throw new IllegalArgumentException("Observer cannot be null");
        }
        
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registered: " + observer.getClass().getSimpleName());
        }
    }
    
    /**
     * Removes an observer from the notification list.
     * 
     * <p>After removal, the observer will no longer receive notifications for
     * completed orders. If the observer was not registered, this method has no effect.
     * 
     * @param observer The observer to remove from notifications
     */
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    
    /**
     * Notifies all registered observers that a cake order has been completed.
     * 
     * <p>Calls the {@link OrderObserver#update(Cake)} method on each registered observer,
     * passing the completed cake object. This method is called automatically after each
     * successful order placement.
     * 
     * @param cake The completed cake order to notify observers about
     */
    private void notifyObservers(Cake cake) {
        for (OrderObserver observer : observers) {
            observer.update(cake);
        }
    }
    
    /**
     * Places an order for a cake with optional decorations.
     * 
     * <p>This is the main entry point for creating cake orders. The method:
     * <ol>
     *   <li>Creates a base cake using the {@link CakeFactory}</li>
     *   <li>Applies decorations in the order specified in the decorations list</li>
     *   <li>Notifies all registered observers of the completed order</li>
     *   <li>Returns the fully decorated cake</li>
     * </ol>
     * 
     * <p>The decorations are applied in the order they appear in the list, with each
     * decorator wrapping the previous cake (following the Decorator pattern).
     * 
     * @param cakeType The type of cake to create (APPLE, CHEESE, or CHOCOLATE)
     * @param cakeSize The size of the cake (SMALL, MEDIUM, or LARGE)
     * @param decorations List of decorations to apply (can be empty)
     * @param customerName The name of the customer placing the order (for tracking/logging)
     * @return The completed and decorated cake ready for pickup
     * @throws IllegalArgumentException if cakeType or cakeSize is null
     */
    public Cake placeOrder(CakeType cakeType, CakeSize cakeSize, List<Decoration> decorations, String customerName) {
        if (cakeType == null) {
            throw new IllegalArgumentException("Cake type cannot be null");
        }
        if (cakeSize == null) {
            throw new IllegalArgumentException("Cake size cannot be null");
        }
        if (decorations == null) {
            throw new IllegalArgumentException("Decorations list cannot be null");
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Processing new order for: " + customerName);
        System.out.println("Cake Type: " + cakeType.getDisplayName());
        System.out.println("Cake Size: " + cakeSize.getDisplayName());
        System.out.println("Decorations: " + (decorations.isEmpty() ? "None" : decorations));
        
        // Create base cake using factory
        Cake cake = CakeFactory.createCake(cakeType, cakeSize);
        
        // Apply decorations in sequence
        for (Decoration decoration : decorations) {
            cake = applyDecoration(cake, decoration);
        }
        
        System.out.println("Order completed: " + cake.describe());
        System.out.println("Total cost: $" + String.format("%.2f", cake.getCost()));
        System.out.println("=".repeat(50));
        
        // Notify all registered observers
        notifyObservers(cake);
        
        return cake;
    }
    
    /**
     * Applies a single decoration to a cake using the appropriate decorator class.
     * 
     * <p>Maps the {@link Decoration} enum value to its corresponding decorator class
     * and wraps the cake with the decorator. This method encapsulates the mapping
     * logic between decoration types and decorator implementations.
     * 
     * @param cake The cake to decorate
     * @param decoration The type of decoration to apply
     * @return The decorated cake (wrapped with the appropriate decorator)
     * @throws IllegalArgumentException if decoration is null or unknown
     */
    private Cake applyDecoration(Cake cake, Decoration decoration) {
        if (decoration == null) {
            throw new IllegalArgumentException("Decoration cannot be null");
        }
        
        switch (decoration) {
            case CHOCOLATE_CHIPS:
                return new ChocolateChipsDecorator(cake);
            case CREAM:
                return new CreamDecorator(cake);
            case SKITTLES:
                return new SkittlesDecorator(cake);
            default:
                throw new IllegalArgumentException("Unknown decoration type: " + decoration);
        }
    }
    
    /**
     * Gets the number of observers currently registered with this system.
     * 
     * @return The number of registered observers
     */
    public int getObserverCount() {
        return observers.size();
    }
}

