package cs3500.tripletrios.controller;


import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;


import java.io.IOException;

/**
 * Behaviors needed for the controller to delegate
 * between the model and the view.
 */
public interface TripleTrioFeatureController {

  /**
   * Returns if controller is human.
   * @return true if controller is human
   */
  boolean isHuman();

  /**
   * Handles an action when a grid cell is clicked.
   * @param xGridLoc xGridLoc the x-coordinate of the clicked cell
   * @param yGridLoc yGridLoc the y-coordinate of the clicked cell
   */
  void handleCellClickForGrid(int xGridLoc, int yGridLoc);

  /**
   * Handles an action when a player presses a card on the hand.
   * @param i the number of the card that was clicked
   * @param color the color of the card that was clicked
   */
  void handleCellClickForHand(int i, CardColor color);
}