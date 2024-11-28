package model;

import java.util.List;

import controller.ModelFeatures;
import model.card.Card;
import model.cell.Cell;
import model.rule.BattleRule;

/**
 * Interface for Game Triple Triad.
 * To start a game, read the ConfigReader class to see
 * where to define configuration files.
 */
public interface TripleTriad extends model.ReadOnlyTripleTriad {
  /**
   *
   * @param listener
   */
  void addListener(ModelFeatures listener);

  /**
   * Starts the game with the given list of types of players and the
   * dimensions of the board. Starts with Player A as first player.
   * @param grid starting 2D array of cells configuration
   * @param allCards all the cards to start the game with
   * @param rule the battling rule of the game
   * @throws IllegalStateException    if the game has already started or the game is over
   * @throws IllegalArgumentException if the board or deck configurations are null
   * @throws IllegalArgumentException if the number of cards is not at least one greater than
   *     the number of card cells in the board
   * @throws IllegalArgumentException if the names of cards are not unique
   */
  void startGame(Cell[][] grid, List<Card> allCards, BattleRule rule);

  /**
   * Plays a card to the game's board.
   * All indices are 0-based.
   * @param handIdx the index the card in the hand
   * @param row the row of the board to play to
   * @param col the column of the board to play to
   * @throws IllegalArgumentException if the indices of the
   *     hand, row, or column are out of bounds
   * @throws IllegalStateException if the game has not started
   *     or if the game is over
   */
  void playToBoard(int handIdx, int row, int col);
}
