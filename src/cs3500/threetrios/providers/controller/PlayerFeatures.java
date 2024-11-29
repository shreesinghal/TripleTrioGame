package cs3500.threetrios.providers.controller;

/**
 * Features for player interface to interact
 * with a controller. A player must notify a
 * controller of the move to make.
 */
public interface PlayerFeatures {

  /**
   * Make a move in the game triple triad.
   * @param handIdx index of card in hand
   * @param row row index of board
   * @param column column index of board
   */
  void makeMove(int handIdx, int row, int column);
}
