package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;
import cs3500.tripletrios.Model.Cell;
import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioTextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class TripleTrioControllerImpl implements TripleTrioController {

  private TripleTrioModel model;

  private TripleTrioTextView view;

  private Readable userInput;

  private Appendable output;

  private Scanner gameScanner;

  private ArrayList<ArrayList<Cell>> grid;

  private Set<Card> deck;

  public TripleTrioControllerImpl(Readable userInput, Appendable output) {
    if (userInput == null || output == null) {
      throw new IllegalArgumentException("User input or output cannot be null");
    }

    this.userInput = userInput;
    this.output = output;

  }

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
    this.view = new TripleTrioTextView(model, this.output);
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

    view.render();


  }
}
