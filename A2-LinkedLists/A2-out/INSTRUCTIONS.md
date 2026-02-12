# Assignment 2: Linked List Implementation

For this assignment you will develop your own implementation of a singly linked list (SLL). Your SLL should implement the same index-based `ListADT` you built in A0, even though those operations will be slower on a linked list. You will then add linked-list-native operations (like inserting after a node) and a copy constructor.

There are a lot of files in this assignment, so take a moment and let's walk through them. The hope is that breaking things into pieces (hence the many files) will help you to start with the basic properties of an SLL and expand outwards until you have built a fully operational implementation of this data structure.

## Files Provided

We have included the following classes that you need to reference, but DO NOT need to make changes to:
- `NodeSL.java`, the class defining a very important object: the individual nodes that make up an SLL. In the lecture slides, this was implemented as a nested class `Node` within `LinkedList`.
- `NodeBasedOps.java`, an interface for node-based (linked-list-friendly) methods you will implement.
- 1 test class, `SLLTest.java`. These are tests we are providing to help guide your SLL development. Feel free to add additional tests to help ensure your code runs as expected, but it's not specifically required.

You must also copy your `ListADT.java` from A0 into the `src` folder for this assignment. Your `SLL` class should implement both `ListADT<T>` and `NodeBasedOps<T>`.

We suggest structuring your work in phases by implementing the interfaces one at a time:
- Phase 1: implement `ListADT<T>` (index-based methods)
- Phase 2: implement `NodeBasedOps<T>` (node-based methods)
- Phase 3: implement the copy constructor

The only class you need to edit is `SLL.java`. This is where you will develop your code to implement a linked list.

### Vocabulary

- `SLL`: Singly Linked List. This is the type of linked list we have talked about in class.
- `Stub`: A short method definition that has the correct call signature and the minimal implementation required for clean compilation. When you implement an interface, you will need to make sure that every method in the interface is present in your implementation class. Otherwise, your code will fail to compile. The easiest way to do this is to write a `stub`. The stub will need to return something of the correct type. For example, a `stub` of a method that returns an integer could just `return 0;`. As you develop your code, you will replace this with your actual implementation of the method.

## Exception Policy (Use Built-In Exceptions)

When your methods need to signal errors, use the following built-in exception types:
- `IndexOutOfBoundsException`: invalid index or range
- `IllegalArgumentException`: invalid argument (including self-splice / self-insert if applicable)
- `IllegalStateException`: operation invalid in current state (e.g., remove from empty list)

## Phase 1: ListADT (Index-Based) Core

The goal of Phase 1 is to make your linked list behave like the `ListADT` interface from A0. These index-based methods will be slower on a linked list, but they provide a familiar API and help highlight the differences between arrays and linked lists. In addition, you should implement `isEmpty()` and `toString()` to make debugging easier.

### Adding Stubs

Your `SLL` class must implement ALL of the methods in `ListADT<T>` (copied from A0). If any are missing (or named incorrectly), it won't compile.

Therefore, your first step should be to write stubs for each method in the `ListADT` interface, plus stubs for `isEmpty()` and `toString()`.

**Finding an index in a linked list:** Unlike arrays, you cannot jump directly to index `i`. For any index-based method (`get`, `set`, `add`, `remove`), you should traverse from `head`, counting steps until you reach the desired index. This means these operations are `O(n)` in the list length.
Tip: Write a small helper like `nodeAt(index)` (or `getNode(index)`) to avoid repeating traversal logic in every method.

### Class Variables and Constructor

The interface does not include the class variables, so think about what properties you want your `SLL` objects to have (for example, a `head` reference, and possibly a `size` field).
**Important:** `getTail()` must be implemented by traversing the list. Do **not** add a `tail` field. We will test for a `tail` instance variable, and using one will lose points.

Next, flesh out the constructor stub to implement the creation of an empty list. An empty list will have `head` reference set to `null`.

### Displaying Lists

The method `toString` will allow you to print lists. Write a method that will assemble and return a `String` variable that contains the `data` from each node in the list in order. 

For example, suppose we have a list of three `NodeSL` objects like `C`-->`B`-->`A`. We want to print a String that reads "[C, B, A]".

To implement the general case:
* Define a variable to hold your accumulating string as you traverse the list
* Initialize your string with a bracket ("[")
* Next, traverse your list, looping through all elements except the last element and adding them to the string with trailing commas (e.g., "B,")
* Finally, add the last element along with a closing bracket ("]")

We also want to account for a special case: if the list is empty, you won't find anything to print because there won't be any nodes to traverse. Instead, you will just need to add a closing bracket to your string (to get "[]"), and then it will be ready to print.

Below is a `for` loop that will traverse the list, stopping just before the last element.  You can use it as a building block to construct your `toString` method:

`for (Node item = this.head; item.next != null; item = item.next) {
     // item is a node in the list
   }`

Once your `toString` method is completed, you will be able to display the contents of your list in the usual way:

   System.out.println(list);

### Filling in the Stubs

Next, flesh out your stubs by adding the code needed to implement these methods:
* `get`, `set`, `add`, `remove`, and `size` (from `ListADT`)
* `isEmpty` (a convenience method: think about what it means in terms of `size`)
* `toString` (for debugging)

In all cases, make sure you can handle the special case where the list is initially empty, and that index-based methods throw exceptions when given invalid indices.

### Checking Your Work

Once you have written stubs for all the required methods, your program should compile, and you can run the Phase 1 tests in `SLLTest.java`. 

**Make sure your code passes these tests before moving on to Phase 2.**

## Phase 2: Node-Based Methods (Linked-List-Friendly)

Now that your SLL works with index-based `ListADT` operations, you will add **node-based methods** that are more natural and often faster on linked lists. These methods use `NodeSL` references directly.

## Phase 2 Stubs

Here you will work through the methods outlined in `NodeBasedOps.java`.
Note: for `addAfter` and `removeAfter`, if the `here` argument is `null`, treat that as the head position (i.e., insert/remove at the head).
1. Start by adding stubs for all of the methods.
2. Implement `getHead` and `getTail` (accessors).
3. Implement `addFirst` and `addLast`.
4. Implement `removeFirst` and `removeLast`.
5. Finally implement `addAfter` and `removeAfter`.

Tackle one at a time, and try to get it passing all the relevant tests before going on to the next one. **No, really!** You want to catch any bugs in one method before going on to the next, otherwise you'll never be sure whether a problem is in the new thing you are working on or something earlier.

While working on this stage, it's pretty easy to accidentally write code that goes into an infinite loop. If your program seems frozen, **that's a sign**. While developing, you may want to include a print statement inside every loop so you can easily detect this sort of bug. Only remove it (or comment out) when you are sure that things are working properly. If you do find that your code is looping infinitely, try drawing a picture of what is happening to understand why.

## Copy Constructor (Required)

Implement a copy constructor `SLL(SLL<T> other)` that makes a **deep copy** of the list. The new list should contain the same values in the same order, but no nodes should be shared between the original and the copy.

## Phase 3: Benchmarking (Reflection Prompt)

We have provided a `Benchmark.java` file that compares **node-based** operations (`addAfter`/`removeAfter`) with **index-based** operations (`add(index, value)`/`remove(index)`) at a midpoint position. This is meant to help you see the performance impact of choosing index-based methods on a linked list.

To run it from the command line (MacOS / Linux / Windows):
```
javac *.java
java Benchmark
```

In your reflection (README), include a few sentences describing what you observed in the timings and why those results make sense for linked lists.
