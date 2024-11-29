package cs3500.threetrios.providers.model.card;

import cs3500.threetrios.providers.model.Direction;

/**
 * An interface for cards that have a name
 * and directions associated with attack values.
 */
public interface Card {
  /**
   * Compares this card to the given card.
   * @param other Card to compare
   * @param direction which direction to compare numbers
   * @return -1 if other is greater, 1 if this card is greater,
   *     and 0 if the cards are equal
   * @throws IllegalArgumentException if other or direction is null
   */
  int compareTo(Card other, Direction direction);

  /**
   * Returns the name of this card.
   * @return the name of this card.
   */
  String fetchName();

  /**
   * Returns a copy of this card.
   * @return a copy of this card
   */
  Card copyCard();

  /**
   * Returns the number of this card at the given direction.
   *
   * @param direction the direction (North, East, South, West)
   * @return the card's number
   * @throws IllegalArgumentException if direction is null
   */
  model.card.CardNumber fetchNumAtDirection(Direction direction);

  /**
   * Returns true if this object is equal to given other.
   * @param other other object
   * @return true if this object is equal to given other.
   */
  boolean equals(Object other);

  /**
   * Returns the integer hashcode of this object.
   * @return integer hashcode of this object
   */
  int hashCode();
}
