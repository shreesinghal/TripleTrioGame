package cs3500.tripletrios.Model;

import cs3500.tripletrios.Controller.TripleTrioController;
import cs3500.tripletrios.View.TripleTrioView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TripleTrioGameModel implements TripleTrioModel{

  ArrayList<ArrayList<Cell>> grid;

  Player currPlayer;

  Player opposingPlayer;

  boolean gameOver;

  TripleTrioController controller;

  TripleTrioView view;

  boolean gameStarted;


  public TripleTrioGameModel() {
    this.grid = grid;
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
  public void startGame(ArrayList<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid) {
    
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
  }

  private static ArrayList<Card> createHand(ArrayList<Card> deckOfCards, int gridSize) {
    ArrayList<Card> playerHand = new ArrayList<Card>();
    for (int i = 0; i < (gridSize + 1) / 2; i++) {
      playerHand.add(deckOfCards.remove(0));
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
    return false;
  }

  /**
   * Returns if the game is won by the player as specified by the implementation.
   *
   * @return true if the game has been won or false if the game has not
   * @throws IllegalStateException if the game has not started or the game is not over
   */
  @Override
  public boolean isGameWon() {
    return false;
  }
}
