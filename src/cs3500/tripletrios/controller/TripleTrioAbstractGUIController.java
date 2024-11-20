package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

abstract public class TripleTrioAbstractGUIController implements TripleTrioFeatureController {

  protected TripleTrioModel model;
  protected final TTFrame view;
  protected Card selectedCard = null;
  protected boolean hasBeenPlaced = false;

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   *
   * @param view a GUI view.
   */
  public TripleTrioAbstractGUIController(TTFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
  }


  public void playGame(TripleTrioModel model) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
  }

  public void playMove(int xPos, int yPos) {
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


  /**
   * Handles an action when a player presses a grid cell.
   */
  public abstract void handleCellClickForGrid(int xGridLoc, int yGridLoc);

  public abstract void handleCellClickForHand(int cardNum, CardColor color);
}
