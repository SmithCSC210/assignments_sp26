# CSC 210 Data Structures  
## Assignment 3: Iteration + Copy-Style Slicing

### Learning goals
By the end of this assignment, you should be able to:
- Implement iteration for two different list representations (array-backed and node-backed).
- Use a list through its iterator without relying on indexing or nodes.
- Explain (informally) how representation affects runtime cost.

---

This week you’ll keep building on your List work using both arrays and nodes.

## Part 1: Iterators

Your job for **Part 1** is to implement iterators for both structures.

- `SLL`: your iterator should traverse by following node links.
- `DynamicArray`: your iterator should traverse by advancing an index.

### Requirements
- Both classes must implement `Iterable<T>`.
- The iterator must traverse elements in list order.
- The iterator must not modify the underlying list.
- `next()` must throw `NoSuchElementException` if there are no more elements.
- Each call to `iterator()` must return a **fresh iterator** starting at the beginning.

### Testing
We will test your iterators using enhanced for-loops, like this:

```java
static int sum(Iterable<Integer> xs) {
    int total = 0;
    for (int x : xs) total += x;
    return total;
}
```

Your code should make this work for both `SLL<Integer>` and `DynamicArray<Integer>`.
Think about what methods you will need to implement to allow this sum method to work.

We will test your iterators using enhanced for-loops, so make sure they work with `Iterable<T>`.

### Iterator Design Requirements

To keep testing consistent, you must implement iteration in the **standard Java way**:

- `SLL<T>` and `DynamicArray<T>` must each implement `Iterable<T>`.
- Each class must implement `public Iterator<T> iterator()`.

**You must implement a *separate* iterator for each data structure class.**
- `SLL` iteration must be **node-based** (walk by following `.next` links).
- `DynamicArray` iteration must be **index-based** (walk by incrementing an index).

We recommend implementing the iterator as a **private inner class** inside each structure, e.g.
- `private class SLLIterator implements Iterator<T>`
- `private class DynamicArrayIterator implements Iterator<T>`

For example, you will want to update `SLL<T>` to include:
```// inside SLL<T>
public Iterator<T> iterator() {
    return new SLLIterator();
}

private class SLLIterator implements Iterator<T> {
    public boolean hasNext() { /* ... */ }
    public T next() { /* ... */ }
}
```

This approach is recommended because it allows the iterator to access private fields like `head`, `data`, and `size`.

#### What is NOT Allowed

- Do **not** implement iteration by calling `get(i)` repeatedly.  
(This can make iteration on a linked list accidentally `O(n^2)`.)
- Do **not** store iterator state (like “current node” or “current index”) as fields on the list itself.
Each call to `iterator()` must return a **fresh iterator** starting at the beginning.
- Do **not** modify the list during iteration.

#### Required iterator behavior
- `hasNext()` returns whether there is another element.
- `next()` returns the next element and advances the iterator.
- If `next()` is called when there is no next element, throw `NoSuchElementException`.

## Part 2: Split, Served Four Ways

Next we will look at a `split` operation. Your goal is to implement `split` using both **copy style** and **transfer style**, for each of `DynamicArray` and `SLL`.

### Method signatures (in both classes)
- `splitCopy(int index)`
- `splitTransfer(int index)`

### What split means
Splitting at `index` separates the list into a prefix and a tail:

- **Prefix:** elements `0 .. index-1`  
  Stays in the original list for **transfer-style**.
- **Tail:** elements `index .. size-1`  
  Returned as a new list.

### Copy-style: `splitCopy(int index)`
Copy-style returns a new list containing elements `index .. size-1`, in the same order.

- The original list must be unchanged.
- The returned list must have its own internal storage:
  - `SLL`: allocate **new nodes**
  - `DynamicArray`: allocate a **new backing array**
- You should **not clone element objects**, but you may copy references to elements.

### Transfer-style: `splitTransfer(int index)`
Transfer-style returns a new list containing elements `index .. size-1`, in the same order.

- The original list is mutated to keep only elements `0 .. index-1`.
- The returned list should get the tail using transfer semantics:
  - `SLL`: detach/relink existing nodes (no new nodes for the tail)
  - `DynamicArray`: move references, move references (you may allocate new backing arrays if needed). The key requirement is that the source loses those elements and the two results do not share backing storage.

#### Important note for arrays (to avoid aliasing bugs)
After `splitTransfer`, the two lists must be **independent**: mutating one must not affect the other (i.e., no shared backing array).

Transfer here means:
- Do not allocate new element objects (you are moving/copying references).
- Some reference copying may still be necessary because arrays are contiguous.
- The key semantic difference is: **the source loses those elements**.

### Index rules
- `index` is allowed to be between `0` and `size` (inclusive).
- `index = 0` ⇒ returned list is the whole list; original becomes empty (transfer) / unchanged (copy).
- `index = size` ⇒ returned list is empty.
- If `index` is out of range, throw `IndexOutOfBoundsException`.

---

## Part 3: Benchmarks (lightweight)

You do **not** build a benchmark harness. We provide `Timer.java`.

Your job:
1. Run the benchmark.
2. Write a brief reflection in `README.md`.

What you are benchmarking: `splitCopy` vs `splitTransfer` for both `SLL` and `DynamicArray` (as run by `Timer.java`).

### How to run
```sh
javac $(ls *.java | grep -v 'Test\.java$')
java Timer
```
The timer prints four lines per *n*. You can use this to compare the performance of the different methods as the number of elements grows.

## Contract / Policies

### Exceptions (use built-in)

* IndexOutOfBoundsException: invalid index or range
* IllegalArgumentException: invalid argument
* IllegalStateException: invalid operation for current state

### Method behavior

* Non-transfer operations should be functional (return a new structure; inputs unchanged).
* Methods with Transfer in the name are expected to mutate the source structure.

### Implementation notes

* Linked lists should use node-based operations (no converting to arrays).

## Files to Submit

* SLL.java
* DynamicArray.java
* ListADT.java (if your implementation requires it)
* Any iterator classes you implement
* README.md (reflection)
* CHECKLIST.md
