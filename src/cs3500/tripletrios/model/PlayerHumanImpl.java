package cs3500.tripletrios.model;

import java.util.ArrayList;

/**
 * Player implementation to represent a player in the game.
 * This can be used to interact with the game and play moves.
 */
public class PlayerHumanImpl implements Player {

  private ArrayList<Card> hand;
  private final CardColor color;



  /**
  * Creates a new player with a hand of cards.
  * @param hand list of cards
  * @param color color of player
  */
  public PlayerHumanImpl(ArrayList<Card> hand, CardColor color) {
    this.hand = hand;
    this.color = color;
  }


  /**
  * Returns the color of the player.
  *
  * @return color of the player
  */
  @Override
  public CardColor getColor() {
    return this.color;
  }

  /**
  * Returns the players hand.
  * @return players hand
  */
  @Override
  public ArrayList<Card> getHand() {
    return (ArrayList<Card>) this.hand.clone();
  }

  /**
  * Removes a card from the hand.
  * @param card the card wanting to be removed
  */
  @Override
  public void removeCardFromHand(Card card) {
    this.hand.remove(card);
  }



}