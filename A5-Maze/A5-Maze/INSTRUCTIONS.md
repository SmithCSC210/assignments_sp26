# Assignment 5: Recursive Maze Solving

In this assignment, you will build a recursive maze solver in Java.
You will represent mazes using a 2D array and search for a path from `S` (start) to `F` (finish).

## Learning Goals

- Implement a class that satisfies a provided Java interface.
- Practice recursive problem solving with clear base cases.
- Parse text-file input into a machine-readable representation.
- Use recursive return values to mark a successful path.

## Files and What To Edit

You are given support code that you should not modify:

- `MazeLocation.java`
- `MazeDirection.java`
- `MazeContents.java`
- `MazeViewer.java`
- `DisplayableMaze.java`

You should focus on:

- `Maze.java`: implement `DisplayableMaze` and maze parsing/storage logic.
- `SolveMaze.java`: implement recursive maze solving logic.

## Phase 1: Build the `Maze` Representation

Your first goal is a working `Maze` class.

1. Implement the `DisplayableMaze` interface in `Maze.java`.
2. Add instance variables needed to represent the maze, including a 2D array:
   - `MazeContents[][] mazeGrid`
3. Implement required getters:
   - `getHeight()`
   - `getWidth()`
   - `getContents(int i, int j)`
   - `getStart()`
   - `getFinish()`
4. Implement `isExplorable(int i, int j)`.

A location should be explorable only if it is:

- in bounds,
- not a wall, and
- not already visited.

You can use `initDemoMaze()` in `Maze.java` while bringing up your class.

## Phase 2: Recursive Solver

Implement recursive exploration so the solver returns whether a path exists.

### Problem Statement

Given a maze, determine whether `F` can be reached from `S` by moving only one square at a time in cardinal directions (`NORTH`, `SOUTH`, `EAST`, `WEST`).

### Base Cases

1. Success case:
   - If current location is finish, mark it `MazeContents.PATH` and return `true`.
2. Failure case:
   - If current location is not explorable, return `false`.

### Recursive Step

When neither base case applies:

1. Mark current location as `MazeContents.VISITED`.
2. Recursively explore neighbors.
3. Combine results with logical OR (`||`).
4. Before returning:
   - mark current location `PATH` if any recursive call succeeds,
   - otherwise mark it `DEAD_END`.

### Output

After solving, print whether a solution was found.

### Optional Animation Delay

You may add a short sleep in recursive calls to visualize progress:

```java
try { Thread.sleep(50); } catch (InterruptedException e) {}
```

Before submission, reduce delay to less than 10 ms (or remove it) so grading runs quickly.

## Phase 3: Read Maze Files

Your program should read a maze file and encode it into `mazeGrid`.

### Running from the command line

```bash
java SolveMaze maze1
```

If no filename is provided, you may default to `maze1`.

### File format

- `#` = wall
- `.` or space = open passage
- `S` = start (exactly once)
- `F` = finish (exactly once)

Example:

```text
#########
#S..#...#
#.#.#.#.#
#.###.#.#
#.....#F#
#########
```

Mazes may vary in dimensions and may not be surrounded by walls.

## Testing Guidance

At minimum, test with:

- `maze1`
- `maze2`
- `maze3`

Your submission may be tested on additional mazes.

## Submission Checklist

Include at least:

- `Maze.java`
- `SolveMaze.java`
- `CHECKLIST.md`
- `README.md`

`CHECKLIST.md` should be filled out, and `README.md` should include your reflection and required collaboration/reference information.

`Kudos` and `Code Hygiene` items are manually graded.
