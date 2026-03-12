import java.awt.Color;

/**
 *  Maze Contents represents the status of a square in a maze
 *
 *  @author  Nicholas R. Howe
 *  @version CSC 212, October 2021
 */
public enum MazeContents {
  /** Wall cell (not explorable). */
  WALL (false, Color.black),
  /** Open cell (explorable). */
  OPEN (true, Color.white),
  /** Visited cell (not explorable). */
  VISITED (false, new Color(200, 255, 200)),
  /** Dead-end cell (not explorable). */
  DEAD_END (false, new Color(255, 200, 200)),
  /** Path cell (explorable). */
  PATH (true, Color.green.darker());

  /** Can we visit this square? */
  public final boolean isExplorable;

  /** How to display the square */
  public final Color color;

  /**
   * Constructor for a maze cell type.
   *
   * @param isExplorable whether the cell can be explored
   * @param color color to render in the viewer
   */
  private MazeContents(boolean isExplorable, Color color) {
    this.isExplorable = isExplorable;
    this.color = color;
  }
}
