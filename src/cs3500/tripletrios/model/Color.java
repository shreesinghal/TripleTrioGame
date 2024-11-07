package cs3500.tripletrios.model;



/**
 * Color enum to represent two player colors.
 */
public enum Color {
    RED,
    BLUE;

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