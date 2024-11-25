package cs3500.tripletrios.model;

import cs3500.tripletrios.controller.TripleTrioModelListener;

import java.util.ArrayList;
import java.util.Set;

/**
 * Interface to represents the TripleTrioGame functionality.
 */
public interface TripleTrioModel extends ReadOnlyTripleTrioModel {


  /**
   * Starts the game with a given deck of cards. The deck is used
   * to set up the player hands. We also instantiate the grid.
   *
   * @param deckOfCards a set of cards all unique
   * @param grid        2D arraylist of cells
   * @throws IllegalArgumentException if the grid size is even
   * @throws IllegalStateException    if the size of deck is less than the grid size
   */
  void startGame(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid);


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
   * Places the players card where desired.
   *
   * @param xPos x coordinate of desired place
   * @param yPos y coordinate of desired place
   * @param card card that is being placed
   */
  void placeCard(int xPos, int yPos, Card card);



  /**
   * Executes the battle phase on the card at specified location.
   *
   * @param xPos x position of card
   * @param yPos y position of card
   */
  void executeBattlePhase(int xPos, int yPos);

  /**
   * Determines the winner of the game once the game has ended.
   *
   * @return the winner of the game
   */
  WinningState determineWinner();



  /**
   * Switches the current player.
   */
  void switchTurns();


  /**
   * Documents the path that the strategy takes.
   * @param corner takes in the position from grid that is being checked
   */
  void documentCheckOnGrid(Posn corner);

  /**
   * Adds a listener to the Triple Trio model for a specific player color.
   * @param listener the listener to be added, which will respond to model updates
   * @param color color of card
   * @throws IllegalArgumentException if the listener is null
   */
  void addListener(TripleTrioModelListener listener, CardColor color);

  /**
   * Starts a GUI specific game.
   */
  void startGUIGame();

  /**
   * Creates a copy of the grid.
   * @return grid as 2D arraylist of cells
   * @throws IllegalStateException if the game has not started
   */
  ArrayList<ArrayList<Cell>> createCopyOfGrid();
}