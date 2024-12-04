package cs3500.threetrios.providers.view;

import cs3500.threetrios.providers.model.TripleTriad;
import cs3500.threetrios.providers.view.tripletriadgui.ThreeTriosView;
import cs3500.threetrios.providers.view.tripletriadgui.TripleTriadView;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.WinningState;
import cs3500.tripletrios.view.CardView;
import cs3500.tripletrios.view.GridPanel;
import cs3500.tripletrios.view.HandPanel;
import cs3500.tripletrios.view.TTFrame;

public class ViewAdapterTheirsToOurs extends ThreeTriosView implements TTFrame {
  private final TripleTriadView theirView;
  private final TripleTriad theirModel;

  public ViewAdapterTheirsToOurs(TripleTriadView theirView, TripleTriad theirModel) {
    super(theirModel, theirModel.fetchTurn().toString());
    this.theirView = theirView;
    this.theirModel = theirModel;
  }

  /**
   * Makes the view visible for the viewer to see
   * when the game is ready to start.
   */
  @Override
  public void makeVisible() {
    theirView.display(true);
  }

  /**
   * Gets the handview of the player based on the color given.
   *
   * @param color
   * @return the HandPanel for the specific player
   */
  @Override
  public HandPanel getHandView(CardColor color) {
    // no equivalent method
    // Cant be done because ThreeTriosView uses composition with ThreeTriosPanel.
    // ThreeTriosPanel contains their handView. This class cannot access threeTriosPanel,
    // which would otherwise handle this method function.
    return null;
  }

  /**
   * @return
   */
  @Override
  public GridPanel getGridPanel() {
    // no equivalent method
    // Cant be done because ThreeTriosView uses composition with ThreeTriosPanel.
    // ThreeTriosPanel contians their gridPanel. This class cannot access threeTriosPanel,
    // which would otherwise handle this method function.
    return null;
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   *
   * @param listener the controller
   */
  @Override
  public void addListeners(TripleTrioFeatureController listener) {
    theirView.addFeatureListener(new ListenerAdapterOursToTheirs(listener));
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    theirView.advance();
    theirView.display(true);
  }

  /**
   * Creates a popup error message to tell the user something specific.
   *
   * @param message the message to be shown
   */
  @Override
  public void printInvalidClickMessage(String message) {
    theirView.popup(message);
  }

  /**
   * Updates the display of the grid with the current cards after a player's turn.
   */
  @Override
  public void updateTurn() {
    // no equivalent method
    // Their implementation automatically updates the turn of
    // the model after each move in advance,
    // so they don't have a concrete
    // public updateTurn method.
    this.refresh();
  }

  /**
   * Displays the popup of who wins/tie.
   *
   * @param finalState final state of game
   */
  @Override
  public void displayGameOverMessage(WinningState finalState) {
    theirView.popup(finalState.toString());
  }

  /**
   * Updates the Grid with cards placed.
   *
   * @param cardView
   */
  @Override
  public void updateGrid(CardView cardView) {

  }

  /**
   * @param x
   * @param y
   * @param card
   */
  @Override
  public void addCardToGrid(int x, int y, Card card) {
    // no equivalent method
    // This is because their view class has a private panel class that handles
    // adding a card to the grid. This method cannot be accessed publicly.
    // Therefore, this methods cannot be adapted in this manner.
    // We cannot access their private game panel because it is created with
    // in the constructor of the view class privately.

    // Update the model with the card placement
    this.theirModel.playToBoard(0, x, y);

    this.refresh();
  }

  /**
   * Display the current State of the game.
   */
  @Override
  public String toString() {
    return theirView.toString();
  }


}
