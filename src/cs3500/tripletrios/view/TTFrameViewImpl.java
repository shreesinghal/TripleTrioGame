package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;

/**
 * Implements the GUI view for the Triple Trios game, displaying the main game board,
 * player hands, and managing visual updates based on the current game state.
 */
public class TTFrameViewImpl extends JFrame implements TTFrame {


  private final GridPanel gridPanel;
  private final HandPanel redHand;
  private final HandPanel blueHand;
  private final ReadOnlyTripleTrioModel model;

  /**
   * Creates a frame for the game in a GUI style.
   * This uses both the Hand panel and grid panel.
   * @param model the instance of the game
   */
  public TTFrameViewImpl(ReadOnlyTripleTrioModel model) {
    this.model = model;
    this.redHand = new HandPanel(model, model.getPlayer());
    this.blueHand = new HandPanel(model, model.getOppPlayer());
    this.gridPanel = new GridPanel(model);
    this.createVisual();
  }

  private void createVisual() {
    this.setTitle("Current Player: " + this.model.getPlayer().getColor());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE); // allows us to close the window
    this.setSize(new Dimension((int) (getToolkit().getScreenSize().getWidth()
        + redHand.getPixelDimensions().getWidth()
        + blueHand.getPixelDimensions().getWidth()),
            (int) (getToolkit().getScreenSize().getHeight()
              + redHand.getPixelDimensions().getHeight()
              + blueHand.getPixelDimensions().getHeight())));

    //allows us to set the size of the window
    this.setLocationRelativeTo(null); //allows us to center the window
    this.setLayout(new BorderLayout());
    this.add(gridPanel, BorderLayout.CENTER);
    redHand.setPreferredSize(redHand.getPixelDimensions());
    blueHand.setPreferredSize(blueHand.getPixelDimensions());
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

  /**
   * Gets the handview of the player.
   * @param color red or blue
   * @return a handpanel object
   */
  @Override
  public HandPanel getHandView(CardColor color) {
    if (color == CardColor.BLUE) {
      return blueHand;
    }
    return redHand;
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   * @param listener the controller
   */
  @Override
  public void addClickListeners(TripleTrioFeatureController listener) {
    if (listener.isHuman()) {
      this.blueHand.addClickListener((TripleTrioHumanPlayerContr) listener);
      this.redHand.addClickListener((TripleTrioHumanPlayerContr) listener);
      this.gridPanel.addClickListener((TripleTrioHumanPlayerContr) listener);
    }
  }


  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    this.repaint();
    this.setTitle("Current Player: " + this.model.getPlayer().getColor());
  }

  /**
   * @param s
   */
  @Override
  public void printInvalidClickMessage(String s) {
    JOptionPane.showMessageDialog(this, s, "Invalid Click", JOptionPane.ERROR_MESSAGE);

  }


}
