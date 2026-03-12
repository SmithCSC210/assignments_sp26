/**
 *  Interface for mazes to be displayed graphically
 *
 *  @author  Nicholas R. Howe
 *  @version CSC 212, October 2021
 */
public interface DisplayableMaze {
    /** @return height of maze grid */
    public int getHeight();

    /** @return width of maze grid */
    public int getWidth();

    /**
     * @param i row index
     * @param j column index
     * @return contents of maze grid at row i, column j
     */
    public MazeContents getContents(int i, int j);

    /**
     * @param i row index
     * @param j column index
     * @return true if the maze grid is explorable at row i, column j
     */
    public boolean isExplorable(int i, int j);

    /** @return location of maze start point */
    public MazeLocation getStart();

    /** @return location of maze finish point */
    public MazeLocation getFinish();
}
