package model.cell;

import model.card.Card;
import model.PlayerType;

/**
 * An interface for cells in a grid-layout board.
 * Cells may have special features unique to their implementation.
 */
public interface Cell {
  /**
   * Returns which player has won the card in this cell.
   * @return The player type of the card that owns the card in the cell
   */
  PlayerType fetchOwner();

  /**
   * Returns true if this cell has a card.
   * @return true if this cell has a card.
   */
  boolean hasCard();

  /**
   * Returns a copy of the card that is in the cell.
   * If there is no card in the cell, return null.
   * @return a copy of the card that is in the cell
   * @throws IllegalStateException if cell has no card.
   */
  Card fetchCard();

  /**
   * Given a card, sets current card in cell.
   * @param card Card
   * @throws IllegalStateException if cell is not playable
   *     This is dictated by implementations.
   * @throws IllegalArgumentException if card is null
   */
  void playCard(Card card);

  /**
   * Sets owner of the current cell to the new owner.
   * @param owner PlayerType of new owner.
   * @throws IllegalArgumentException of owner is null
   * @throws IllegalArgumentException if the owner is not a playable player type
   */
  void changeOwner(PlayerType owner);

  /**
   * Returns a copy of the cell.
   * Mutating the card in the returned cell does not
   * affect the original cell's card.
   * @return a copy of this cell
   */
  Cell copyCell();

  /**
   * Returns the number of cards that can be played to this cell.
   * @return number of cards that can be played to this cell
   */
  int cardCapacity();

  /**
   * Returns true if this cell is equal to another.
   * @return true if this Cell Equals another
   */
  boolean equals(Object other);

  /**
   * Returns the integer hashcode of this cell.
   * @return hashcode of this object
   */
  int hashCode();
}
