package cs3500.tripletrios.model;

import java.util.ArrayList;

/**
 * Player implementation to represent a player in the game.
 */
public class PlayerImpl implements Player {

  private ArrayList<Card> hand = new ArrayList<Card>();
  private final Color color;



  /**
  * Creates a new player with a hand of cards.
  * @param hand list of cards
  * @param color color of player
  */
  public PlayerImpl(ArrayList<Card> hand, Color color) {
    this.hand = hand;
    this.color = color;
  }


  /**
  * Returns the color of the player.
  *
  * @return color of the player
  */
  @Override
  public Color getColor() {
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