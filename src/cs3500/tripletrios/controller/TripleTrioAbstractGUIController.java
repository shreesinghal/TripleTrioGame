package cs3500.tripletrios.controller;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.*;
import cs3500.tripletrios.view.TTFrame;

import java.util.ArrayList;
import java.util.Set;

abstract public class TripleTrioAbstractGUIController implements TripleTrioFeatureController {

  protected TripleTrioModel model;
  protected final TTFrame view;
  protected Card selectedCard = null; //card which is selected to place on grid

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   * @param view a GUI view.
   */
  public TripleTrioAbstractGUIController(TTFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
  }

  /**
   * Constructor that instantiates a controller that takes in a GUI view
   * and a Triple Trio model.
   * @param view a GUI view
   * @param model a Triple Trio Model
   */
  public TripleTrioAbstractGUIController(TTFrame view, TripleTrioGameModel model) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.view = view;
    this.model = model;
  }



  public void playGame(String deckPath,
                       String gridPath) {
    ArrayList<ArrayList<Cell>> grid = GridConfigReader.readGridConfiguration(gridPath);
    Set<Card> deck = CardDatabaseReader.readDeckConfiguration(deckPath);

    model.startGame(deck, grid);
    view.makeVisible();
  }

  /**
   * To be overridden by subclasses for handling their turn notifications
   */
  protected abstract void onTurnNotification();



  public void playMove(int xPos, int yPos) {
    model.getPlayer().removeCardFromHand(selectedCard); // Remove card from hand
    view.getHandView(this.model.getPlayer().getColor()).unHighlight(); // Unhighlight card
    view.getGridPanel().placeCardOnGrid(xPos - 1, yPos - 1, selectedCard); // Notify grid

    view.refresh(); // Refresh view to reflect changes

    model.placeCard(xPos - 1, yPos - 1, selectedCard);
    model.executeBattlePhase(xPos - 1, yPos - 1);
    model.switchTurns();
    view.refresh();

    System.out.println("You have placed a " + selectedCard.getColor() + " card at " + xPos + " " + yPos);
  }





}
