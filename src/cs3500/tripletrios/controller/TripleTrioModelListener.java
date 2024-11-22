package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.CardColor;

public interface TripleTrioModelListener {
  /**
   * Notifies that it's a player's turn.
   * @param color the name of the current player
   */
  void onPlayerTurn(CardColor color);

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
