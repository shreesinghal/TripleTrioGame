package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;


/**
 * This is the controller implementation specific to the GUI. Implements the
 * TripleTrioController interface and delegates input(clicks) from the GUI view to the
 * model of the game. This controller also accounts for AI strategies.
 */
public class TripleTrioHumanPlayerContr extends TripleTrioAbstractGUIController implements TripleTrioModelListener {
  protected final TripleTrioModel model;
  protected TTFrame view;
  private final Player player;

  public TripleTrioHumanPlayerContr(TripleTrioModel model, Player player, TTFrame viewPlayer) {
    super(viewPlayer);

    if (model == null) {
      throw new NullPointerException("model cannot be null");
    }

    if (player == null) {
      throw new NullPointerException("player cannot be null");
    }

    this.view = viewPlayer;
    this.player = player;
    this.model = model;
    view.addClickListeners(this);
  }


  /**
   * Play a new game of Triple Trio with the given configurations.
   *
   * @param deckPath deckPath the path to the deck
   * @param gridPath gridPath the path to the grid
   */
  @Override
  public void playGame(String deckPath,
                       String gridPath) {
    super.playGame(deckPath, gridPath);
    model.addListener(this);
  }

  /**
   * Handles an action when a player presses a card on the hand.
   */
  public void handleCellClickForHand(int cardNum, CardColor color) {
    if (model.getPlayer().getColor() == color) {
      selectedCard = model.getPlayer().getHand().get(cardNum);
    } else {
      view.printInvalidClickMessage("It's not your turn!");
    }

    System.out.println("You clicked on the card at index " + cardNum + " in the "
        + model.getPlayer().getColor() + " hand.");
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
    // never used in this impl
  }

  /**
   * Returns if controller is human.
   *
   * @return true if controller is human
   */
  @Override
  public boolean isHuman() {
    return true;
  }

  /**
   * Handles an action when a player presses a grid cell.
   */
  @Override
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) {
    if (selectedCard == null) {
      view.printInvalidClickMessage("It's not your turn!");
    } else {
      super.playMove(xGridLoc, yGridLoc);
      selectedCard = null;
    }
  }

  public void onPlayerTurn(CardColor color) {
    System.out.println("It's " + color.toString() + "'s turn!");
    model.setTurn(color);
    view.refresh();
  }

  @Override
  public void onCardPlaced(int x, int y) {
    System.out.println("Card placed at (" + x + ", " + y + ")");
    view.refresh();
  }

  @Override
  public void onGameStateUpdated() {
    System.out.println("Game state updated.");
    view.refresh();
  }

  /**
   * To be overridden by subclasses for handling their turn notifications
   */
  @Override
  protected void onTurnNotification() {

  }
}
