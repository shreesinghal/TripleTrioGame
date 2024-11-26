package model;

import java.util.List;
import model.card.Card;
import model.cell.Cell;

/**
 * Interface for Read Only Triple Triad.
 * Contains observational methods for the game triple triad.
 */
public interface ReadOnlyTripleTriad {

  /**
   * Returns the hand of the given player.
   * @return the hand of the player of the playerType
   * @throws IllegalArgumentException if playerType is null
   * @throws IllegalStateException if the game has not started
   */
  List<Card> fetchHand(PlayerType playerType);

  /**
   * Returns a copy of the current game board.
   * @return the current board
   * @throws IllegalStateException if the game has not started
   */
  Cell[][] fetchBoard();

  /**
   * Returns the number of rows in the Board.
   * @return int number of rows in Board.
   */
  int numBoardRows();

  /**
   * Returns number of columns in the board.
   * @return number of columns in the board.
   */
  int numBoardColumns();

  /**
   * Returns score of player. This is defined as the number of
   * Cells owned on the board and cards in Hand.
   * @param player player
   * @return integer number of player score
   */
  int fetchPlayerScore(PlayerType player);

  /**
   * Returns which player or no player that owns this cell.
   * @param row Row coordinate in Board
   * @param column Column coordinate in Board
   * @return Which player owns Board coordinate
   */
  PlayerType ownerAtCoordinate(int row, int column);

  /**
   * Returns the number of cards a player can flip
   * by playing the given card at the given coordinate.
   * @param row Row coordinate in Board.
   * @param column Column coordinate in Board.
   * @param card Card to be played.
   * @param player the player playing the card
   * @return Number of cards a player can flip.
   */
  int numCardsPlayerCanFlip(int row, int column, Card card, PlayerType player);

  /**
   * Returns true if current player can play at coordinate.
   * @param row Row coordinate in Board.
   * @param column Card to be played.
   * @return true if current player can play at coordinate.
   */
  boolean canPlayerPlayAtCoordinate(int row, int column);

  /**
   * Determines if the game is over.
   * @return if the game is over.
   * @throws IllegalStateException if the game has not started
   *     or if the game is over
   */
  boolean isGameOver();

  /**
   * Returns the type of the player that won or null if the game tied.
   * @return the player that won or null
   * @throws IllegalStateException if the game has not started
   */
  PlayerType fetchWinner();

  /**
   * Returns the current player.
   * @return the PlayerType of current Player
   */
  PlayerType fetchTurn();

  /**
   * Gets the Card at the given coordinate.
   * @param row Row of the board
   * @param column Column of the board
   * @return Card object at the coordinate
   * @throws IllegalStateException if there is no card at coordinate.
   */
  Card cardAtCoordinate(int row, int column);
}
