package cs3500.tripletrios.controller;

import cs3500.tripletrios.ConfigReaders.CardDatabaseReader;
import cs3500.tripletrios.ConfigReaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Color;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class TripleTrioGUIController implements TripleTrioController {
  private TripleTrioModel model;
  private TTFrame view;
  private Card selectedCard = null;



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
  public void playGame(TripleTrioModel model, String deckPath, String gridPath) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    this.model = model;

    // read from config files
    GridConfigReader gridReader = new GridConfigReader();
    ArrayList<ArrayList<Cell>> grid = gridReader.readGridConfiguration(gridPath);

    CardDatabaseReader cardReader = new CardDatabaseReader();
    Set<Card> deck = cardReader.readDeckConfiguration(deckPath);

    model.startGame(deck, grid);

    view.makeVisible();
  }

  /**
   * Handles an action when a player presses a card on the hand.
   */
  @Override
  public void handleCellClickForHand(int cardNum, Color color) {
    selectedCard = model.getPlayer().getHand().get(cardNum);
  }

  /**
   * Handles an action when a player presses a grid cell.
   */
  @Override
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) throws IOException {
    if (selectedCard != null) {
      this.playMove(xGridLoc, yGridLoc);
      view.getHandView(this.model.getPlayer().getColor()).unHighlight();
    }
  }

  /**
   * Play a game of Triple Trios given a model with initial conditions.
   *
   * @param model a triple trio model
   */
  @Override
  public void playGame(TripleTrioModel model) {
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
    System.out.println("Reached controller play move");
    model.getPlayer().removeCardFromHand(selectedCard);
    System.out.println(model.getPlayer().getHand().size());
    model.placeCard(xPos - 1, yPos - 1, selectedCard);
    model.executeBattlePhase(xPos - 1, yPos - 1);
  }


}
