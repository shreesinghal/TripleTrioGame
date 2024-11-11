package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.*;
import java.awt.*;

/**
 * This is our view class for our GUI implementation.
 */
public class TTFrameImpl extends JFrame implements TTFrame {


  private final GridPanel gridPanel;
  private final HandPanel redHand;
  private final HandPanel blueHand;
  private final ReadOnlyTripleTrioModel model;

  public TTFrameImpl(ReadOnlyTripleTrioModel model) {
    this.model = model;
    this.redHand = new HandPanel(model, model.getPlayer());
    this.blueHand = new HandPanel(model, model.getOppPlayer());
    this.gridPanel = new GridPanel(model);
    this.createVisual();
  }

  private void createVisual() {
    this.setTitle("Current Player: " + this.model.getPlayer().getColor()); //change tp say the current player
    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // allows us to close the window
    this.setSize(new Dimension((int) (getToolkit().getScreenSize().getWidth()
      + redHand.getDimensions().getWidth()
      + blueHand.getDimensions().getWidth()),
            (int) (getToolkit().getScreenSize().getHeight()
              + redHand.getDimensions().getHeight()
              + blueHand.getDimensions().getHeight()))); //allows us to set the size of the window
    this.setLocationRelativeTo(null); //allows us to center the window
    this.setLayout(new BorderLayout());
    this.add(gridPanel, BorderLayout.CENTER);
    redHand.setPreferredSize(redHand.getDimensions());
    blueHand.setPreferredSize(blueHand.getDimensions());
    this.add(redHand, BorderLayout.LINE_END); //paintComponent called instantly
    this.add(blueHand, BorderLayout.LINE_START);

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

  @Override
  public void addClickListener(TripleTrioController listener) {
    this.blueHand.addClickListener(listener);
    this.redHand.addClickListener(listener);
    this.gridPanel.addClickListener(listener);

  }


  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
  }

}
