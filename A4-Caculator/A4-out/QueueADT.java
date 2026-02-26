/**
 * Interface for queue functionality
 */
public interface QueueADT<T> {
    /** Add item onto tail of queue
     * @param value element to add */
    public void enqueue(T value);

    /** Remove head item from the queue
     * @return element at head of queue */
    public T dequeue();

    /** Returns head item from the queue without removing
     * @return element at head of queue */
    public T peek();

    /** Returns true if there are no elements to remove
     * @return true if queue is empty, false otherwise */
    public boolean isEmpty();

    /** Returns number of elements in the queue
     * @return number of elements */
    public int size();

}
