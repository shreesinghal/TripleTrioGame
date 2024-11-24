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
 * An abstract GUI controller class for managing the game flow of Triple Trio
 * in a graphical user interface (GUI) environment. This class provides core
 * functionality to interact with the model and view components of the game
 * and allows subclasses to implement specific behavior for handling turn
 * notifications.
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
   * Play a game of Triple Trio by
   * giving a deck and grid from configurations and calling start game
   * from the model.
   * @param deckPath path for the deck of cards
   * @param gridPath path for the grid
   */
  public void playGame(String deckPath,
                       String gridPath) {
    ArrayList<ArrayList<Cell>> grid = GridConfigReader.readGridConfiguration(gridPath);
    Set<Card> deck = CardDatabaseReader.readDeckConfiguration(deckPath);

    model.startGame(deck, grid);
    view.makeVisible();
  }

  /**
   * To be overridden by subclasses for handling their turn notifications.
   */
  protected abstract void onTurnNotification();


  /**
   * Plays a move in the game by removing selected card from hand and
   * places it onto the grid. It then runs the battle phase following
   * by switching turns of player.
   * @param xPos x position of chosen cell on grid
   * @param yPos y position of chosen cell on grid
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
            selectedCard.getColor() + " card at " + xPos + " " + yPos);
  }





}
