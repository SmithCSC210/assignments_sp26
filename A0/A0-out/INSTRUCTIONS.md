# Assignment 0

In this assignment, you will develop an abstract data type for a list (as a collection).
Think about the exercise we did in class where we described the functionality of a variable.
You will apply the same line of thinking to lists for this week's assignment.

## Interface 1: ListADT

### Writing the Interface
Begin by thinking through the operations you want to perform on a list. In class, you will have magnets and other physical objects; use those to model list behaviors and decide what operations matter (e.g., add, remove, get, set, size).
Your goal is to create a Java interface called `ListADT`.
This interface will describe the operations supported directly by a list.
You will also write Javadoc to accompany the interface.
Your interface should compile cleanly from the command line and the Javadoc should build cleanly.

Your interface should include each of the following:
* Javadoc describing the purpose of the interface.
Since an interface in Java doesn't include any constructors, this Javadoc should specify your assumptions about how a hypothetical ListADT object would be created (arguments required, initial capacity, empty list, etc). Assume the list is resizable (so it can apply to both array-based and linked implementations).
* Call signatures for all the necessary methods
* Javadoc for those methods describing what they do and any restrictions on their use.
If a method might throw an exception under certain circumstances, the Javadoc should say so.

### Writing Unit Tests

Next, write a set of tests to confirm that an object conforming to this interface works correctly.
Instead of free-form text, put your tests in a markdown file named `TESTS.md`.
Use a table to make your edge cases easy to grade. Each row should describe one test.

Your tests should use the following palette of operations:
* Create an object with some set of properties (i.e., empty list, initial capacity)
* Method call with expected return value
* Method call with expected exception thrown

Since some method calls change the state of the object, your tests may require a sequence of operations to set the object up as desired.
When a test requires multiple steps, describe the setup steps in the table.
