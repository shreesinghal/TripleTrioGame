package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.WinningState;

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

  /**
   * Creates a popup error message to tell the user something specific.
   * @param message the message to be shown
   */
  void printInvalidClickMessage(String message);

  /**
   * Updates the display of the grid with the current cards after a player's turn.
   */

  void updateTurn();

  /**
   * Displays the popup of who wins/tie.
   * @param finalState final state of game
   */
  void displayGameOverMessage(WinningState finalState);

  /**
   * This listens for the ai olayers moves.
   * @param controller2 controller for ai player
   */
  void addAIListener(TripleTrioFeatureController controller2);
}




