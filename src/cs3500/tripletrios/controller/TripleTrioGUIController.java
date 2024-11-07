package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;

public class TripleTrioGUIController implements TripleTrioController {
  TripleTrioModel model;
  TTFrame view;

  public TripleTrioGUIController(TTFrame view) {
    if (view == null) {
      throw new IllegalArgumentException("view cannot be null");
    }
    this.view = view;
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
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }
    this.model = model;
    view.makeVisible();
  }
}
