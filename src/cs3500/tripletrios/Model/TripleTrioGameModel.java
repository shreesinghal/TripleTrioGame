package cs3500.tripletrios.Model;

import cs3500.tripletrios.Controller.TripleTrioController;
import cs3500.tripletrios.View.TripleTrioView;

import java.util.*;

public class TripleTrioGameModel implements TripleTrioModel{

  ArrayList<ArrayList<Cell>> grid;

  Set<Card> deck;
  Player currPlayer;
  Player opposingPlayer;
  boolean gameOver;
  TripleTrioController controller;
  TripleTrioView view;
  boolean gameStarted;

  public TripleTrioGameModel() {
    this.gameOver = false;
    this.currPlayer = new PlayerImpl(new ArrayList<Card>(), Color.RED);
    this.opposingPlayer = new PlayerImpl(new ArrayList<Card>(), Color.BLUE);
  }

  /**
   * Starts the game with a given deck of cards. The deck is used
   * to set up the player hands. We also instantiate the grid.
   *
   * @param deckOfCards a set of cards all unique
   * @param grid        2D arraylist of cells
   * @throws IllegalArgumentException if the grid size is even
   * @throws IllegalStateException    if the size of deck is less than the grid size
   */
  @Override
  public void startGame(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid) {

    this.grid = grid;
    this.deck = deckOfCards;
    
    int gridSize = 0;

    for (ArrayList<Cell> rows : grid ) {
      for (Cell cell : rows) {
        if (cell.cellType == Cell.CellType.CARDCELL) {
          gridSize++;
        }
      }
    }

    if (gridSize % 2 == 0) {
      throw new IllegalArgumentException("The grid size must be odd");
    }
    
    
    if (deckOfCards.size() < gridSize) {
      throw new IllegalStateException("Deck size must be greater than grid size");
    }

    ArrayList<Card> playerAHand = createHand(deckOfCards, gridSize);
    ArrayList<Card> playerBHand = createHand(deckOfCards, gridSize);


    this.currPlayer = new PlayerImpl(playerAHand, Color.RED);
    this.opposingPlayer = new PlayerImpl(playerBHand, Color.BLUE);

    this.gameStarted = true;
    this.gameOver = false;
  }

  private static ArrayList<Card> createHand(Set<Card> deckOfCards, int gridSize) {
    ArrayList<Card> playerHand = new ArrayList<Card>();
    for (int i = 0; i < (gridSize + 1) / 2; i++) {
      playerHand.add(deckOfCards.iterator().next());
    }

    return playerHand;
  }


  /**
   * Returns if the game is over as specified by the implementation.
   *
   * @return true if the game has ended and false otherwise
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public boolean isGameOver() {
    ensureGameStarted();
    return gameOver;
  }

  private void ensureGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException("Game is not started");
    }
  }

  /**
   * Returns if the game is won by the player as specified by the implementation.
   *
   * @return true if the game has been won or false if the game has not
   * @throws IllegalStateException if the game has not started or the game is not over
   */
  @Override
  public boolean isGameWon() {
    ensureGameStarted();
    return false;
  }

  /**
   * Returns the current player of the game.
   *
   * @return player
   */
  @Override
  public Player getPlayer() {
    return this.currPlayer;
  }

  /**
   * Returns the grid in its current status.
   *
   * @return the grid
   */
  @Override
  public ArrayList<ArrayList<Cell>> getGrid() {
    return this.grid;
  }

  /**
   * Returns the deck of the game.
   *
   * @return a set of decks(each unqiue)
   */
  @Override
  public Set<Card> getDeck() {
    return this.deck;
  }


}
