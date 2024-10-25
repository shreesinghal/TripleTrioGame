package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;
import cs3500.tripletrios.Model.Cell;
import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioView;
import cs3500.tripletrios.View.TripleTrioViewImpl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class TripleTrioControllerImpl implements TripleTrioController {

  private TripleTrioModel model;
  private TripleTrioViewImpl view;
  private Appendable output;
  private Scanner gameScanner;
  private Readable userInput;
  private ArrayList<ArrayList<Cell>> grid;
  private Set<Card> deck;

  /**
   *
   * Play a new game of Triple Trio with the given configurations.
   *
   * @param model a triple trio model
   * @param deckPath the path to the deck
   * @param gridPath the path to the grid
   */
  @Override
  public void playGame(TripleTrioModel model, String deckPath, String gridPath) throws IOException {    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    // sets MVC
    this.model = model;
    this.view = new TripleTrioViewImpl(model, this.output);
    this.gameScanner = new Scanner(userInput);

    // read from config files
    GridConfigReader gridReader = new GridConfigReader();
    grid = gridReader.readGridConfiguration(gridPath);

    CardDatabaseReader cardReader = new CardDatabaseReader();
    deck = cardReader.readDeckConfiguration(deckPath);

    try {
      model.startGame(deck, grid);
    } catch (IllegalStateException | IllegalArgumentException e) {
      output.append(e.getMessage());
    }

  }
}
