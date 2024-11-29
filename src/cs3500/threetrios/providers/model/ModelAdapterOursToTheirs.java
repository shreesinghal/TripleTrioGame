package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.controller.ModelFeatures;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.model.cell.Cell;
import model.rule.BattleRule;
import cs3500.tripletrios.model.Cell.CellType;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapterOursToTheirs implements TripleTriad {

  private TripleTrioModel ourModel;

  public ModelAdapterOursToTheirs(TripleTrioModel ourModel) {
    if (ourModel == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.ourModel = ourModel;
  }

  /**
   * @param listener
   */
  @Override
  public void addListener(ModelFeatures listener) {

  }

  /**
   * Starts the game with the given list of types of players and the
   * dimensions of the board. Starts with Player A as first player.
   *
   * @param grid     starting 2D array of cells configuration
   * @param allCards all the cards to start the game with
   * @param rule     the battling rule of the game
   * @throws IllegalStateException    if the game has already started or the game is over
   * @throws IllegalArgumentException if the board or deck configurations are null
   * @throws IllegalArgumentException if the number of cards is not at least one greater than
   *                                  the number of card cells in the board
   * @throws IllegalArgumentException if the names of cards are not unique
   */
  @Override
  public void startGame(Cell[][] grid, List<Card> allCards, BattleRule rule) {
    ourModel.startGame();
  }

  /**
   * Plays a card to the game's board.
   * All indices are 0-based.
   *
   * @param handIdx the index the card in the hand
   * @param row     the row of the board to play to
   * @param col     the column of the board to play to
   * @throws IllegalArgumentException if the indices of the
   *                                  hand, row, or column are out of bounds
   * @throws IllegalStateException    if the game has not started
   *                                  or if the game is over
   */
  @Override
  public void playToBoard(int handIdx, int row, int col) {
    ourModel.placeCard(row, col, ourModel.getPlayer().getHand().get(handIdx));
  }





  /**
   * Returns the hand of the given player.
   *
   * @param playerType
   * @return the hand of the player of the playerType
   * @throws IllegalArgumentException if playerType is null
   * @throws IllegalStateException    if the game has not started
   */
  @Override
  public List<Card> fetchHand(PlayerType playerType) {
    return playerType.equals(PlayerType.PLAYER_A)
            ? arrayListToList(ourModel.getPlayer().getHand())
            : arrayListToList(ourModel.getOppPlayer().getHand());
  }

  private List<model.card.Card> arrayListToList(ArrayList<cs3500.tripletrios.model.Card> ourHand) {
    List<model.card.Card> cards = new ArrayList<>();
    for (cs3500.tripletrios.model.Card ourCard : ourHand) {
      Card theirCard = CardAdapterOursToTheirs(ourCard);
      cards.add(theirCard);
    }
    return cards;
  }

  /**
   * Returns a copy of the current game board.
   *
   * @return the current board
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public Cell[][] fetchBoard() {
    return new Cell[0][];
  }

  /**
   * Returns the number of rows in the Board.
   *
   * @return int number of rows in Board.
   */
  @Override
  public int numBoardRows() {
    return ourModel.getGridHeight();
  }

  /**
   * Returns number of columns in the board.
   *
   * @return number of columns in the board.
   */
  @Override
  public int numBoardColumns() {
    return ourModel.getGridWidth();
  }

  /**
   * Returns score of player. This is defined as the number of
   * Cells owned on the board and cards in Hand.
   *
   * @param player player
   * @return integer number of player score
   */
  @Override
  public int fetchPlayerScore(PlayerType player) {
    //cannot adapt this because our models getPlayerScore() method is private
  }

  /**
   * Returns which player or no player that owns this cell.
   *
   * @param row    Row coordinate in Board
   * @param column Column coordinate in Board
   * @return Which player owns Board coordinate
   */
  @Override
  public PlayerType ownerAtCoordinate(int row, int column) {
    if (ourModel.getCurrentGrid().get(row).get(column).getCard().getColor() == CardColor.RED) {
      return PlayerType.PLAYER_A;
    } else if (ourModel.getCurrentGrid().get(row).get(column).getCard().getColor() == CardColor.BLUE) {
      return PlayerType.PLAYER_B;
    } else {
      return PlayerType.NULL_PLAYER;
    }
  }

  /**
   * Returns the number of cards a player can flip
   * by playing the given card at the given coordinate.
   *
   * @param row    Row coordinate in Board.
   * @param column Column coordinate in Board.
   * @param card   Card to be played.
   * @param player the player playing the card
   * @return Number of cards a player can flip.
   */
  @Override
  public int numCardsPlayerCanFlip(int row, int column, Card card, PlayerType player) {
    //we cannot implement this because our executeBattlePhase() does this using private
    //helper methods
  }

  /**
   * Returns true if current player can play at coordinate.
   *
   * @param row    Row coordinate in Board.
   * @param column Card to be played.
   * @return true if current player can play at coordinate.
   */
  @Override
  public boolean canPlayerPlayAtCoordinate(int row, int column) {
    if (!ourModel.getCurrentGrid().get(row).get(column).getCellType().equals(CellType.HOLE)
            && ourModel.getCurrentGrid().get(row).get(column).isEmpty()) {
      return true;
    }

    return false;
  }

  /**
   * Determines if the game is over.
   *
   * @return if the game is over.
   * @throws IllegalStateException if the game has not started
   *                               or if the game is over
   */
  @Override
  public boolean isGameOver() {
    return !ourModel.getFinalState().equals(WinningState.GameNotDone);
  }

  /**
   * Returns the type of the player that won or null if the game tied.
   *
   * @return the player that won or null
   * @throws IllegalStateException if the game has not started
   */
  @Override
  public PlayerType fetchWinner() {
    if (!ourModel.isGameStarted()) {
      throw new IllegalStateException("Game has not yet started");
    }
    if (ourModel.getFinalState().equals(WinningState.Tie)) {
      return PlayerType.NULL_PLAYER;
    } else if (ourModel.getFinalState().equals(WinningState.RedWins)) {
      return PlayerType.PLAYER_A;
    } else if (ourModel.getFinalState().equals(WinningState.BlueWins)) {
      return  PlayerType.PLAYER_B;
    }

    return PlayerType.NULL_PLAYER;

  }

  /**
   * Returns the current player.
   *
   * @return the PlayerType of current Player
   */
  @Override
  public PlayerType fetchTurn() {
    if (ourModel.getPlayer().getColor().equals(CardColor.RED)) {
      return PlayerType.PLAYER_A;
    } else {
      return PlayerType.PLAYER_B;
    }
  }

  /**
   * Gets the Card at the given coordinate.
   *
   * @param row    Row of the board
   * @param column Column of the board
   * @return Card object at the coordinate
   * @throws IllegalStateException if there is no card at coordinate.
   */
  @Override
  public Card cardAtCoordinate(int row, int column) {
    return null;
  }
}
