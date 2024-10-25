package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioView;

import java.io.FileReader;

public interface TripleTrioController {

    /**
     *
     * Play a new game of Triple Trio with the given configurations.
     *
     * @param model a triple trio model
     * @param fileReader a file reader
     */
    void playGame(TripleTrioModel model, FileReader fileReader);

}
