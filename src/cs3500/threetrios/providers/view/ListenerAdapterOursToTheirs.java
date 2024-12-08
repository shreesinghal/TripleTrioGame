package cs3500.threetrios.providers.view;

import cs3500.threetrios.providers.controller.ViewFeatures;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.CardColor;

/**
 * Adapter class that converts our TripleTrioFeatureController to the provider's
 * ViewFeatures interface.
 * This adapter allows our controller implementation to be used where the provider's
 * ViewFeatures interface is expected, bridging the gap between the two different
 * implementations of view-controller interaction.
 * The adapter handles the translation of method calls between the two systems,
 * including differences in event handling, player representation, and game action execution.
 */
public class ListenerAdapterOursToTheirs implements ViewFeatures {
  private final TripleTrioFeatureController ourListener;

  /**
   * Constructs a new ListenerAdapterOursToTheirs.
   * This adapter wraps our TripleTrioFeatureController to make it compatible with
   * the provider's ViewFeatures interface.
   * @param ourlistener The TripleTrioFeatureController instance to be adapted.
   * @throws IllegalArgumentException if the provided listener is null.
   */
  public ListenerAdapterOursToTheirs(TripleTrioFeatureController ourlistener) {
    if (ourlistener == null) {
      throw new IllegalArgumentException("Listener cannot be null");
    }
    this.ourListener = ourlistener;
  }



  @Override
  public void selectedCard(int cardIdx, PlayerType playerType) {
    ourListener.handleCellClickForHand(cardIdx, playerType == PlayerType.PLAYER_A
        ? CardColor.RED
        : CardColor.BLUE);
  }

  /**
   * Plays the current selected card to the coordinate.
   *
   * @param row    the row (0-based)
   * @param column the column (0-based)
   */
  @Override
  public void playAtBoardPoint(int row, int column) {
    ourListener.handleCellClickForGrid(row, column);
  }
}
