package src.test.java.com.cakeshop;

import src.main.java.com.cakeshop.singleton.CakeOrderingSystem;
import src.main.java.com.cakeshop.factory.CakeType;
import src.main.java.com.cakeshop.observer.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class CakeShopTest {

    private CakeOrderingSystem orderingSystem;

    @BeforeEach
    void setUp() {
        orderingSystem = CakeOrderingSystem.getInstance();
    }

    @Test
    void testSingleton() {
        CakeOrderingSystem anotherInstance = CakeOrderingSystem.getInstance();
        assertSame(orderingSystem, anotherInstance, "Should return the same instance");
    }

    @Test
    void testFactoryCreatesCorrectCake() {
        var appleCake = com.cakeshop.factory.CakeFactory.createCake(CakeType.APPLE);
        assertEquals("Apple Cake", appleCake.getName());
        assertTrue(appleCake.getDescription().contains("apple"));
    }

    @Test
    void testDecoratorAddsFeatures() {
        var baseCake = com.cakeshop.factory.CakeFactory.createCake(CakeType.CHOCOLATE);
        var decorated = new com.cakeshop.decorator.ChocolateChipsDecorator(baseCake);

        assertTrue(decorated.getName().contains("Chocolate Chips"));
        assertTrue(decorated.getCost() > baseCake.getCost());
    }

    @Test
    void testOrderProcessing() {
        var order = orderingSystem.placeOrder(
                CakeType.CHEESE,
                Arrays.asList("cream"),
                "TestCustomer",
                false);

        assertNotNull(order);
        assertEquals("TestCustomer", order.getCustomerName());
        assertTrue(order.getTotalPrice() > 0);
    }

    @Test
    void testObserverRegistration() {
        CustomerDashboard dashboard = new CustomerDashboard("Test");
        orderingSystem.registerObserver(dashboard);

        // Should not throw exception
        assertDoesNotThrow(() -> {
            orderingSystem.placeOrder(
                    CakeType.APPLE,
                    Collections.emptyList(),
                    "Test",
                    false);
        });
    }

    @Test
    void testMultipleDecorators() {
        var baseCake = com.cakeshop.factory.CakeFactory.createCake(CakeType.CHEESE);
        var withCream = new com.cakeshop.decorator.CreamDecorator(baseCake);
        var withSkittles = new com.cakeshop.decorator.SkittlesDecorator(withCream);

        String description = withSkittles.getDescription();
        assertTrue(description.contains("cream"));
        assertTrue(description.contains("Skittles"));
        assertTrue(withSkittles.getCost() > baseCake.getCost());
    }

    @Test
    void testPriorityOrder() {
        var order1 = orderingSystem.placeOrder(
                CakeType.APPLE,
                Collections.emptyList(),
                "Customer1",
                false);

        var order2 = orderingSystem.placeOrder(
                CakeType.CHOCOLATE,
                Collections.emptyList(),
                "Customer2",
                true // Priority
        );

        // In our system, priority orders might be processed differently
        // This test verifies the system handles both types
        assertNotNull(order1);
        assertNotNull(order2);
    }

    @Test
    void testSalesStatistics() {
        // Clear previous orders
        orderingSystem = CakeOrderingSystem.getInstance();

        orderingSystem.placeOrder(CakeType.APPLE, Collections.emptyList(), "Test", false);
        orderingSystem.placeOrder(CakeType.CHEESE, Collections.emptyList(), "Test", false);
        orderingSystem.placeOrder(CakeType.APPLE, Collections.emptyList(), "Test", false);

        var stats = orderingSystem.getSalesStatistics();
        assertTrue(stats.get(CakeType.APPLE) >= 2);
        assertTrue(stats.get(CakeType.CHEESE) >= 1);
    }

    @AfterEach
    void tearDown() {
        // Reset singleton for testing (in real scenario, you wouldn't do this)
        // This is just for test isolation
        try {
            var field = CakeOrderingSystem.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (Exception e) {
            // Ignore
        }
    }
}
