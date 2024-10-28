package cs3500.tripletrios.Model;

import java.util.ArrayList;
import java.util.Set;

public interface TripleTrioModel {


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

//  /**
//   * Returns whether the game has been won.
//   * @return true is game has been won, else false
//   */
//  boolean isGameWon();

  /**
   * Returns the current player of the game.
   * @return player
   */
  Player getPlayer();

  /**
   * Returns the grid in its current status.
   * @return the grid
   */
  ArrayList<ArrayList<Cell>> getGrid();

  /**
   * Returns the deck of the game.
   * @return a set of decks(each unqiue)
   */
  Set<Card> getDeck();

  /**
   * Places the players card where desired.
   * @param x_pos x coordinate of desired place
   * @param y_pos y coordinate of desired place
   * @param card card that is being placed
   */
  void placeCard(int x_pos, int y_pos, CardImpl card);


  /**
   *  Executes the battle phase on the card at specified location.
   * @param x_pos x position of card
   * @param y_pos y position of card
   */
  void executeBattlePhase(int x_pos, int y_pos);
}