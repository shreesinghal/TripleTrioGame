package cs3500.threetrios.providers.view.tripletriadgui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import cs3500.threetrios.providers.controller.ViewFeatures;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;
import cs3500.threetrios.providers.view.tripletriadgui.ThreeTriosPanel;
import cs3500.threetrios.providers.view.tripletriadgui.TripleTriadView;

/**
 * Represents a graphical user interface for a game of Three Trios.
 * Consists of a main panel displaying the board and hands of each player.
 * On their turn, users can click on a card in their hand, highlighting it,
 * and play it to a cell on the board.
 */
public class ThreeTriosView extends JFrame implements TripleTriadView {
  private final ThreeTriosPanel panel;

  /**
   * Creates a graphical ThreeTriosView, with a panel created
   * displaying the state of the given game model.
   * @param model the read only version of the game model
   */
  public ThreeTriosView(ReadOnlyTripleTriad model, String title) {
    super();
    this.setTitle(title);
    this.panel = new ThreeTriosPanel(model);
    this.add(this.panel);
    this.setMinimumSize(this.panel.getPreferredSize());
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void addFeatureListener(ViewFeatures features) {
    this.panel.addFeatures(features);
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void selectedCard(int cardIdx, PlayerType playerType) {
    this.panel.selectedCard(cardIdx, playerType);
  }

  @Override
  public void advance() {
    this.panel.advance();
  }

  public void popup(String message) {
    JOptionPane.showMessageDialog(null,
            message, "Game Alert",
            JOptionPane.ERROR_MESSAGE);
  }
}
