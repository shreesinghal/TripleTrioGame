package controller;

import model.PlayerType;

/**
 * Represents the features a graphical view can perform.
 */
public interface ViewFeatures {
  /**
   * Selects the given card index as the card to play.
   * @param cardIdx the card index
   */
  void selectedCard(int cardIdx, PlayerType playerType);

  /**
   * Plays the current selected card to the coordinate.
   * @param row the row (0-based)
   * @param column the column (0-based)
   */
  void playAtBoardPoint(int row, int column);
}
