package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.PlayerAIImpl;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;
import cs3500.tripletrios.strategies.PlayerMove;
import cs3500.tripletrios.strategies.TripleTrioStrategy;
import cs3500.tripletrios.view.CardView;
import cs3500.tripletrios.view.TTFrame;


/**
 * Controller implementation that represents the AI player.
 * This controller uses the abstract GUI controller class to represent
 * a singular AI player in the game.
 */
public class TripleTrioAIPlayerContr
        extends TripleTrioAbstractGUIController
        implements TripleTrioModelListener {
  
  private final PlayerAIImpl player;
  private boolean ourPlayerCanPlay = false;


  /**
   * Constructor that instantiates a controller that takes in a GUI view
   * as well a strategy to use.
   * @param view a GUI view.

   */  
  public TripleTrioAIPlayerContr(TripleTrioModel model, PlayerAIImpl playerAI,
                                 TTFrame view) {
    super(view);
    this.model = model;


    if (playerAI.getColor() == CardColor.RED) {
      ourPlayerCanPlay = true;
    }


    this.view = view;
    this.player = playerAI;
    model.addListener(this, this.player.getColor());
  }

  @Override
  protected void onTurnNotification() {
    if (model.getPlayer().getColor() == player.getColor()) {
      System.out.println("AI is calculating its move...");

      PlayerMove aiMove = player.makeMove();
      Card selectedCard = player.getHand().get(aiMove.getCardInd());
      model.placeCard(aiMove.getX(), aiMove.getY(), selectedCard); // Place the card
      player.removeCardFromHand(player.getHand()
              .get(aiMove.getCardInd())); // Remove the card from the hand
      view.getHandView(player.getColor())
              .removeCard(selectedCard); // Update hand view
      view.getHandView(player.getColor()).repaint();

      view.getGridPanel().placeCardOnGrid(aiMove.getX(),
        aiMove.getY(),
        new CardView(selectedCard,
          aiMove.getX(),
          aiMove.getY(),
          view.getGridPanel().getWidth() / model.getGridWidth(),
          view.getGridPanel().getHeight() / model.getGridHeight())); // Update grid
      model.executeBattlePhase(aiMove.getX(), aiMove.getY());
      model.switchTurns();
      view.refresh();

      if (model.getFinalState() != WinningState.GameNotDone) {
        view.displayGameOverMessage(model.getFinalState());
      }

      System.out.println("You have placed a "
        + selectedCard.getColor()
        + " card at " + aiMove.getX()
        + " "
        + aiMove.getY());
    }
  }


  /**
   * Returns if controller is human.
   * @return true if controller is human
   */
  @Override
  public boolean isHuman() {
    return false;
  }

  /**
   * Handles cell click when player clicks on grid.
   * @param xGridLoc xGridLoc the x-coordinate of the clicked cell
   * @param yGridLoc yGridLoc the y-coordinate of the clicked cell
   */
  @Override
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) {
    //no implementation for AI player
  }

  /**
   * Handles an action when a player presses a card on the hand.
   *
   * @param i     the number of the card that was clicked
   * @param color the color of the card that was clicked
   */
  @Override
  public void handleCellClickForHand(int i, CardColor color) {
    // no impementation for ai
  }

  /**
   * Notifies that it's a player's turn.
   * @param color the name of the current player
   */
  @Override
  public void onPlayerTurn(CardColor color) {
    onTurnNotification();
  }

  /**
   * Notifies that a card was placed.
   *
   * @param x the x-coordinate of the placed card
   * @param y the y-coordinate of the placed card
   */
  @Override
  public void onCardPlaced(int x, int y) {
    //no implementation
  }

  /**
   * Notifies that the game state has updated.
   */
  @Override
  public void onGameStateUpdated() {
    //no implementation
  }

}
