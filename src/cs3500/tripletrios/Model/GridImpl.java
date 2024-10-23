package cs3500.tripletrios.Model;

import java.util.Collection;
import java.util.Collections;

public class GridImpl implements Grid{

  Collection<Cells> cells;

  int row;

  int col;

  /**
   * A method to check how many cells we have in the grid
   *
   * @param row the rows specified
   * @param col the columns specified
   * @return the number of cells as an integer
   */
  @Override
  public int gridSize(int row, int col) {
    return (row*col);
  }
}
