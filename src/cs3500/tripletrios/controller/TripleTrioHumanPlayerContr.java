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
  protected TTFrame view;
  private final Player player;
  private boolean ourPlayerCanPlay = false;

  public TripleTrioHumanPlayerContr(TripleTrioModel model, Player player, TTFrame viewPlayer) {
    super(viewPlayer);
    this.model = model;

    if (player.getColor() == CardColor.RED) {
      ourPlayerCanPlay = true;
    }


    if (player == null) {
      throw new NullPointerException("player cannot be null");
    }

    this.view = viewPlayer;
    this.player = player;
    model.addListener(this, this.player.getColor());

//    view.addClickListeners(this);
  }


  /**
   * Play a new game of Triple Trio with the given configurations.
   * @param deckPath deckPath the path to the deck
   * @param gridPath gridPath the path to the grid
   */
  @Override
  public void playGame(String deckPath,
                       String gridPath) {
    super.playGame(deckPath, gridPath);
  }

  /**
   * Handles an action when a player presses a card on the hand.
   * @param cardNum the number of the card that was clicked
   * @param color the color of the card that was clicked
   */
  public void handleCellClickForHand(int cardNum, CardColor color) {
    if (model.getPlayer().getColor() == color && ourPlayerCanPlay) {
      selectedCard = model.getPlayer().getHand().get(cardNum);
      System.out.println("[" + model.getPlayer().getHand() + "]");
      System.out.println("selected card is " + selectedCard);
      view.getHandView(player.getColor()).highlightHandCard(cardNum);

    } else if (!ourPlayerCanPlay && model.getPlayer().getColor() != color) {
      view.printInvalidClickMessage("You're playing on the wrong view!");
    } else {
      view.printInvalidClickMessage("It's not your turn!");
    }

    System.out.println("You clicked on the card at index " + cardNum + " in the "
        + model.getPlayer().getColor() + " hand.");
  }

  /**
   * Returns the player of the controller.
   * @return player
   */
  public Player getPlayerOfController() {
    return this.player;
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
   * @param xGridLoc the x coordinate of the click on grid
   * @param yGridLoc the y coordinate of the click on grid
   */
  @Override
  public void handleCellClickForGrid(int xGridLoc, int yGridLoc) {
    if (selectedCard == null) {
      view.printInvalidClickMessage("Select a card first!");
    } else {
      super.playMove(xGridLoc, yGridLoc);
      selectedCard = null;
    }
  }


  /**
   * Notifies that it's a player's turn.
   * @param color the name of the current player
   */
  public void onPlayerTurn(CardColor color) {
    System.out.println("It's " + color.toString() + "'s turn!");
    ourPlayerCanPlay = true;
    view.updateTurn();
    view.refresh();
  }

  /**
   * Notifies that a card was placed.
   * @param x the x-coordinate of the placed card
   * @param y the y-coordinate of the placed card
   */
  @Override
  public void onCardPlaced(int x, int y) {
    System.out.println("Card placed at (" + x + ", " + y + ")");
    view.refresh();
  }

  /**
   * Notifies that the game state has been updated
   */
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
