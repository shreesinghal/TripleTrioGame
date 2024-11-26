package strategy;

/**
 * Represents a move for a game of Triple Triad.
 * Includes all information needed to play a turn.
 */
public interface TripleTriadMove {
  /**
   * Returns the hand index of the move.
   * @return hand index of card for move.
   */
  int fetchHandIdx();

  /**
   * Returns the row coordinate of the move.
   * @return row coordinate of move.
   */
  int fetchRow();

  /**
   * Returns column coordinate of the move.
   * @return column coordinate of the move
   */
  int fetchColumn();

  /**
   * Returns true if this move is equal to another move,
   * this includes the row index, column index, and hand index.
   * @param other Object
   * @return true if this move is equal to another
   */
  @Override
  public boolean equals(Object other);

  /**
   * Returns integer hashcode of this object.
   * @return integer hashcode of this object
   */
  @Override
  public int hashCode();
}
