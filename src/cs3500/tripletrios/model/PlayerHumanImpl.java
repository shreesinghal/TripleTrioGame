package cs3500.tripletrios.model;

import java.util.ArrayList;

/**
 * Player implementation to represent a player in the game.
 * This can be used to interact with the game and play moves.
 */
public class PlayerHumanImpl extends PlayerAbstract {

  /**
  * Creates a new player with a hand of cards.
  * @param hand list of cards
  * @param color color of player
  */
  public PlayerHumanImpl(ArrayList<Card> hand, CardColor color) {
    super(hand, color);
  }

  /**
   * Checks if the Player is a human.
   * @return true or false is player is human
   */
  @Override
  public boolean isHuman() {
    return true;
  }


}