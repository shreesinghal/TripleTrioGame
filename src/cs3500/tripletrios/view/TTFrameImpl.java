package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JFrame;

public class TTFrameImpl extends JFrame implements TTFrame {

  private final TTPanel panel;

  public TTFrameImpl(ReadOnlyTripleTrioModel model) {
    this.panel = new TTPanel(model);
    this.createVisual();
  }

  private void createVisual() {
    this.setTitle("Triple Trio"); //change tp say the current player
    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // allows us to close the window
    this.setSize(800, 800); //allows us to set the size of the window
    this.setLocationRelativeTo(null); //allows us to center the window
    this.add(panel);
  }

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  /**
   * Sets the controller to handle the clicks on view.
   *
   * @param listener the controller
   */
  @Override
  public void addClickListener(TripleTrioControllerImpl listener) {

  }



}
