package cs3500.threetrios.providers.view;

import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.WinningState;
import cs3500.tripletrios.view.CardView;
import cs3500.tripletrios.view.GridPanel;
import cs3500.tripletrios.view.HandPanel;
import cs3500.tripletrios.view.TTFrame;
import model.ReadOnlyTripleTriad;
import view.tripletriadgui.TripleTriadView;
import view.tripletriadgui.TripleTriadPanel;

import java.io.IOException;

public class ViewAdapterTheirsToOurs extends view.tripletriadgui.ThreeTriosView implements TTFrame {
  view.tripletriadgui.TripleTriadView theirView;

  public ViewAdapterTheirsToOurs(TripleTriadView theirView, ReadOnlyTripleTriad theirModel) {
    super(theirModel, theirModel.fetchTurn().toString());
    this.theirView = theirView;
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
    // cant be done because ThreeTriosView uses composition with ThreeTriosPanel.
    // this class cannot access threeTriosPanel, which would otherwise handle this
    // method function.
  }

  /**
   * @return
   */
  @Override
  public GridPanel getGridPanel() {
    return ;
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   *
   * @param listener the controller
   */
  @Override
  public void addListeners(TripleTrioFeatureController listener) {
    theirView.addFeatureListener(listener);
  }

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  @Override
  public void refresh() {
    theirView.display(true);
  }

  /**
   * Creates a popup error message to tell the user something specific.
   *
   * @param message the message to be shown
   */
  @Override
  public void printInvalidClickMessage(String message) {
    theirView;
  }

  /**
   * Updates the display of the grid with the current cards after a player's turn.
   */
  @Override
  public void updateTurn() {

  }

  /**
   * Displays the popup of who wins/tie.
   *
   * @param finalState final state of game
   */
  @Override
  public void displayGameOverMessage(WinningState finalState) {

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

  }

  /**
   * Display the current State of the game.
   */
  @Override
  public String toString() {
    return "";
  }

  /**
   * Renders output to the screen.
   *
   * @throws IOException if output is improper.
   */
  @Override
  public void render() throws IOException {

  }

  /**
   * Displays the winner along with a final message.
   *
   * @param winner the winner of the game
   */
  @Override
  public void displayFinalMessage(WinningState winner) throws IOException {

  }
}
