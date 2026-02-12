public interface NodeBasedOps<T> {
  /** 
   *  Accessor for head node
   *  @return the head node
   */
  public NodeSL<T> getHead();
  
  /** 
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeSL<T> getTail();

  /** 
   *  Inserts the given item at the head of the list
   *  @param v item to insert 
   */
  public void addFirst(T v);

  /** 
   *  Inserts the given item at the tail of the list
   *  @param v item to insert 
   */
  public void addLast(T v);

  /** 
   *  Removes the given item from the head of the list
   *  @return v item removed
   */
  public T removeFirst();

  /** 
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast();

  /** 
   *  Inserts the given item after the specified node.
   *  If here is null, insert at the head.
   *  @param here node to insert after
   *  @param v item to insert 
   */
  public void addAfter(NodeSL<T> here, T v);

  /** 
   *  Removes the node after the given position.
   *  If here is null, remove the head node.
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T removeAfter(NodeSL<T> here);
}
