package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.controller.TripleTrioGUIController;

public interface TTFrame {

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  void makeVisible();



  void addClickListener(TripleTrioController listener);

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();


}




