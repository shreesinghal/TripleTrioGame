package cs3500.tripletrios.strategies;

import cs3500.tripletrios.controller.TripleTrioModelListener;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.PlayerHumanImpl;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is a of the model for testing purposes.
 * It can mimic and keep track of moves.
 */
public class MockModel implements TripleTrioModel {

  private boolean gameStarted;
  private Player currentPlayer;
  private Player opposingPlayer;
  private ArrayList<ArrayList<Cell>> grid;
  private ArrayList<ArrayList<Cell>> originalGrid;
  private Set<Card> deck;
  private int currentPlayerScore;
  private int oppPlayerScore;
  private WinningState winner;
  private final List<Posn> transcript;
  private HashMap<Posn, Boolean> legalSpaces;
  private final Map<Posn, Integer> scoreSpaces;



  /**
   * Creates a mock of the model for testing purposes. It can mimic and keep track of moves.
   */
  public MockModel() {
    this.currentPlayer = new PlayerHumanImpl(new ArrayList<>(), CardColor.RED);
    this.opposingPlayer = new PlayerHumanImpl(new ArrayList<>(), CardColor.RED);
    this.grid = new ArrayList<>();
    this.legalSpaces = new HashMap<>();
    this.scoreSpaces = new HashMap<>();
    this.transcript = new ArrayList<>();
    this.originalGrid = grid;
    this.gameStarted = false;
    this.currentPlayerScore = 0;
    this.oppPlayerScore = 0;
    

  }

  /**
   * Sets the number of flips the card makes if placed at this location.
   * @param posn location of card
   * @param cardIndex card index in hand
   * @param flips number of slips
   */
  public void setFlipCountForMove(Posn posn, int cardIndex, int flips) {
    scoreSpaces.putIfAbsent(posn, flips);
  }

  /**
   * Gets the transcript of the mock model.
   * @return the transcript
   */
  public List<Posn> getTranscript() {
    return transcript;
  }

  @Override
  public void startGame(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid) {
    this.grid = grid;
    this.originalGrid = grid;
    this.deck = deckOfCards;

    int gridSize = 0;
    for (ArrayList<Cell> rows : grid ) {
      for (Cell cell : rows) {
        if (cell.getCellType() == Cell.CellType.CARDCELL) {
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

    int handSize = (gridSize + 1) / 2;

    ArrayList<Card> deckList = new ArrayList<>();
    deckList.addAll(deckOfCards);

    for (int i = 1; i <= handSize; i++) {
      Card currRedCard = deckList.remove(0);
      currRedCard.setCardColor(CardColor.RED);
      playerAHand.add(currRedCard);
    }


    for (int i = 1; i <= handSize; i++) {
      Card currBlueCard = deckList.remove(0);
      currBlueCard.setCardColor(CardColor.BLUE);
      playerBHand.add(currBlueCard);
    }


    this.currentPlayer = new PlayerHumanImpl(playerAHand, CardColor.RED);
    this.opposingPlayer = new PlayerHumanImpl(playerBHand, CardColor.BLUE);

    this.gameStarted = true;
  }

  @Override
  public WinningState getFinalState() {
    // Return true if the grid is full
    return this.determineWinner();
  }

  @Override
  public boolean isGameStarted() {
    return gameStarted;
  }


  @Override
  public Player getPlayer() {
    return currentPlayer;
  }

  @Override
  public Player getOppPlayer() {
    return opposingPlayer;
  }

  @Override
  public ArrayList<ArrayList<Cell>> getCurrentGrid() {
    return grid;
  }

  /**
   * Returns the original grid from the config file.
   * @return the original grid at start game.
   */
  @Override
  public ArrayList<ArrayList<Cell>> getOriginalGrid() {
    return originalGrid;
  }

  @Override
  public void placeCard(int xPos, int yPos, Card card) {
    Cell cell = grid.get(yPos).get(xPos);
    cell.placeCard(card);
    transcript.add(new Posn(xPos, yPos));
  }


  /**
   *  Checks if placeing a card at the posn is legal.
   * @param pos location to place card
   * @return true if legal to place
   */
  public boolean isLegalMove(Posn pos) {
    transcript.add(pos);
    return legalSpaces.getOrDefault(pos, true);
  }

  /**
   * Documents checking a cell by a strategy by adding it to the transcript.
   * @param pos takes in the position from grid that is being checked
   */
  public void documentCheckOnGrid(Posn pos) {
    transcript.add(pos);
  }

  /**
   * Adds a listener to the Triple Trio model for a specific player color.
   * @param listener the listener to be added, which will respond to model updates
   * @param color color of card
   * @throws IllegalArgumentException if the listener is null
   */
  @Override
  public void addListener(TripleTrioModelListener listener, CardColor color) {
    //no implementation
  }


  /**
   * Starts a GUI game.
   */
  @Override
  public void startGUIGame() {
    //no implementation
  }

  /**
   * Sets the value for a specific cell position.
   */
  public void setCellValue(Posn pos, int value) {
    scoreSpaces.put(pos, value);
  }

  /**
   * Adds a cell position and specifies if it should be legal or not.
   */
  public void setCellLegal(Posn pos, boolean isLegal) {
    legalSpaces.put(pos, isLegal);
  }


  /**
   * Simulates scoring based on predetermined values.
   */
  public int getValueAtPos(Posn pos) {
    transcript.add(pos);
    return scoreSpaces.getOrDefault(pos, 0);
  }


  @Override
  public void executeBattlePhase(int xPos, int yPos) {
    // not needed
  }

  @Override
  public WinningState determineWinner() {
    return winner;
  }

  /**
   * Returns the height of the grid. For view purposes.
   *
   * @return dimensions
   */
  @Override
  public int getGridHeight() {
    return this.grid.size();
  }

  /**
   * Returns the width of the grid. For view purposes.
   *
   * @return dimensions
   */
  @Override
  public int getGridWidth() {
    return this.grid.get(0).size();
  }

  /**
   * Returns the full deck of cards.
   *
   * @return set of cards
   */
  @Override
  public Set<Card> getDeck() {
    return deck;
  }



  @Override
  public void switchTurns() {
    Player temp = currentPlayer;
    currentPlayer = opposingPlayer;
    opposingPlayer = temp;
  }


  /**
   * Sets the grid to a new grid.
   * @param newGrid new grid to replace
   */
  public void setGrid(ArrayList<ArrayList<Cell>> newGrid) {
    this.grid = newGrid;
  }

  /**
   * Sets the player score.
   * @param player the player whose score is being set
   * @param score the score we are using
   */
  public void setPlayerScore(Player player, int score) {
    if (player.equals(currentPlayer)) {
      this.currentPlayerScore = score;
    } else {
      this.oppPlayerScore = score;
    }
  }

  /**
   * Sets the winner using a WinningState object.
   * @param winner the winner of the game
   */
  public void setWinner(WinningState winner) {
    this.winner = winner;
  }
}
