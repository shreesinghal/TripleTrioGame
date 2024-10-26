package cs3500.tripletrios.View;

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
}