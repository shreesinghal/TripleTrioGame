package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;

/**
 * The `TTFrame` interface defines the basic requirements for any graphical frame
 * that displays the Triple Trios game, including methods for making the frame visible,
 * accessing the player's hand view, adding click listeners, and refreshing the view
 * to reflect changes in the game state.
 */
public interface TTFrame {

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  void makeVisible();

  /**
   * Gets the handview of the player based on the color given.
   *
   * @return the HandPanel for the specific player
   */
  HandPanel getHandView(CardColor color);

  GridPanel getGridPanel();

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   * @param listener the controller
   */
  void addClickListeners(TripleTrioFeatureController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  void printInvalidClickMessage(String s);

  void updateTurn();
}




