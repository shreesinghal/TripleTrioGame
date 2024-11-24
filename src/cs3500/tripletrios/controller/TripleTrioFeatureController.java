package cs3500.tripletrios.controller;


import cs3500.tripletrios.model.TripleTrioModel;


import java.io.IOException;

/**
 * Behaviors needed for the controller to delegate
 * between the model and the view.
 */
public interface TripleTrioFeatureController {

  /**
   * Play a new game of Triple Trio with the given configurations.
   * @param model a triple trio model
   * @param deckPath deckPath the path to the deck
   * @param gridPath gridPath the path to the grid
   * @throws IOException if something in the game is displayed wrong.
   */
  void playGame(TripleTrioModel model,
                String deckPath,
                String gridPath) throws IOException;

  /**
   * Returns if controller is human.
   * @return true if controller is human
   */
  boolean isHuman();

  /**
   * Handles an action when a player presses a grid cell.
   * @param xGridLoc the x coordinate of the click on grid
   * @param yGridLoc the y coordinate of the click on grid
   */
  void handleCellClickForGrid(int xGridLoc, int yGridLoc);
}