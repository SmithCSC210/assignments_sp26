/**
 * Node class for a singly linked list.
 */
public class NodeSL<T> {
      /** The data at the node */
      private T data;
      
      /** Link to the next node */
      private NodeSL<T> next;

      /**
       * Constructs a node with the given data and next reference.
       *
       * @param data data to store in this node
       * @param next next node (or null)
       */
      NodeSL(T data, NodeSL<T> next) {
          this.data = data;
          this.next = next;
      }

      /** @return data field */
      public T getData() {
        return data;
      }

      /** @param data new data value */
      public void setData(T data) {
        this.data = data;
      }

      /** @return next node */
      public NodeSL<T> getNext() {
        return next;
      }

      /** @param next new next node */
      public void setNext(NodeSL<T> next) {
        this.next = next;
      }
}
