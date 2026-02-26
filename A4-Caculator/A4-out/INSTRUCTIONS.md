# Assignment 4:  DIY Calculator

_Note: These instructions contain formatting that does not render nicely in raw text formats (e.g., TextEdit or Notepad).  VSCode offers a markdown viewer window that will format the text for you._

## Learning Goals
By completing this assignment, you will:

* Implement abstract data types by choosing between multiple backing structures
* Enforce evaluation order using a stack
* Use queues to process input incrementally
* Detect malformed input through structural invariants

## Introduction
In this assignment, you will practice using stacks in a real parsing application: computing the result of arithmetic expressions.
To start, you will write a postfix calculator that can read an expression in postfix notation (e.g. `3 2 + 5 *`) from a queue and use a stack to compute the result.

You will develop stack and queue implementations to use as the foundation for your calculator.

## Specifications

Your first program will be able to read a postfix expression and compute the result:

	java CalculatePostfix "3 2 + 5 *"
	Answer: 25

To run the programs from the command line like this, you will need to have a class `CalculatePostfix.java` that has a `main` method (modeled on the one in `Tokenizer.java`).

A note about valid equations:

* We assume all numbers are non-negative and unary minus is not supported.
* You may assume that tokens in inputs are space-separated (as above).


## Phase 1: Stack and Queue Implementations

For this assignment, **you must implement `Stack` and `Queue` yourself** by using **either your `SLL`** *or* **your `DynamicArray`** from earlier assignments as the backing structure.

You will choose **one** of the following designs:
- **SLL-backed** stack/queue
- **DynamicArray-backed** stack/queue

Use the **most efficient methods** available for your chosen backing structure, as discussed in class.

You should **copy in your `SLL` / `DynamicArray` implementation files** as needed so the project compiles.
Do **not** use Java’s built-in `ArrayDeque`, `LinkedList`, or `Stack`/`Queue` classes.
Your code must compile without importing any classes from `java.util` related to stacks or queues.

Take a look at the `StackADT` interface (which you will implement in `Stack`) and the `QueueADT` interface (which you will implement in `Queue`).
These are **your** stack/queue ADTs — not Java’s.
Make sure you **do not import** Java’s legacy `Stack` class or `Queue` interface by mistake.
Hint: `peek()` means “look at the next item without removing it.” For a stack, that’s the top item; for a queue, that’s the front item.

## Phase 2: Tokenization

We will be reading the mathematical expressions in as a `String`.
Included, you will find code for a `Tokenizer` class that includes a `readTokens` method.
`readTokens` will parse equations provided as strings by separating numbers, operators, parentheses, etc. 
It will put these objects into a **queue**, returning a **queue** of all the individual components of the original string.
The queue allows us to operate on one component at a time.
The `Tokenizer` class includes a `main` method so that you can call it from the command line.
Note: `Tokenizer` uses your custom `Queue`, so you won’t be able to run it until you have a working `Queue` implementation.
You may want to play around with it a to make sure you understand how it works before moving on to Phase 3.

### The `instanceof` operator

You may notice that this input queue will hold data of heterogeneous types: `Double` for the numbers and `Character` for the operators.
Because `Double` and `Character` are both subclasses of the `Object` class, you can store both in the input queue, which is of type `Queue<Object>`.
At various points in this assignment, however, you'll want to differentiate the `Double`s from the operators (type `Character`).
To accomplish this, use the `instanceof` operator.
For example,  `x instanceof Character` has the value `true` if `x` is of type `Character`.
Likewise, `x instanceof Double` has the value `true` if `x` is of type `Double`.

In the methods you are implementing, your stacks will only hold one type (`Double` in `CalculatePostfix` and `Character` in the optional `CalculateInfix`).
Make sure you handle type consistently; for example, in `CalculatePostfix`, you can use a `Stack<Double>` as your stack. 
You'll just need to make sure you cast the elements to type `Double` as you add them.
As an example:
Object token = input.dequeue();

```
if (token instanceof Double) {
    Double value = (Double) token;
    stack.push(value);
   }
```

## Phase 3: Postfix Computation 
![HP "No Equals" hat](Hewlett-Packard_No_Equals_hat.jpg "Postfix Hat")

For Phase 3, you will join the "No Equal" team (see the vintage Hewlett Packard postfix-enthusiast hat in the photo above) and use `CalculatePostfix.postfixToResult` to calculate the solution to a postfix equation.
For example, "3 2 + 5 \*" corresponds to (3+2)\*5 in our standard infix notation. 

The first step in processing the input expression will be to parse the tokens and store them in a queue.  The best way to do that is just to call the method from class `Tokenizer`. (See Phase 1 above).
Once you have done that, use the algorithm below to compute the value of the postfix expression:

* Take a token one at a time from the queue and process it as follows:
  * If the token is a **number**, `push(...)` it onto the stack
  * If the token is an **operator**, `pop()` two numbers off the stack, combine them using the **operator**, and `push(...)` the result back onto the stack
* Once all the tokens have been processed in this way, the stack should contain exactly one element: the result.  Print it out.

If the stack runs out of elements needed for processing an operator, or if there are too many items on the stack at the end, then the starting expression was malformed, and you should throw an exception to report the error.
Our tests assume that you will throw an `IllegalArgumentException` in these cases (with a non-empty error message):

* You try to pop from an empty stack
* There are too many numbers remaining on the stack at the end
* You encounter an unknown operator
* You try to divide by 0

You should implement this method as a method called `postfixToResult` within the `CalculatePostfix.java` class.  We encourage you to test your methods by writing a `CalculatePostfixTest` class that thoroughly tests different kinds of expressions, including all the edge cases you can think of.
Your calculator must support: +, -, *, and /.
Make sure your postfix calculator is passing all of the tests in the `CalculatePostfixTest` class.

*Note: Remember that the first `Double` you pop will be the second number in your equation.*

## Kudos: Infix Computation
*This portion is optional enrichment and is not required for full credit.*
Handling infix expressions is more complicated because you have to deal with parentheses.
However, infix expressions can be converted (parentheses and all) into postfix ones using a single stack. 
In this stage, you will once again parse a string containing an equation using `Tokenizer.readTokens()`.
The end result will be a queue of `Doubles` and `Characters` consistent with the format of a postfix expression (e.g., you will convert `(3 + 2) * 5` to `3 2 + 5 *`).
Once you have done that, you can just call your `CalculatePostfix.postfixToResult` method you wrote in Phase 2 to do all of the actual math, and if your Phase 2 code passes all the tests, you should be able to just call the method without any changes. 

You will write a method in the `CalculateInfix` class called `infixToPostfix`.
In this stage, as soon as you read a `Double` from the input queue, you can put it directly into the output queue to send to `CalculatePostfix.postfixToResult`.
However, you will add the operators (type `Character`) to a stack and use the order of operations (`PEMDAS`) to determine how you process them.
The outline below is a simplified version (ignoring operator associativity) of the full shunting yard algorithm given on Wikipedia: [Shunting-yard algorithm](http://en.wikipedia.org/w/index.php?title=Shunting-yard_algorithm&oldid=572362024).
You may find this article helpful to give you some additional context about the steps needed.
For this assignment, you may assume all operators are left-associative.

_Note: to follow this implementation, you will need instances of both `Stack` and `Queue`._

* While there are tokens to be read:

  * Read a token.

  * If the token is a number, then add it to the output queue.

  * If the token is an operator (the "queue operator") then:

	* while there is an operator token at the top of the stack (the "stack operator"), and the stack operator has GREATER THAN OR EQUAL precedence than the queue operator,

  	* pop the stack operator off the stack and add it to the output queue;

	* when no more high-precedence stack operators remain, finally push the queue operator onto the stack.

  * If the token is a left parenthesis, then push it onto the stack.

  * If the token is a right parenthesis:
	* Until the token at the top of the stack is a left parenthesis, pop operators off the stack onto the output queue.

	* Pop the left parenthesis from the stack, but not onto the output queue.

	* If the stack runs out without finding a left parenthesis, then there are mismatched parentheses.

* When there are no more tokens to read:
  * While there are still tokens in the stack:
	* If the token on the top of the stack is a parenthesis, then there are mismatched parentheses.

	* If it is an operator, pop it onto the output queue.
* Exit.

Once again, you want to make sure you think about when you'd like this program to throw exceptions.

Once the algorithm is implemented, you should be able to send the now-populated output queue directly to your postfix-processing method  `CalculatePostfix.postfixToResult` to compute the final answer.
You don't have to write any additional functions to do math in this class, as `CalculatePostfix` should already be able to handle all the actual computation.  And you don't need to write any additional functions to read and process the input, since `Tokenizer` already handles that part.  Three function calls, and the work is done.

