# Assignment 0

In this assignment, you will develop an abstract data type for a list (as a sequence).
Think about the exercise we did in class where we described the functionality of a variable.
You will apply the same line of thinking to lists for this week's assignment.
This assignment is your first experience working with an abstract data type: you are implementing required behavior, not inventing your own interface.

## Interface 1: ListADT

### Writing the Interface
Begin by thinking through the operations you want to perform on a list. In class, you will have cards, magnets and other physical objects; use those to model list behaviors and decide what operations matter (e.g., add, remove, get, set, size).
Your goal is to create a Java interface called `ListADT`.
This interface will describe the operations supported directly by a list.
You will also write Javadoc to accompany the interface.
Your interface should compile cleanly from the command line and the Javadoc should build cleanly.

Your interface should include each of the following:
* Javadoc describing the purpose of the interface. Since an interface in Java doesn't include any constructors, this Javadoc should also specify your assumptions about how a hypothetical ListADT object would be created (arguments required, initial capacity, empty list, etc). These assumptions should describe the logical initial state of the list, not a specific representation (e.g., do not assume a backing array or node structure). 
* Call signatures for all the necessary methods. Your ListADT should support at least:
    - querying size / emptiness
    - accessing elements by position
    - inserting and removing elements by position
You may include additional operations if you clearly justify them. You will want your list to be resizable (so that you can add and remove elements). 
* Javadoc for those methods describing what they do and any restrictions on their use. If a method might throw an exception under certain circumstances, the Javadoc should say so.
* You are not required to define operations involving two lists (e.g., concatenation), but you may include them if you clearly specify their behavior.

### Writing Unit Tests

Next, write a set of tests to confirm that an object conforming to this interface works correctly.
Instead of free-form text, put your tests in a markdown file named `TESTS.md`.
Use a table to make your edge cases easy to grade. Each row should describe one test.
Be sure to include boundary cases (e.g., empty list, index 0, index = size, index = size − 1).

Your tests should use the following palette of operations:
* Create an object with some set of properties (i.e., empty list, initial capacity)
* Method call with expected return value
* Method call with expected exception thrown

Since some method calls change the state of the object, your tests may require a sequence of operations to set the object up as desired.
When a test requires multiple steps, describe the setup steps in the table.



*Remember: If your code does not compile, the autograder cannot run and you may receive a zero.*
