package cs3500.tripletrios.Model;

import cs3500.tripletrios.Controller.TripleTrioController;
import cs3500.tripletrios.View.TripleTrioView;

import java.awt.*;
import java.util.*;
import java.util.List;

public class TripleTrioGameModel implements TripleTrioModel{

  private ArrayList<ArrayList<Cell>> grid;

  private Set<Card> deck;
  private Player currPlayer;
  private Player opposingPlayer;
  private TripleTrioController controller;
  private TripleTrioView view;
  private boolean gameStarted;
  private boolean gameOver;

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

  /**
   * Returns if the game started.
   *
   * @return true if game started, else false
   */
  @Override
  public boolean isGameStarted() {
    return this.gameStarted;
  }

//  /**
//   * Returns whether the game has been won.
//   *
//   * @return true is game has been won, else false
//   */
//  @Override
//  public boolean isGameWon() {
//    this.ensureGameStarted();
//    //check if the grid is full
//
//
//
//  }

  private Player determineWinner() {
    /**
     * do we have to account for - 1 in size?
     * need to account for draw -->
     * 1) player enum(Player Red, Player Blue, No Player)
     * 2) edit the color class and return a color instead and account for player in a separate function
     */
    ArrayList<Card> cardsInGrid = new ArrayList<>();
    for (int i = 0; i < this.grid.size(); i++) {
      for (int j = 0; j < this.grid.get(i).size(); j++ ) {
        if (this.grid.get(j).get(i).getCellType().equals(Cell.CellType.CARDCELL)) {
          cardsInGrid.add(this.grid.get(j).get(i).getCard());
        }
        //what happens if it is a hole, do I need this if statement?
      }
    }

    int currPlayerCards = currPlayer.getHand().size();
    int opposingPlayerCards = opposingPlayer.getHand().size();

    for (Card card : cardsInGrid) {
      if (card.getColor() == currPlayer.getColor()) {
        currPlayerCards++;
      } else {
        opposingPlayerCards++;
      }
    }

    //checks the winner based on cards on grid and in hand
    if (currPlayerCards > opposingPlayerCards) {
      return currPlayer;
    } else {
      return opposingPlayer;
    }
  }

  private void ensureGameStarted() {
    if (!gameStarted) {
      throw new IllegalStateException("Game is not started");
    }
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

  /**
   * Places the players card where desired.
   * @param x_pos x coordinate of desired place
   * @param y_pos y coordinate of desired place
   * @param card card that is being placed
   */
  @Override
  public void placeCard(int x_pos, int y_pos, CardImpl card) {
    if (ensurePositionWithinBounds(x_pos, y_pos)) {
      this.grid.get(y_pos).get(x_pos).placeCard(card);
    } else {
      throw new IllegalArgumentException("Invalid position entered.");
    }

  }

  /**
   * Executes the battle phase on the card at specified location.
   *
   * @param x_pos x position of card
   * @param y_pos y position of card
   */
  @Override
  public void executeBattlePhase(int x_pos, int y_pos) {
    List<Point> flippedCards = battleAdjacentCards(x_pos, y_pos);
    while (!flippedCards.isEmpty()) {
      for (Point cardLocation : flippedCards) {
        flippedCards.addAll(battleAdjacentCards(cardLocation.x, cardLocation.y));
        flippedCards.remove(cardLocation);
      }
    }
  }


  private List<Point> battleAdjacentCards(int x_pos, int y_pos) {
    List<Point> flippedCards = new ArrayList<>();
    Card card = this.grid.get(y_pos).get(x_pos).getCard();

    Point southLoc = new Point(x_pos, y_pos - 1);
    Point northLoc = new Point(x_pos, y_pos + 1);
    Point eastLoc = new Point(x_pos + 1, y_pos);
    Point westLoc = new Point(x_pos - 1, y_pos);

    if (cardCanBattle(southLoc) && card.getNorth() > this.grid.get(y_pos - 1).get(x_pos).getCard().getSouth()) {
      this.grid.get(y_pos - 1).get(x_pos).getCard().flipOwnership();
      flippedCards.add(new Point(x_pos, y_pos - 1));
    }

    if (cardCanBattle(westLoc) && card.getEast() > this.grid.get(y_pos).get(x_pos + 1).getCard().getWest()) {
      this.grid.get(y_pos).get(x_pos + 1).getCard().flipOwnership();
      flippedCards.add(new Point(x_pos + 1, y_pos));

    }

    if (cardCanBattle(northLoc) && card.getSouth() > this.grid.get(y_pos + 1).get(x_pos).getCard().getNorth()) {
      this.grid.get(y_pos + 1).get(x_pos).getCard().flipOwnership();
      flippedCards.add(new Point(x_pos, y_pos + 1));

    }

    if (cardCanBattle(eastLoc) && card.getWest() > this.grid.get(y_pos).get(x_pos - 1).getCard().getEast()) {
      this.grid.get(y_pos).get(x_pos - 1).getCard().flipOwnership();
      flippedCards.add(new Point(x_pos - 1, y_pos));
    }
    return flippedCards;
  }


  private boolean cardCanBattle(Point adjCardLoc) {
    boolean canBattle = ensurePositionWithinBounds(adjCardLoc.x, adjCardLoc.y);
    if (this.grid.get(adjCardLoc.y).get(adjCardLoc.x).getCard() != null) {
      if (this.grid.get(adjCardLoc.y).get(adjCardLoc.x).getCard().getColor() != currPlayer.getColor()) {
        canBattle = true;
      } else {
        canBattle = false;
      }
    } else {
      canBattle = false;
    }

    return canBattle;

  }

  private boolean ensurePositionWithinBounds(int xPosition, int yPosition) {
    boolean ensured = true;
    ArrayList<Cell> row = grid.get(1);
    if (xPosition <= 0 || xPosition >= row.size()
        || yPosition <= 0 || yPosition >= grid.size()) {
      ensured = false;
    }

    if (Cell.CellType.HOLE == grid.get(yPosition).get(xPosition).getCellType()) {
      ensured = false;
    }

    if (grid.get(yPosition).get(xPosition).getCard() == null) {
      ensured = false;
    }

    return ensured;
  }


}