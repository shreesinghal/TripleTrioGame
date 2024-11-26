package view.tripletriadgui;

import controller.ViewFeatures;
import model.PlayerType;

/**
 * Represents a panel in a TripleTriad view.
 */
public interface TripleTriadPanel {
  /**
   * Adds the given feature to this panel's list of features.
   * @param features a feature object this panel uses
   */
  void addFeatures(ViewFeatures features);

  /**
   * Advances the view's observations of the model
   * to the model's current state.
   */
  void advance();

  void selectedCard(int cardIdx, PlayerType playerType);
}
