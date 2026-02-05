# Assignment 1: DynamicArray

**Key Learning Goal: how a list works internally when it is backed by an array**

For this assignment, you will write a new class called `DynamicArray` that implements the key features of a list using an array as the backend, as described in lecture.
The data structure we are creating is conceptually similar to `ArrayList`.
We will not try to match every single method from `ArrayList`, but the methods you implement will behave consistently with how `ArrayList` acts.

Your `DynamicArray` should be designed in an object-oriented style: whenever the instructions refer to “the current `DynamicArray`”, this means the particular instance (object) of the class on which a method is called.

You are expected to test your class thoroughly to ensure that all methods work correctly, including proper behavior when invalid indices are given (e.g., throwing exceptions).

## Updating Your ListADT from A0 (Required)

In A0, you designed a ListADT interface and were encouraged to make design choices and (optionally) add extra operations. For A1, we are shifting from design to implementation, so we need a standardized interface to ensure everyone’s code compiles and can be graded consistently. You are expected to remove or rename methods from your A0 interface as needed to match this standardized version (-- this doesn't mean yours aren't also good descriptions! These are just the common ways of talking about these operations.)

Before implementing DynamicArray, **update your ListADT** so that it includes exactly the following core list operations (with these names, parameter orders, and return types):

* int size()

* boolean isEmpty()

* T get(int index)

* T set(int index, T value)

* void add(int index, T value) (inserts at index; shifting happens in the implementation)

* T remove(int index)


### About “extra” operations from A0

If you added methods like contains, clear, sort, removeFirst, removeLast, getSublist, etc., remove them from the interface for A1. You are welcome to implement extra operations as additional methods in DynamicArray for your own use, but we will not grade them unless explicitly stated.

### Implementation

* Your class must have a field that stores a native Java array -- this is the actual storage that your list methods are built on top of.
* You should also have a class variable that tracks the size of the list. The logical **size** (number of elements in the list) may be smaller than the backing array’s **capacity** (its length).
* You will need at least one constructor for your class, whose job is to allocate the initial array storage space. It should take an integer length as its argument.
* It is also a good idea to write a copy constructor that takes a `DynamicArray` as its input and makes a deep copy of it, allocating new array storage and looping to copy all the values. A copy constructor is recommended but not required unless your Group 3 methods rely on it.

**Note**: There is a quirk in the way Java implements generic classes that makes it impossible to allocate array storage for the generic.
To get around this, we have provided the private utility method below. Call it when you want to allocate new array space.

~~~
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }
~~~

### Group 1 — Basic List Behavior

When we work with a list, there are some fundamental behaviors we expect it to have. Your job is to implement these behaviors in `DynamicArray` using a basetype array as backing:

* `get` should return the element stored at an index. It should also throw `IndexOutOfBoundsException` for invalid indices.

* `size` should return the number of elements stored in the `DynamicArray` (the logical size of the list)

* `toString` must be implemented. It should print the array in the exact format `[a, b, c]` (comma + single space between elements, no extra spaces after `[` or before `]`). The empty array should print as `[]`.

* `isEmpty` should return `true` when `size() == 0`.

* `set` should update the value stored at the given index and return the previous value (which will be `null` if it was previously unset).
It should throw `IndexOutOfBoundsException` for invalid indices.

### Group 2 — Adding and Removing Elements

The methods in this group modify the logical size of a list by one element at a time.
These are mutating methods because they change the state of the object they are called upon.
You will want to implement these methods from `ListADT` in your `DynamicArray` using a basetype array on the backend.

* `add` should insert an element at a provided index and shift any subsequent elements to the right. 
It should throw `IndexOutOfBoundsException` for invalid indices.
Valid indices are between 0 and size(). Adding at index `size` is the same as appending.
You will also want to overload this method for append (a special case, not part of the ListADT interface), where only the value to be added is passed. 

* `remove` removes and returns the element at an index, then shifts the subsequent elements to the left.
It should throw `IndexOutOfBoundsException` for invalid indices (think about how these differ from in `add`).

**Note**: At all times, the invariant `size ≤ capacity` must hold.
When an `add` operation would violate this invariant, you must replace the backing array with a larger one and copy existing elements over. The specific growth strategy is up to you and will not be graded. You do not need to shrink the array when performing `remove`.

## Group 3 — Whole-Array Operations (Extension)

* Group 3 is an extension. A correct, well-tested implementation of Groups 1–2 corresponds to solid mastery of the learning goals for this assignment (roughly a B+/A- range).
Completing Group 3 correctly is how you demonstrate the deeper fluency needed for an A.

Important: All Group 3 operations must be implemented in a functional style: they must not modify the current DynamicArray or any argument arrays. They must return a new DynamicArray.

The methods in this group are for combining and splitting whole arrays:

* `append` concatenates a passed `DynamicArray` to the end of the current `DynamicArray` and returns the result as a new `DynamicArray` object.

* `addAll` inserts all the elements of a passed `DynamicArray` at the specified index, returning the result as a new `DynamicArray`.

* `splitSuffix` returns the elements from a specified index and after as a new `DynamicArray`.

* `splitPrefix` returns the elements before a specified index as a new `DynamicArray`.

* `delete` removes the elements spanning from the first index up to just before the other, i.e., [fromIndex, toIndex). It returns a new DynamicArray with that range removed; the original is unchanged.

* `extract` returns a new `DynamicArray` containing the elements from one index up to just before another, [fromIndex, toIndex).

**Note**: 

* All range methods use the format inclusive to exclusive, i.e. [fromIndex, toIndex).

* Valid indices: 0 ≤ fromIndex ≤ toIndex ≤ size(). Otherwise throw `IndexOutOfBoundsException`.

## Tips

* To implement the methods in Group 3 from the ADT, think through what we discussed in class about operations on arrays -- what must happen when your modification changes the size of the list?
* Do not import any packages in your DynamicArray implementation (tests may import JUnit). Your implementations should demonstrate all steps required to implement each method.
* If you get stuck, reviewing the slides is likely to help.
* Also, feel free to discuss with classmates how these implementations should work. 

## Testing

Good testing makes the implementation much easier, because it helps you to identify and solve bugs in your code.
As you write your code (or even before!), you should create a set of tests to check that your methods work properly.
You will not be graded on test coverage or quality this week, but your tests must compile and run.
We have provided you with example tests for *append*.

To simplify grading, we request that you write your tests in a class called `DynamicArrayTest`. We have provided a starter file, which you should flesh out further.
(Note: the exception to the "no packages" rule is that you should import `Junit` for testing.)
We will evaluate your code based on private grading tests, and you will be able to see a summary of the results, but not the actual tests, in Gradescope.

### Tips for Writing Tests

#### Reminder on how to write tests

Every constructor and method should be tested.  Besides covering the expected use cases, try to think about edge cases -- the exceptions to the normal assumptions that are nevertheless still valid usage.  Additionally, if a method is supposed to throw an exception in certain circumstances, you should write a test to verify that it does.

#### RunTests Wrapper
We’ve provided a wrapper class called RunTests that makes JUnit output easier to read.
To compile and run your tests from the command line:

MacOS / Linux:
```
javac -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar *.java
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar RunTests
```

Windows (PowerShell or CMD):
```
javac -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar *.java
java  -cp .;junit-4.13.2.jar;hamcrest-core-1.3.jar RunTests
```

#### Example: Append

You'll see we provided you with tests for the `append` method.
Walking through the logic: to test the *append* operation, we begin with a few tests of its basic functionalities, varying the number of elements, using singletons, etc.
For each call, we want to check: 
* that the result is correct, and 
* that the original arrays have not been modified by the operation.

Next, we want to think of the edge cases:
* How about if you try to append an array to itself?  Since we are implementing a functional style, this should give a valid result!
* What if one or both arrays is empty (but not `null`)?
* Note: You don't need to worry about the case where either array is `null`, because that will throw a `NullPointerException` -- the appropriate response.

## Submitting

For this assignment, you should submit your work via Gradescope.  We will be setting up an autograder there that can tell you immediately whether your implementation passes our suite of tests.  More details will be posted when this is available.
