package cs3500.tripletrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TripleTrioGameModel implements TripleTrioModel{

  private ArrayList<ArrayList<Cell>> grid;

  private Set<Card> deck;
  private Player currPlayer;
  private Player opposingPlayer;
  private boolean gameStarted;

  public TripleTrioGameModel() {
    this.currPlayer = new PlayerImpl(new ArrayList<Card>(), Color.RED);
    this.opposingPlayer = new PlayerImpl(new ArrayList<Card>(), Color.BLUE);
  }

  /**
   * Starts the game with a given deck of cards. The deck is used
   * to set up the player hands. We also instantiate the grid.
   *
   * @param deckOfCards a set of cards all unique
   * @param grid    2D arraylist of cells
   * @throws IllegalArgumentException if the grid size is even
   * @throws IllegalStateException  if the size of deck is less than the grid size
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

    ArrayList<Card> playerAHand = new ArrayList<>();
    ArrayList<Card> playerBHand = new ArrayList<>();

    ArrayList<Card> deckList = new ArrayList<>();
    deckList.addAll(deckOfCards);
    for (int i = 1; i <= deckOfCards.size() / 2; i++) {
      Card currRedCard = deckList.remove(0);
      currRedCard.setCardColor(Color.RED);
      playerAHand.add(currRedCard);
    }

    for (int i = deckOfCards.size() / 2 + 1; i <= deckOfCards.size(); i++) {
      Card currBlueCard = deckList.remove(0);
      currBlueCard.setCardColor(Color.BLUE);
      playerBHand.add(currBlueCard);
    }

    this.currPlayer = new PlayerImpl(playerAHand, Color.RED);
    this.opposingPlayer = new PlayerImpl(playerBHand, Color.BLUE);

    this.gameStarted = true;
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
    boolean isGridFilled = true;
    for (ArrayList<Cell> row : this.grid) {
      for (Cell cell : row) {
      if (cell.isEmpty()) {
        isGridFilled = false;
      }
      }
    }

    return isGridFilled;
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




  /**
   * Determines the winner of the game once the game has ended.
   * @return the winner of the game
   */
  @Override
  public WinningState determineWinner() {
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
      return WinningState.RedWins;
    } else if (opposingPlayerCards > currPlayerCards){
      return WinningState.BlueWins;
    }

    return WinningState.Tie;
  }

  /**
   * Switches the current player.
   */
  @Override
  public void switchTurns() {
    Player temp = this.opposingPlayer;
    this.opposingPlayer = this.currPlayer;
    this.currPlayer = temp;
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
    if (ensurePositionWithinBounds(new Posn(x_pos, y_pos))) {
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
    List<Posn> flippedCards = battleAdjacentCards(x_pos, y_pos);
    while (!flippedCards.isEmpty()) {
      for (int i = flippedCards.size() - 1; i >= 0; i--) {
        List<Posn> cardsToBeAdded = battleAdjacentCards(flippedCards.get(i).getX(), flippedCards.get(i).getY());
        flippedCards.addAll(cardsToBeAdded);
        i += cardsToBeAdded.size();
        flippedCards.remove(flippedCards.get(i));
        i--;
      }
    }
  }


  private List<Posn> battleAdjacentCards(int x_pos, int y_pos) {
    List<Posn> flippedCards = new ArrayList<>();
    Card card = this.grid.get(y_pos).get(x_pos).getCard();

    if (card == null) {
      return Collections.emptyList();
    }

    Posn southLoc = new Posn(x_pos, y_pos + 1);
    Posn northLoc = new Posn(x_pos, y_pos - 1);
    Posn eastLoc = new Posn(x_pos + 1, y_pos);
    Posn westLoc = new Posn(x_pos - 1, y_pos);

    // this South vs bottom
    if (cardCanBattle(southLoc)
      && card.getSouth() > this.grid.get(y_pos + 1).get(x_pos).getCard().getNorth()) {
      this.grid.get(y_pos + 1).get(x_pos).getCard().flipOwnership();
      flippedCards.add(new Posn(x_pos, y_pos + 1));
    }

    // this West vs right
    if (cardCanBattle(westLoc)
      && card.getWest() > this.grid.get(y_pos).get(x_pos - 1).getCard().getEast()) {
      this.grid.get(y_pos).get(x_pos - 1).getCard().flipOwnership();
      flippedCards.add(new Posn(x_pos - 1, y_pos));
    }

    // this North vs top
    if (cardCanBattle(northLoc)
      && card.getNorth() > this.grid.get(y_pos - 1).get(x_pos).getCard().getSouth()) {
      this.grid.get(y_pos - 1).get(x_pos).getCard().flipOwnership();
      flippedCards.add(new Posn(x_pos, y_pos - 1));
    }

    // this East vs right
    if (cardCanBattle(eastLoc)
      && card.getEast() > this.grid.get(y_pos).get(x_pos + 1).getCard().getWest()) {
      this.grid.get(y_pos).get(x_pos + 1).getCard().flipOwnership();
      flippedCards.add(new Posn(x_pos + 1, y_pos));
    }

    return flippedCards;
  }

  private boolean cardCanBattle(Posn adjCardLoc) {
    return ensurePositionWithinBounds(new Posn(adjCardLoc.getX(), adjCardLoc.getY()))
      && grid.get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard() != null
      && this.grid.get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard().getColor() == opposingPlayer.getColor();
  }

  private boolean ensurePositionWithinBounds(Posn Posn) {
    return Posn.getX() >= 0
      && Posn.getX() < grid.get(0).size()
      && Posn.getY() >= 0
      && Posn.getY() < grid.size()
      && grid.get(Posn.getY()).get(Posn.getX()).getCellType() != Cell.CellType.HOLE;
  }

}