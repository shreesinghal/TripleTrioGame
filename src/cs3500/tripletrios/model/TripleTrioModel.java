package cs3500.tripletrios.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Interface to represents the TripleTrioGame functionality.
 */
public interface TripleTrioModel extends ReadOnlyTripleTrioModel {


  /**
   * Starts the game with a given deck of cards. The deck is used
   * to set up the player hands. We also instantiate the grid.
   * @param deckOfCards a set of cards all unique
   * @param grid 2D arraylist of cells
   * @throws IllegalArgumentException if the grid size is even
   * @throws IllegalStateException if the size of deck is less than the grid size
   */
  void startGame(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid);


  /**
   * Returns if the game is over as specified by the implementation.
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */
  boolean isGameOver();

  /**
   * Returns if the game started.
   * @return true if game started, else false
   */
  boolean isGameStarted();


  /**
   * Returns the current player of the game.
   * @return player
   */
  Player getPlayer();

  /**
   * Returns the grid in its current status.
   * @return the grid
   */
  ArrayList<ArrayList<Cell>> getCurrentGrid();




  /**
   * Returns the full deck of cards.
   * @return set of cards
   */
  Set<Card> getDeck();


  /**
   * Places the players card where desired.
   * @param xPos x coordinate of desired place
   * @param yPos y coordinate of desired place
   * @param card card that is being placed
   */
  void placeCard(int xPos, int yPos, CardImpl card);


  /**
   *  Executes the battle phase on the card at specified location.
   * @param xPos x position of card
   * @param yPos y position of card
   */
  void executeBattlePhase(int xPos, int yPos);

  /**
   * Determines the winner of the game once the game has ended.
   * @return the winner of the game
   */
  WinningState determineWinner();

  /**
  * Gets the current score of a player during the game.
  * @param player player whose score we want to check
  * @return the score at the current point of game
  */
  int getPlayerScore(Player player);

  /**
   * Switches the current player.
   */
  void switchTurns();
}