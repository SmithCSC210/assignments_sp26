/**
 * Interface for stack functionality
 */
public interface StackADT<T> {
    /** Add item onto stack
     * @param value element to add */
    public void push(T value);

    /** Remove top item from the stack
     * @return element at top of stack */
    public T pop();

    /** Returns top item from the stack without removing
     * @return element at top of stack */
    public T peek();

    /** Returns true if there are no elements to pop
     * @return true if stack is empty, false otherwise */
    public boolean isEmpty();

    /** Returns number of elements in the stack
     * @return number of elements */
    public int size();

}
