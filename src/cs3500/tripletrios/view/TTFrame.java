package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioControllerImpl;

public interface TTFrame {

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  void makeVisible();

  /**
   * Sets the controller to handle the clicks on view.
   * @param listener the controller
   */
  void addClickListener(TripleTrioControllerImpl listener);
}




