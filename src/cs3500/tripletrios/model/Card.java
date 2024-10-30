package cs3500.tripletrios.model;

import java.util.Map;

/**
 * Card interface for the game. 
 */
public interface Card {

  /**
   * Returns the name of the current card.
   * @return String of the name
   */
  String getName();

  /**
   * Returns the player that the Card belongs to.
   * @return the Player
   */
  Color getColor();

  /**
   * Switch ownership of the card from current to opposing player.
   */
  void flipOwnership();

  /**
   * Returns all the card's attack values.
   * @return Map containing the 4 attack values
   */
  Map<Direction, Integer> getAttackValues();

  /**
   * Returns the north attack value.
   * @return attack value as an int
   */
  int getNorth();

  /**
   * Returns the south attack value.
   * @return attack value as an int
   */
  int getSouth();

  /**
   * Returns the east attack value.
   * @return attack value as an int
   */
  int getEast();

  /**
   * Returns the west attack value.
   * @return attack value as an int
   */
  int getWest();

  public void setCardColor(Color color);

  /**
   * Checks if the current card is equal to the input.
   * @param o card to compare to
   * @return true if the cards are equal
   */
  public boolean equals(Object o);

}