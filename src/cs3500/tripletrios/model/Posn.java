package cs3500.tripletrios.model;

/**
 * This class represents a position with an x and y value.
 */
public class Posn {
  private final int x;
  private final int y;

  public Posn(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gives the x value of posn.
   * @return int x
   */
  int getX() {
    return x;
  }

  /**
   * Gives the y value of posn.
   * @return int y
   */
  int getY() {
    return y;
  }
}
