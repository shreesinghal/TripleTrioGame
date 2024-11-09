package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.*;
import java.awt.*;

public class TTFrameImpl extends JFrame implements TTFrame {


  private final GridPanel gridPanel;
  private final HandPanel redHand;
  private final HandPanel blueHand;

  public TTFrameImpl(ReadOnlyTripleTrioModel model) {
    this.redHand = new HandPanel(model, model.getPlayer());
    this.blueHand = new HandPanel(model, model.getOppPlayer());
    this.gridPanel = new GridPanel(model, (int) redHand.getDimensions().getHeight());
    this.createVisual();
  }

  private void createVisual() {
    this.setTitle("Triple Trio"); //change tp say the current player
    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // allows us to close the window
    this.setSize(new Dimension((int) (gridPanel.getDimensions().getWidth() + redHand.getDimensions().getWidth() + blueHand.getDimensions().getWidth()),
            (int) (gridPanel.getDimensions().getHeight() + redHand.getDimensions().getHeight() + blueHand.getDimensions().getHeight()))); //allows us to set the size of the window
    this.setLocationRelativeTo(null); //allows us to center the window
    this.setLayout(new BorderLayout());
    this.add(gridPanel, BorderLayout.CENTER);
    redHand.setPreferredSize(redHand.getDimensions());
    blueHand.setPreferredSize(blueHand.getDimensions());
    this.add(redHand, BorderLayout.EAST);
    this.add(blueHand, BorderLayout.WEST);

    this.setResizable(true);
    this.setVisible(true);
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

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

//  @Override
//  public void mouseClick() {
//
//  }

}
