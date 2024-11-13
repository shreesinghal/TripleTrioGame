package cs3500.tripletrios.model;

import java.util.ArrayList;

/**
 * Interface to define behaviors of the player.
 * A player plays the game, has a hand of cards, and a color.
 */
public interface Player {

  /**
  * Returns the color of the player.
  * @return color of the player
  */
  Color getColor();

  /**
  * Returns the players hand.
  * @return players hand
  */
  ArrayList<Card> getHand();

  /**
  * Removes a card from the hand.
  * @param card the card wanting to be removed
  */
  void removeCardFromHand(Card card);

}