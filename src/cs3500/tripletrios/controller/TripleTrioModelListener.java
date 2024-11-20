package cs3500.tripletrios.controller;

public interface TripleTrioModelListener {
  /**
   * Notifies that it's a player's turn.
   * @param playerName the name of the current player
   */
  void onPlayerTurn(String playerName);

  /**
   * Notifies that a card was placed.
   * @param x the x-coordinate of the placed card
   * @param y the y-coordinate of the placed card
   */
  void onCardPlaced(int x, int y);

  /**
   * Notifies that the game state has updated.
   */
  void onGameStateUpdated();
}
