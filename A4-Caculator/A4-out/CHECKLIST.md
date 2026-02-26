# CSC 210 Data Structures
## Assignment 4 Checklist

Listed below are various aspects of the assignment.  When you turn in
your work, please indicate the status of each item

- YES: indicates that the item is fully complete
- NO: indicates that the item is not attempted
- PART: indicates that the item is attempted but not fully complete

Note: this checklist covers only the coding part of the assignment (80%).
You should also make sure that your logic and ideas are clearly expressed to fulfill the prototyping requirement (20%).


## Grade-ability Check
Please confirm the following minimum criteria are met:

_____ Program compiles without errors 

_____ All required files included with submission (including basic readme info and completed checklist file) 

_____ README.md contains answers to any questions and your reflection on the assignment 

**Assignments that do not meet the above criteria cannot be graded**


## Coding Points:

_____ 1 pts: Code implements QueueADT<T> and StackADT<T> exactly

### Queue Implementation

_____ 1 pts: enqueue(x) increases size by 1

_____ 1 pts: dequeue() returns elements in first-in-first-out order

_____ 1 pts: peek() returns next element without removing it

_____ 1 pts: isEmpty() and size() return correct values

_____ 1 pts: dequeue() on empty throws required IllegalArgumentException

_____ 2 pts: Uses appropriate backing operations (no unnecessary O(n) behavior)

### Stack Implementation

_____ 1 pts: push(x) increases size by 1

_____ 1 pts: pop() returns elements in last-in-first-out order

_____ 1 pts: peek() returns next element without removing it

_____ 1 pts: isEmpty() and size() return correct values

_____ 1 pts: pop() on empty throws IllegalArgumentException

_____ 2 pts: Uses appropriate backing operations (no unnecessary O(n) behavior)

### PostFix

_____ 1 pts: Pushes numeric tokens onto the stack

_____ 2 pts: Processes operators by popping & combining numbers

_____ 2 pts: Produces correct answer to any valid postfix expression

_____ 1 pts: Throws an exception with an informative error message in case of malformed expressions


## Code Hygiene (4 pts):

____ 1 pt: No copy/paste near-duplicate code blocks for the same behavior (reusing your code is better for everyone!)

_____ 1 pt: Common logic is factored into helpers 

_____ 1 pt: Methods are short enough to read (no 100-line monster methods unless justified)

_____ 1 pt: Names communicate intent (especially for helper methods)

### Kudos:
_____ 0 pt: Evaluation of InFix expressions is attempted with the Shunting Yard Algorithm (code compiles and shows meaningful progress)

## General Items (6 pts):

_____ 1 pt: Student-written code compiles without warnings that indicate correctness problems

_____ 2 pts: Student-provided code runs and executes without unexpected crashing

_____ 2 pt: Javadoc builds without errors/warnings

_____ 1 pt: Indentation and other style norms are followed
