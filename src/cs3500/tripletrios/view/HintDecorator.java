package cs3500.tripletrios.view;

import java.awt.Graphics2D;

/**
 * Interface for decorating game panels with hint functionality.
 * Provides methods to draw hints on the game board and control hint visibility.
 */
public interface HintDecorator {
  /**
   * Draws hint information on the provided graphics context.
   * @param g2d Graphics2D context to draw hints on
   */
  void drawHints(Graphics2D g2d);

  /**
   * Enables or disables the hint display.
   * @param enabled true to show hints, false to hide them
   */
  void setEnabled(boolean enabled);

  /**
   * Returns whether hints are currently enabled.
   * @return true if hints are enabled, false otherwise
   */
  boolean isEnabled();
}