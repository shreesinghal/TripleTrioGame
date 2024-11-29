package cs3500.threetrios.providers.view.tripletriadgui;

import cs3500.threetrios.providers.controller.ViewFeatures;
import cs3500.threetrios.providers.model.PlayerType;

/**
 * Represents a graphical view for a game of Triple Triad.
 */
public interface TripleTriadView {

  /**
   * Adds a ViewFeatures to components of this view.
   * @param features the features of this game
   */
  void addFeatureListener(ViewFeatures features);


  /**
   * Displays the graphical view to the user.
   * @param show a boolean for whether to display or not
   */
  void display(boolean show);

  /**
   * Repaints this component with the selected card highlighted.
   * @param cardIdx the card to highlight
   * @param playerType the player whose card is selected
   */
  void selectedCard(int cardIdx, PlayerType playerType);

  /**
   * Advances all components via repainting.
   */
  void advance();

  /**
   * Displays a popup window with the given message.
   * @param message message to display
   */
  void popup(String message);
}
