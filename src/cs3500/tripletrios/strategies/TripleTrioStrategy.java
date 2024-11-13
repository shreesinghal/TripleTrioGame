package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;

public interface TripleTrioStrategy {

  /**
   * This shows a move by the AI player.
   * It gives a reasonably calculated PlayerMove that can be made to the game.
   * @return move
   */
  public PlayerMove moveCard();

}
