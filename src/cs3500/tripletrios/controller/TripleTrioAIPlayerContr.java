package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.strategies.PlayerMove;
import cs3500.tripletrios.strategies.TripleTrioStrategy;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;

/**
 * Controller implementation that represents the AI player.
 * This controller uses the abstract GUI controller class to represent
 * a singular AI player in the game.
 */
public class TripleTrioAIPlayerContr
        extends TripleTrioAbstractGUIController
        implements TripleTrioModelListener {
  
  private TripleTrioStrategy strategy;

  /**
   * Constructor that instantiates a controller that takes in a GUI view
   * as well a strategy to use.
   * @param view a GUI view.
   * @param strategy a strategy for the player
   */  
  public TripleTrioAIPlayerContr(TTFrame view, TripleTrioStrategy strategy) {
    super(view);
    if (strategy == null) {
      throw new IllegalArgumentException("strategy cannot be null");
    }
    this.strategy = strategy;
  }

  @Override
  protected void onTurnNotification() {
    if (!model.getPlayer().isHuman()) {
      System.out.println("AI is calculating its move...");
      PlayerMove move = strategy.moveCard();
      playMove(move.getX(), move.getY());
    }
  }


  @Override
  public void playGame(String deckPath,
                       String gridPath) {
    super.playGame(deckPath, gridPath);
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
  public void playGameWithModel(TripleTrioModel model,
                                String deckPath,
                                String gridPath) throws IOException {
    //no implementation
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
   * Notifies that it's a player's turn.
   * @param color the name of the current player
   */
  @Override
  public void onPlayerTurn(CardColor color) {
    //no implementation
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
