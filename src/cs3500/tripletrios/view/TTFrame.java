package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.model.Color;

public interface TTFrame {

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  void makeVisible();

  /**
   * Gets the handview of the player
   *
   * @return
   */
  HandPanel getHandView(Color color);

  void addClickListeners(TripleTrioController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();


}




