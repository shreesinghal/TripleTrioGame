package cs3500.tripletrios.controller;


import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;
import cs3500.tripletrios.view.CardView;
import cs3500.tripletrios.view.GridPanelHintDecorator;
import cs3500.tripletrios.view.TTFrame;

import java.util.HashMap;
import java.util.Map;


/**
 * Abstract controller for handling TripleTrio game logic with a graphical user interface (GUI).
 * Defines the common behavior for controlling a TripleTrio game, including managing
 * game turns, handling card placement, and updating the view.
 */
abstract public class TripleTrioAbstractGUIController implements TripleTrioFeatureController {

  protected TripleTrioModel model;
  protected TTFrame view;
  protected Card selectedCard = null; //card which is selected to place on grid
  protected final Map<CardColor, Boolean> hintsEnabled = new HashMap<>();

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   * @param view a GUI view.
   */
  public TripleTrioAbstractGUIController(TTFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;

    GridPanelHintDecorator hintDecorator = new
            GridPanelHintDecorator(view.getGridPanel(), model);
    view.getGridPanel().addDecorator(hintDecorator);
    hintsEnabled.put(CardColor.RED, false);
    hintsEnabled.put(CardColor.BLUE, false);
  }


  @Override
  public void toggleHints(CardColor color) {
    hintsEnabled.put(color, !hintsEnabled.get(color));
    if (!hintsEnabled.get(color)) {
      view.getGridPanel().setHintDecorator(null);
    } else if (selectedCard != null) {
      GridPanelHintDecorator decorator = new GridPanelHintDecorator(
              view.getGridPanel(),
              model
      );
      decorator.setSelectedCard(selectedCard);
      decorator.setEnabled(true);
      view.getGridPanel().setHintDecorator(decorator);
    }
    view.refresh();
  }

  @Override
  public boolean areHintsEnabled(CardColor color) {
    return hintsEnabled.get(color);
  }




  /**
   * To be overridden by subclasses for handling their turn notifications.
   */
  protected abstract void onTurnNotification();


  /**
   * Plays a move by placing the selected card on the specified grid coordinates.
   * @param xPos xPos the x-coordinate where the card should be placed (1-indexed).
   * @param yPos yPos the y-coordinate where the card should be placed (1-indexed).
   */
  public void playMove(int xPos, int yPos) {
    try {
      model.placeCard(xPos - 1, yPos - 1, selectedCard); // Place the card
      model.getPlayer().removeCardFromHand(selectedCard); // Remove the card from the hand
      view.getHandView(this.model.getPlayer()
              .getColor()).removeCard(selectedCard);
      // Update hand view
      view.getHandView(this.model.getPlayer().getColor()).repaint();

      view.getGridPanel().placeCardOnGrid(xPos - 1, yPos - 1, new CardView(selectedCard,
          xPos - 1,
          yPos - 1,
          view.getGridPanel().getWidth() / model.getGridWidth(),
              view.getGridPanel().getHeight() / model.getGridHeight())); // Update grid
      model.executeBattlePhase(xPos - 1, yPos - 1);
      model.switchTurns();
      view.refresh();

      if (model.getFinalState() != WinningState.GameNotDone) {
        view.displayGameOverMessage(model.getFinalState());
      }

      System.out.println("You have placed a "
          + selectedCard.getColor()
          + " card at " + xPos + " " + yPos);
    } catch (Exception e) {
      view.printInvalidClickMessage("You cannot place a card there.");
    }
  }

//  public void handleCardSelection(Card card) {
//    hintDecorator.setSelectedCard(card);
//    hintDecorator.setEnabled(true);
//    gridPanel.repaint();
//  }
}
