package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.TripleTrioModel;

import java.io.IOException;

/**
 * Behaviors needed for the controller to delegate
 * between the model and the view.
 */
public interface TripleTrioController {

    /**
     * Play a new game of Triple Trio with the given configurations.
     * @param model a triple trio model
     * @param deckPath deckPath the path to the deck
     * @param gridPath gridPath the path to the grid
     * @throws IOException if something in the game is displayed wrong.
     */
  void playGame(TripleTrioModel model, String deckPath, String gridPath) throws IOException;
}