package cs3500.tripletrios.model;

import cs3500.tripletrios.strategies.PlayerMove;
import cs3500.tripletrios.strategies.TripleTrioStrategy;

import java.util.ArrayList;

/**
 * AI player implementation for the Triple Trios game.
 * This player uses a strategy to compute its next move.
 */
public class PlayerAIImpl implements Player {

  private ArrayList<Card> hand;
  private final CardColor color;
  private final TripleTrioStrategy strategy;

  /**
   * Creates a new AI player with a hand of cards and a strategy.
   * @param hand the AI player's hand of cards
   * @param color the color associated with the AI player
   * @param strategy the strategy used to compute the AI's moves
   */
  public PlayerAIImpl(ArrayList<Card> hand, CardColor color, TripleTrioStrategy strategy) {
    this.hand = hand;
    this.color = color;
    this.strategy = strategy;
  }

  @Override
  public CardColor getColor() {
    return this.color;
  }

  @Override
  public ArrayList<Card> getHand() {
    return (ArrayList<Card>) this.hand.clone();
  }

  @Override
  public void removeCardFromHand(Card card) {
    this.hand.remove(card);
  }

  /**
   * Computes the next move for the AI.
   * @return the card chosen by the AI to play
   */
  public PlayerMove makeMove() {
    return strategy.moveCard();
  }
}
