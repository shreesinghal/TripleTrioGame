package cs3500.tripletrios.View;

import cs3500.tripletrios.Model.WinningState;

import java.io.IOException;

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
  void dispalyFinalMessage(WinningState winner) throws IOException;
}