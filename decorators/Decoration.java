package decorators;

/**
 * Enumeration representing the different types of decorations available for cakes.
 * 
 * <p>This enum provides type-safe decoration identifiers that map to their corresponding
 * decorator classes. It is used by the ordering system to apply decorations to cakes
 * without exposing the concrete decorator classes to client code.
 * 
 * <p>Example usage:
 * <pre>
 * List&lt;Decoration&gt; decorations = Arrays.asList(Decoration.CREAM, Decoration.CHOCOLATE_CHIPS);
 * </pre>
 * 
 * @author Amer Abuyaqob
 * @version 1.0
 */
public enum Decoration {
    /**
     * Represents Chocolate Chips decoration.
     * Maps to {@link ChocolateChipsDecorator}.
     */
    CHOCOLATE_CHIPS,
    
    /**
     * Represents Cream decoration.
     * Maps to {@link CreamDecorator}.
     */
    CREAM,
    
    /**
     * Represents Skittles decoration.
     * Maps to {@link SkittlesDecorator}.
     */
    SKITTLES
}

