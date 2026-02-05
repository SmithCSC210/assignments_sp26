/**
 * TODO: Describe what this class represents and how it works at a high level
 *
 * @param <T> TODO: Describe what type of elements this class can store
 * @author TODO
 */
public class DynamicArray<T> {

    /**
     * Creates a new generic array of the given capacity.
     * <p>
     * Java does not allow direct creation of generic arrays. This helper method
     * safely encapsulates the required cast and suppresses the expected unchecked
     * cast warning.
     *
     * @param capacity the desired length of the array
     * @return a new array of type T[] with the given capacity
     */
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }
}