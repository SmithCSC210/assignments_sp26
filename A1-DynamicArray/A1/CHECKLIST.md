# Assignment 1 Checklist | CSC 210

Listed below are various aspects of the assignment.  When you turn in
your work, please indicate the status of each item

- YES: indicates that the item is fully complete
- NO: indicates that the item is not attempted
- PART: indicates that the item is attempted but not fully complete

## Grade-ability Check
Please confirm the following minimum criteria are met:

_____ Program compiles without errors 

_____ All required files included with submission (including basic readme info and completed checklist file) 

_____ README.md contains answers to any questions and your reflection on the assignment 

**Assignments that do not meet the above criteria cannot be graded**

## Coding Points (13 pts):

_____ 1 pt: ListADT (from A0) included and includes all specified methods

_____ 1 pt: DynamicArray method call signatures correctly implement ListADT

_____ 1 pt: Uses a backing array (T[] data) + a size field (int size)

_____ 1 pt: Maintains invariant: 0 ≤ size ≤ data.length

_____ 1 pt: Logical index i maps to backing array index i for 0 ≤ i < size and items beyond size are not incorporated into operations

_____ 1 pt: get(i) returns element at logical index i 

_____ 1 pt: set(i,x) updates element at logical index i

_____ 1 pt: add(i,x) inserts at logical index i (shifts right)

_____ 1 pt: remove(i) removes logical index i (shifts left)

_____ 1 pt: Bounds errors throw an appropriate unchecked exception (e.g., IndexOutOfBoundsException)

_____ 1 pt: No checked exceptions used for normal ADT misuse

_____ 1 pt: Adds that would make size > data.length trigger a resize

_____ 1 pt: Resize correctly allocates a new array and copies existing elements in the correct order

## Code Hygiene (4 pts):

____ 1 pt: No copy/paste near-duplicate code blocks for the same behavior (reusing your code is better for everyone!)

_____ 1 pt: Common logic is factored into helpers (e.g., checkIndex, resizeIfNeeded, shiftLeft/Right)

_____ 1 pt: Methods are short enough to read (no 100-line monster methods unless justified)

_____ 1 pt: Names communicate intent (especially for helper methods)


## General Items (6 pts):

_____ 1 pt: Student-written code compiles without warnings that indicate correctness problems

_____ 2 pts: Student-provided code runs and executes without unexpected crashing

_____ 2 pt: Javadoc builds without errors/warnings

_____ 1 pt: Indentation and other style norms are followed
