package cs3500.tripletrios.model;

/**
 * This class represents a position with an x and y value.
 * This can be used as a coordinate for the grid.
 */
public class Posn {
  private final int x_coord;
  private final int y_coord;

  public Posn(int x_coord, int y_coord) {
    this.x_coord = x_coord;
    this.y_coord = y_coord;
  }

  /**
   * Gives the x value of posn.
   * @return int x
   */
  int getX() {
    return x_coord;
  }

  /**
   * Gives the y value of posn.
   * @return int y
   */
  int getY() {
    return y_coord;
  }
}
