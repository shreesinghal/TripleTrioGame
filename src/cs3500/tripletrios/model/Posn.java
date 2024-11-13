package cs3500.tripletrios.model;

/**
 * This class represents a position with an x and y value.
 * This can be used as a coordinate for the grid.
 */
public class Posn {
  private final int x_Coord;
  private final int y_Coord;

  public Posn(int xPos, int yPos) {
    this.x_Coord = xPos;
    this.y_Coord = yPos;
  }

  /**
   * Gives the x value of posn.
   * @return int x
   */
  public int getX() {
    return x_Coord;
  }

  /**
   * Gives the y value of posn.
   * @return int y
   */
  public int getY() {
    return y_Coord;
  }

  @Override
  public String toString() {
    return this.x_Coord + ", " + this.y_Coord;
  }

  /**
   * CHecks if the given posn is equal to the current one.
   * @param pos other posn
   * @return
   */
  @Override
  public boolean equals(Object pos) {
    Posn posn =  (Posn) pos;
    return this.x_Coord == posn.x_Coord && this.y_Coord == posn.y_Coord;
  }
}
