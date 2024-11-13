package cs3500.tripletrios.model;



/**
 * Color enum to represent two player colors.
 * The colors are Red for Player Red
 * and Blue for Player Blue.
 */
public enum Color {
    RED,
    BLUE;

    /**
     * Returns the color as a Java Color object rather than
     * a Color object.
     * @return Java Color object.
     */
  public java.awt.Color getColor() {
      switch (this) {
          case RED :
              return java.awt.Color.RED;
          case BLUE :
              return java.awt.Color.BLUE;
        }

    throw new IllegalStateException("Color not found.");
    }
}