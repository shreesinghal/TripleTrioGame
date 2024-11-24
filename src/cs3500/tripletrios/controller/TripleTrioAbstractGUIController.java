package cs3500.tripletrios.controller;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.CardView;
import cs3500.tripletrios.view.TTFrame;

import java.util.ArrayList;
import java.util.Set;

/**
 * Abstract controller for handling TripleTrio game logic with a graphical user interface (GUI).
 * Defines the common behavior for controlling a TripleTrio game, including managing
 * game turns, handling card placement, and updating the view.
 */
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
   * Starts a new game with the given deck and grid configurations.
   * @param deckPath the file path to the deck configuration.
   * @param gridPath the file path to the grid configuration.
   */
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


  /**
   * Plays a move by placing the selected card on the specified grid coordinates.
   * @param xPos xPos the x-coordinate where the card should be placed (1-indexed).
   * @param yPos yPos the y-coordinate where the card should be placed (1-indexed).
   */
  public void playMove(int xPos, int yPos) {
    model.getPlayer().removeCardFromHand(selectedCard); // Remove card from hand
    view.getHandView(this.model.getPlayer().getColor()).unHighlight(); // Unhighlight card
    view.getGridPanel().placeCardOnGrid(xPos - 1, yPos - 1, new CardView(selectedCard,
            xPos - 1,
            yPos - 1,
            view.getGridPanel().getWidth() / model.getGridWidth(),
            view.getGridPanel().getHeight() / model.getGridHeight())); // Notify grid

    view.refresh(); // Refresh view to reflect changes

    model.placeCard(xPos - 1, yPos - 1, selectedCard);
    model.executeBattlePhase(xPos - 1, yPos - 1);
    model.switchTurns();
    view.refresh();

    System.out.println("You have placed a " +
            selectedCard.getColor()
            + " card at " + xPos + " " + yPos);
  }





}
