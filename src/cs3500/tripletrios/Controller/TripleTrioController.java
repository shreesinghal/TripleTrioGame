package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioView;

import java.io.FileReader;
import java.io.IOException;

public interface TripleTrioController {

    /**
     *
     * Play a new game of Triple Trio with the given configurations.
     *
     * @param model a triple trio model
     * @param deckPath the path to the deck
     * @param gridPath the path to the grid
     */
    void playGame(TripleTrioModel model, String deckPath, String gridPath) throws IOException;
}
