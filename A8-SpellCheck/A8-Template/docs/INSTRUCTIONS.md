# A8: DIY Spell Checker

The purpose of this assignment is to help you connect data structure choice to program behavior. You will build and benchmark two dictionary implementations, then choose one to use in a spell checker.

This assignment has three phases:

1. Compare an `ArrayList`-backed dictionary to a `HashSet`-backed dictionary by running the provided benchmark driver on repeated `.contains(...)` calls.
2. Build a `WordValidation` class that uses a dictionary to recognize valid words and generate near misses.
3. Build a command-line `SpellChecker` that uses `WordValidation` to check whether a word is spelled correctly according to the dictionary.

## Overview

Here, we are providing a small `Dictionary` interface along with two starter implementations:

- `ListDictionary`, backed by an `ArrayList<String>`
- `HashSetDictionary`, backed by a `HashSet<String>`

Both classes support the same operations. 

The goal of **Phase 1** is to observe how these two implementations of a set (array-based versus hash-based) perform on the `contains` operation. Then, the goal of **Phase 2** is to choose one and implement a dictionary that can check whether a word is spelled correctly and suggest possible alternatives. Finally, in **Phase 3**, you will plug your dictionary into a spellchecker that can check spelling in a text file and suggest corrections.

## Phase 1: Dictionary Benchmarking

In this phase, you will compare the behavior of two implementations of the same interface.

### Dictionary Interface

The `Dictionary` interface includes three operations:

- `add(String word)`
- `contains(String word)`
- `size()`

You have been given starter implementations for:

- `ListDictionary`
- `HashSetDictionary`

Read them carefully and make sure you understand how each class stores its data.

### Benchmarking Task

We have provided a `Timer.java` driver for this phase. Your job is to make sure that:

1. `ListDictionary` works correctly
2. `HashSetDictionary` works correctly
3. `Timer.java` runs and reports benchmark results for both implementations

`Timer.java` loads the same words into both dictionaries, performs many repeated `.contains(...)` lookups, and prints the elapsed time for each implementation.

Run:

```text
java Timer
```

and include the benchmark output in your `README`. I am mainly looking to see the output that your benchmark produced; it does not need to follow one exact format beyond what `Timer.java` prints.

### What to Notice

As you benchmark, think about these questions:

- How does `ArrayList.contains(...)` work? How does this affect runtime?
- How does `HashSet.contains(...)` work? How does this affect runtime?
- Why is the runtime of these `contains` tests important for the overall efficiency of a spellchecker?
- Which dictionary implementation will you choose to implement Phase 2?

You are asked to discuss your observations in your `README.md`.

## Phase 2: WordValidation and Near Misses

The `WordValidation` class should implement the `SpellingOperations` interface.

For this phase, you should choose which `Dictionary` implementation to use to store valid words in your spell checker and explain that choice in your `README.md`.

The `WordValidation` class is the heart of the spellchecker. It should:

- read valid words from a dictionary file such as `words.txt`
- decide whether a word is spelled correctly
- generate suggestions for misspelled words by generating any valid words that are one edit away

### Constructor

First, you'll want to write a constructor. The constructor should:

1. accept a filename containing valid words
2. open the file and read the words from it
3. store those words using your chosen `Dictionary` implementation

You can use `ReadFile.java` as a starting point for file-reading ideas, but you will probably want to adapt it for this assignment.

### `containsWord`

Your `containsWord` method should:

- accept a string query
- return `true` if the word is in the dictionary
- return `false` otherwise

Your program may ignore capitalization and punctuation. A common approach is to convert words to lower case before checking them and to strip punctuation as you read the input. [Regular expressions are a very useful tool for this!](https://stackoverflow.com/questions/18830813/how-can-i-remove-punctuation-from-input-text-in-java)

### `nearMisses`

Your `nearMisses` method should return a `Set<String>` of suggestions. Think about why a set is a good choice for this use case. Please note that `HashSet<String>` is an implementation of `Set<String>`, so it is valid for you to use this class.

To generate suggestions, consider all valid words that are exactly one edit away from the query. You should support these kinds of near misses:

1. **Deletions**: Delete one letter from the word.
   - Example: **catttle** -> **cattle**
2. **Insertions**: Insert one letter into the word at any point.
   - Example: **catle** -> **cattle**
3. **Substitutions**: Replace one character with another.
   - Example: **caxtle** -> **cattle**
4. **Transpositions**: Swap two adjacent characters.
   - Example: **cattel** -> **cattle**
5. **Splits**: Divide the word into two legal words, recorded as one string with a space in the middle.
   - Example: **cattell** -> **cat tell**

There may be more than one way to generate the same suggestion. Your returned `Set<String>` should still include each suggestion only once.

### Testing Suggestions

You will likely find it very helpful to write tests for yourself as you work, even though you are not required to submit a particular student test file for grading.

In particular, it is a good idea to check:

- the benchmark dictionaries
- dictionary loading
- `containsWord`
- each of the five near-miss categories
- a few edge cases, especially near the beginning or end of a word

As in earlier assignments, we will also run our own grading tests on your code.

## Phase 3: SpellChecker

The `WordValidation` class should not print anything directly. The `SpellChecker` class is responsible for interacting with the user.

Your program should support two modes. We are giving you starter code for the first mode, and your job is to extend it to work for the second mode as well.

For autograder compatibility, your `SpellChecker` class should include:

- a constructor `SpellChecker(String filename)` that builds a spell checker using a particular dictionary file
- a method `checkSpelling(String query)` that returns a map from a misspelled word to a set of suggestions

You may write additional helper methods however you like.

### Provided: Command-Line Argument Mode

If the user runs:

```text
java SpellChecker qest questt quest
```

the program should report on each provided word. Correct words should be identified as correct. Misspelled words should be reported together with suggestions.

We have provided starter code that loops over the words in `args` and calls `WordValidation` on each one.

Then, once you have it working for words passed in through `args`, **extend the program** so that it can also process many words read from input.

### Standard-Input File Mode

If the user runs:

```text
java SpellChecker < sonnet.txt
```

then your program should read words from `System.in`.

We have provided `sonnet.txt` and `sonnet2.txt` for you to use as test inputs while you work on this mode.

In this mode:

- correctly spelled words should produce no output.
- misspelled words should produce output.
- if the same misspelling appears multiple times, it should only be reported once.

This file mode is a required part of the assignment. However, we are grading the behavior of your program, not whether you wrote a specific helper method such as `readFile(...)`.

Think carefully about which data structures are helpful in this mode. Also, think about whether there is a way to *modularize* your code (as developed for the command-line mode) so that you can reuse parts of it here.

### Design Notes

Keep the responsibilities of the classes separate:

- `WordValidation` should focus on dictionary membership and near-miss generation
- `SpellChecker` should focus on reading input and printing user-facing results

## Submission Notes

Your `README.md` should explain:

- the benchmark output `Timer.java` reported when you ran it
- what you observed in the benchmark
- why you chose your dictionary implementation for the spell checker
- what data structure you used for near-miss suggestions, and why
- any other important design choices you made

### Acknowledgment

_The concept for this assignment is due to [David Eck](http://math.hws.edu/eck/)._
