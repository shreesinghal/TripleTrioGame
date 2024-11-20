package cs3500.tripletrios.controller;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;


/**
 * This is the controller implementation specific to the GUI. Implements the
 * TripleTrioController interface and delegates input(clicks) from the GUI view to the
 * model of the game. This controller also accounts for AI strategies.
 */
public class TripleTrioHumanPlayerContr extends TripleTrioAbstractGUIController {
  protected TripleTrioModel model;
  protected TTFrame view;

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   * @param view a GUI view.
   */
  public TripleTrioHumanPlayerContr(TTFrame view) {
    super(view);
    view.addClickListeners(this);
  }

  /**
   * Play a new game of Triple Trio with the given configurations.
   *
   * @param model    a triple trio model
   * @param deckPath deckPath the path to the deck
   * @param gridPath gridPath the path to the grid
   */
  @Override
  public void playGame(TripleTrioModel model,
                       String deckPath,
                       String gridPath) {
    super.playGame(model);

    ArrayList<ArrayList<Cell>> grid = GridConfigReader.readGridConfiguration(gridPath);
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
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) {
    if (selectedCard != null || !hasBeenPlaced) {
      super.playMove(xGridLoc, yGridLoc);
      selectedCard = null;
      hasBeenPlaced = true;
    }
  }


  /**
   * Play a game of Triple Trios given a model with initial conditions.
   *
   * @param model a triple trio model
   */
  public void playGameWithModel(TripleTrioModel model) {
    super.playGame(model);

    ArrayList<ArrayList<Cell>> grid = model.getOriginalGrid();

    Set<Card> deck = model.getDeck();

    model.startGame(deck, grid);

    view.makeVisible();
  }



}
