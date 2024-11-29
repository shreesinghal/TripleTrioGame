package cs3500.threetrios.providers.view.tripletriadgui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import cs3500.threetrios.providers.controller.ViewFeatures;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;
import cs3500.threetrios.providers.view.tripletriadgui.components.BoardPanel;
import cs3500.threetrios.providers.view.tripletriadgui.components.HandPanel;
import cs3500.threetrios.providers.view.tripletriadgui.TripleTriadPanel;

/**
 * Container Panel for components in Three Trios Game.
 * Establishes size of view and sub-panels sizes are dictated
 * by this size to ensure proper fitting.
 */
public class ThreeTriosPanel extends JPanel implements view.tripletriadgui.TripleTriadPanel {
  private final BoardPanel boardPanel;
  private final HandPanel handPanelA;
  private final HandPanel handPanelB;
  private final int preferredWidth = 800;
  private final int preferredHeight = 500;

  /**
   * Creates a panel with the hand and board components.
   * Displays the game state based off the given model.
   * @param model the game model
   * @throws IllegalArgumentException if model is null
   */
  public ThreeTriosPanel(ReadOnlyTripleTriad model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    this.setLayout(new BorderLayout());
    double preferredBoardPercentage = 0.7;
    this.boardPanel = new BoardPanel(
            model,
            (int) (this.preferredWidth * preferredBoardPercentage),
            this.preferredHeight);

    double preferredHandPercentage = (1 - preferredBoardPercentage) / 2;
    this.handPanelA = new HandPanel(model, PlayerType.PLAYER_A,
                    (int) (this.preferredWidth * preferredHandPercentage),
                    this.preferredHeight);

    this.handPanelB = new HandPanel(model, PlayerType.PLAYER_B,
                    (int) (this.preferredWidth * preferredHandPercentage),
                    this.preferredHeight);

    // Add panels to container
    this.add(this.handPanelA, BorderLayout.WEST);
    this.add(this.boardPanel, BorderLayout.CENTER);
    this.add(this.handPanelB, BorderLayout.EAST);
  }

  /**
   * Returns Dimension of window size.
   * @return Dimension of preferred physical size of window
   */
  public Dimension getPreferredSize() {
    return new Dimension(this.preferredWidth, this.preferredHeight);
  }

  /**
   * Adds feature listeners to all sub panels.
   * @param features a feature object this panel uses
   */
  public void addFeatures(ViewFeatures features) {
    this.boardPanel.addFeatures(features);
    this.handPanelA.addFeatures(features);
    this.handPanelB.addFeatures(features);
  }

  /**
   * Advances all sub-panels in this component.
   */
  public void advance() {
    this.boardPanel.advance();
    this.handPanelA.advance();
    this.handPanelB.advance();
  }

  @Override
  public void selectedCard(int cardIdx, PlayerType playerType) {
    if (playerType == PlayerType.PLAYER_A) {
      this.handPanelA.selectedCard(cardIdx);
    }
    else if (playerType == PlayerType.PLAYER_B) {
      this.handPanelB.selectedCard(cardIdx);
    }
  }
}
