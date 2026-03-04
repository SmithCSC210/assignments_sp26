import java.io.*;
import java.util.Scanner;

class SolveMaze {

  public static Scanner readMaze(String fname){
    Scanner file = null;
    try {
      file = new Scanner(new File(fname));
    } catch (FileNotFoundException e) {
      System.err.println("Cannot locate file.");
      System.exit(-1);  
    }
    return file;
  }
  
  public static void main(String[] args) {
    Scanner file = null;
    if(args.length <= 0){
      file = readMaze("maze1");
    }
    else{
      file = readMaze(args[0]);
    }
    

    System.out.println(file.nextLine());
    
    // Maze maze = new Maze();
    // MazeViewer viewer = new MazeViewer(maze);
    // maze.initDemoMaze();
  }
}
