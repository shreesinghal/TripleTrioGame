package cs3500.tripletrios.controller;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.strategies.CornerStrategy;
import cs3500.tripletrios.strategies.PlayerMove;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


/**
 * This is the controller implementation specific to the GUI. Implements the
 * TripleTrioController interface and delegates input(clicks) from the GUI view to the
 * model of the game. This controller also accounts for AI strategies.
 */
public class TripleTrioGUIController implements TripleTrioController {
  private TripleTrioModel model;
  private final TTFrame view;
  private Card selectedCard = null;
  private boolean hasBeenPlaced = false;
  private CornerStrategy cornerStrategy;

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   * @param view a GUI view.
   */
  public TripleTrioGUIController(TTFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
    view.addClickListeners(this);
  }

  /**
   * Play a new game of Triple Trio with the given configurations.
   *
   * @param model    a triple trio model
   * @param deckPath deckPath the path to the deck
   * @param gridPath gridPath the path to the grid
   * @throws IOException if something in the game is displayed wrong.
   */
  @Override
  public void playGame(TripleTrioModel model,
                       String deckPath,
                       String gridPath) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    this.model = model;
    this.cornerStrategy = new CornerStrategy(model);

    // read from config files
    GridConfigReader gridReader = new GridConfigReader();
    ArrayList<ArrayList<Cell>> grid = GridConfigReader.readGridConfiguration(gridPath);

    CardDatabaseReader cardReader = new CardDatabaseReader();
    Set<Card> deck = CardDatabaseReader.readDeckConfiguration(deckPath);

    model.startGame(deck, grid);

    view.makeVisible();
  }

  /**
   * Handles an action when a player presses a card on the hand.
   */
  @Override
  public void handleCellClickForHand(int cardNum, CardColor color) {
    System.out.println("You clicked on the card at index " + cardNum + " in the "
        + model.getPlayer().getColor() + " hand.");
    selectedCard = model.getPlayer().getHand().get(cardNum);
    hasBeenPlaced = false;
  }

  /**
   * Handles an action when a player presses a grid cell.
   */
  @Override
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) throws IOException {
    if (selectedCard != null || !hasBeenPlaced) {
      this.playMove(xGridLoc, yGridLoc);
      selectedCard = null;
      hasBeenPlaced = true;
    }
  }

  /**
   * Handles actions from the AI and utilizes the strategies.
   */
  public void playAI() {
    PlayerMove aiMove = cornerStrategy.moveCard();
    model.placeCard(aiMove.getX(), aiMove.getY(),
        this.model.getPlayer().getHand().get(aiMove.getCardInd()));
    model.switchTurns();
  }

  /**
   * Play a game of Triple Trios given a model with initial conditions.
   *
   * @param model a triple trio model
   */
  @Override
  public void playGameWithModel(TripleTrioModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    this.model = model;

    ArrayList<ArrayList<Cell>> grid = model.getOriginalGrid();

    Set<Card> deck = model.getDeck();

    model.startGame(deck, grid);

    view.makeVisible();
  }

  private void playMove(int xPos, int yPos) throws IOException {
    model.getPlayer().removeCardFromHand(selectedCard);
    view.getHandView(this.model.getPlayer().getColor()).unHighlight();
    view.refresh();

    model.placeCard(xPos - 1, yPos - 1, selectedCard);
    model.executeBattlePhase(xPos - 1, yPos - 1);
    model.switchTurns();
    view.refresh();

    System.out.println("You have placed a " +
            selectedCard.getColor() + " card at " + xPos + " " + yPos);
  }


}
