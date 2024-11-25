package cs3500.tripletrios.model;

import java.util.ArrayList;

public class PlayerAbstract implements Player{

  protected ArrayList<Card> hand;
  protected final CardColor color;

  public PlayerAbstract(ArrayList<Card> hand, CardColor color) {
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
   *
   * @return players hand
   */
  @Override
  public ArrayList<Card> getHand() {return (ArrayList<Card>) this.hand.clone();
  }

  /**
   * Removes a card from the hand.
   *
   * @param card the card wanting to be removed
   */
  @Override
  public void removeCardFromHand(Card card) {
    this.hand.remove(card);
  }

  /**
   * Checks if the player is human.
   *
   * @return true or false whether player is human
   */
  @Override
  public boolean isHuman() {
    return false;
  }
}
