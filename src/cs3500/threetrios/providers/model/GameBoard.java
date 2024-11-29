package cs3500.threetrios.providers.model;

import java.util.HashMap;

import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.model.cell.Cell;
import cs3500.threetrios.providers.model.PlayerType;

/**
 * Represents a Board for the game triple triad.
 * The board is a 2-D array with the first index as the row
 * and the second index as the column (0-based indices).
 * The size of the board cannot change once created,
 * and it must have an odd number of cells.
 */
public interface GameBoard {

  /**
   * Returns a copy of the Board.
   * @return a copy of this Board
   */
  Cell[][] fetchCopy();


  /**
   * Returns the width of this board's grid.
   * @return the board width
   */
  int fetchRows();

  /**
   * Returns the height of this board's grid.
   * @return the board height
   */
  int fetchColumns();

  /**
   * Returns a copy of the card in the cell located
   * at the given row and column indices (0-based).
   * @param row the row index
   * @param col the column index
   * @return a copy of the card
   * @throws IllegalArgumentException if the row
   */
  Cell fetchCell(int row, int col);

  /**
   * Plays a card to a cell of this board.
   * Row and column indices are 0-based.
   * @param card the card to place
   * @param row the row index to place at
   * @param col the column index to place at
   */
  void playToGrid(Card card, PlayerType playerType, int row, int col);

  /**
   * Returns true if every cell has a valid player owner.
   * @return True if every cell has a valid player owner
   */
  boolean isBoardFull();

  /**
   * Returns a hashmap of the player types to
   * the number of cells in the board they own.
   * @return map of player type to number of owned cells
   */
  HashMap<PlayerType, Integer> fetchCellOwnersCount();

  /**
   * Returns the number of cells that can be played too.
   * @return integer number of cells.
   */
  int fetchPlayableCells();

  /**
   * Returns true if this coordinate has a card. Also returns
   * false if this cell is a hole.
   * @param row Row of cell
   * @param column Column of cell
   * @return true if coordinate can be played too.
   */
  boolean canPlayAtCoordinate(int row, int column);

  /**
   * The owner at given coordinate.
   * @param row Row of coordinate
   * @param column Column of coordinate
   * @return owner at given coordinate
   */
  PlayerType ownerAtCoordinate(int row, int column);

  /**
   * Card at given coordinate.
   * @param row Row at given coordinate
   * @param column Column of coordinate
   * @return Card at given coordinate.
   */
  Card contentsAtCoordinate(int row, int column);

  /**
   * Returns the card at the given coordinate.
   * @param row Row index of Board
   * @param column Column index of board
   * @return Card at coordinate
   * @throws IllegalStateException if no card is found.
   */
  Card cardAtCoordinate(int row, int column);

  /**
   * Returns true if this board is equal to another board,
   * this includes number of rows, columns, cells and their contents.
   * This is useful for testing.
   * @param other Object
   * @return True if this board is equal to another.
   */
  @Override
  boolean equals(Object other);

  /**
   * Returns integer hashcode of this object.
   * @return integer hashcode of this object
   */
  @Override
  int hashCode();

}
