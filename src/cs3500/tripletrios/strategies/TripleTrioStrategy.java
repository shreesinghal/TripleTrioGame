package cs3500.tripletrios.strategies;


/**
 * Interface which represents the common behaviors each strategy should have.
 * This interface requires that every strategy have a moveCard method.
 */
public interface TripleTrioStrategy {

  /**
   * This shows a move by the AI player.
   * It gives a reasonably calculated PlayerMove that can be made to the game.
   * @return move
   */
  public PlayerMove moveCard();

}
