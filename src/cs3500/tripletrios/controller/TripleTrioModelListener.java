package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;



/**
 * Behaviors needed for a controller that listens and
 * works specifically with the TripleTrio model. Allows the
 * model to know specifics to which player is playing, where a card
 * was placed, and that the game state has been updated.
 */
public interface TripleTrioModelListener {

  /**
   * Notifies that it's a player's turn.
   * Notifies which player is allowed to act next.
   * @param color the name of the current player
   */
  void onPlayerTurn(CardColor color);

  /**
   * Notifies that a card was placed.
   *
   * @param x    the x-coordinate of the placed card
   * @param y    the y-coordinate of the placed card
   * @param card card being placed
   */
  void onCardPlaced(int x, int y, Card card);

  /**
   * Notifies that the game state has updated.
   */
  void onGameStateUpdated();

}
