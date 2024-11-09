package cs3500.tripletrios.controller;

import cs3500.tripletrios.ConfigReaders.CardDatabaseReader;
import cs3500.tripletrios.ConfigReaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

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

    // read from config files
    GridConfigReader gridReader = new GridConfigReader();
    ArrayList<ArrayList<Cell>> grid = gridReader.readGridConfiguration(gridPath);

    CardDatabaseReader cardReader = new CardDatabaseReader();
    Set<Card> deck = cardReader.readDeckConfiguration(deckPath);

    model.startGame(deck, grid);

    view.makeVisible();
  }

  /**
   * Handle an action in a single cell of the board, such as to make a move.
   *
   * @param row the row of the clicked cell
   * @param col the column of the clicked cell
   */
  public void handleCellClick(int row, int col) {
//    model.placeCard();
    view.refresh();
  }
}
