package cs3500.threetrios.providers.view;

import cs3500.threetrios.providers.controller.ViewFeatures;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.CardColor;

public class ListenerAdapterOursToTheirs implements ViewFeatures {
  private TripleTrioFeatureController ourListener;

  public ListenerAdapterOursToTheirs(TripleTrioFeatureController ourlistener) {
    this.ourListener = ourlistener;
  }


  /**
   * Selects the given card index as the card to play.
   *
   * @param cardIdx    the card index
   * @param playerType
   */
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
