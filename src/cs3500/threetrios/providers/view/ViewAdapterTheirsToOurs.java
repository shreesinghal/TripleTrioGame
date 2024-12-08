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

/**
 * Adapter class that bridges the gap between the provider's view implementation
 * and our view interface.
 * This class extends the provider's ThreeTriosView and implements our TTFrame interface,
 * allowing our controller to interact with the provider's view using our expected interface.
 */
public class ViewAdapterTheirsToOurs extends ThreeTriosView implements TTFrame {
  private final TripleTriadView theirView;
  private final TripleTriad theirModel;


  /**
   * Constructs a new ViewAdapterTheirsToOurs, bridging the provider's view and model to our
   * view interface.
   * @param theirView The provider's TripleTriadView that will be adapted to our view interface
   * @param theirModel The provider's TripleTriad model used to initialize the parent ThreeTriosView
   */
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


  @Override
  public HandPanel getHandView(CardColor color) {
    // no equivalent method
    // Cant be done because ThreeTriosView uses composition with ThreeTriosPanel.
    // ThreeTriosPanel contains their handView. This class cannot access threeTriosPanel,
    // which would otherwise handle this method function.
    return null;
  }


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


  @Override
  public void printInvalidClickMessage(String message) {
    theirView.popup(message);
  }

  /**
   * Updates the display of the grid with the current cards after a player's turn.
   */
  @Override
  public void updateTurn() {
    //Their code ensures the turn is updated everytime the game
    //is refreshed.
    this.refresh();
  }


  @Override
  public void displayGameOverMessage(WinningState finalState) {
    theirView.popup(finalState.toString());
  }


  @Override
  public void updateGrid(CardView cardView) {
    this.refresh();
  }


  @Override
  public void addCardToGrid(int x, int y, Card card) {
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
