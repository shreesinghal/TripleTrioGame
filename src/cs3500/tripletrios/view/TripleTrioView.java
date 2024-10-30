package cs3500.tripletrios.view;

import cs3500.tripletrios.model.WinningState;

import java.io.IOException;

/**
 *Behaviors needed for a view of the TripleTrioGame implementation
 * that transmits information to the user.
 */
public interface TripleTrioView {

  /**
  * Display the current State of the game.
  */
  String toString();

  /**
  * Renders output to the screen.
  * @throws IOException if output is improper.
  */
  void render() throws IOException;


  /**
   * Displays the winner along with a final message.
   * @param winner the winner of the game
   */
  void displayFinalMessage(WinningState winner) throws IOException;
}