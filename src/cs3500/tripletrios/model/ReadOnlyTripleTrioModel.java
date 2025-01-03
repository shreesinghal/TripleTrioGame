package cs3500.tripletrios.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Interface for the Read only TripleTrioModel. This interface
 * contains the behaviors of the game that do not modify the game state.
 */
public interface ReadOnlyTripleTrioModel {


  /**
   * Returns if the game is over as specified by the implementation.
   *
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */
  WinningState getFinalState();

  /**
   * Returns if the game started.
   *
   * @return true if game started, else false
   */
  boolean isGameStarted();


  /**
   * Returns the current player of the game.
   *
   * @return player
   */
  Player getPlayer();

  /**
   * Returns the opposing player of the game.
   *
   * @return player
   */
  Player getOppPlayer();

  /**
   * Returns the grid in its current status.
   *
   * @return the grid
   */
  ArrayList<ArrayList<Cell>> getCurrentGrid();

  /**
   * Returns the original grid from the config file.
   *
   * @return the original grid at start game.
   */
  ArrayList<ArrayList<Cell>> getOriginalGrid();


  /**
   * Determines the winner of the game once the game has ended.
   *
   * @return the winner of the game
   */
  WinningState determineWinner();

  /**
   * Returns the height of the grid. For view purposes.
   *
   * @return dimensions
   */
  public int getGridHeight();

  /**
   * Returns the width of the grid. For view purposes.
   *
   * @return dimensions
   */
  public int getGridWidth();

  /**
   * Returns the full deck of cards.
   *
   * @return set of cards
   */
  Set<Card> getDeck();

  /**
   * Documents the path that the strategy takes.
   * @param corner takes in the position from grid that is being checked
   */
  void documentCheckOnGrid(Posn corner);

}
