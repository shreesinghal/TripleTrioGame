package cs3500.tripletrios.controller;

import cs3500.tripletrios.configReaders.CardDatabaseReader;
import cs3500.tripletrios.configReaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Color;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TripleTrioTextView;
import cs3500.tripletrios.view.TripleTrioView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * Controller class that implements the controller interface and takes user input
 * and communicates it to the TripleTrioGame mode.
 */
public class TripleTrioControllerImpl implements TripleTrioController {

  private TripleTrioModel model;


  private Readable userInput;

  private Appendable output;

  private Scanner scanner;

  /**
   * Constructor that instantiates a controller with user input and output.
   * @param userInput user input.
   * @param output output for the view.
   */
  public TripleTrioControllerImpl(Readable userInput, Appendable output) {
    if (userInput == null || output == null) {
      throw new IllegalArgumentException("User input or output cannot be null");
    }

    this.userInput = userInput;
    this.output = output;
    this.scanner = new Scanner(this.userInput);
  }


  /**
   * Play a new game of Triple Trio with the given configurations.
   *
   * @param model a triple trio model
   * @param deckPath the path to the deck
   * @param gridPath the path to the grid
   */
  @Override
  public void playGame(TripleTrioModel model, String deckPath, String gridPath) throws IOException {

    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    // sets MVC
    this.model = model;
    TripleTrioView view = new TripleTrioTextView(model, this.output);
    this.scanner = new Scanner(userInput);

    // read from config files
    GridConfigReader gridReader = new GridConfigReader();
    ArrayList<ArrayList<Cell>> grid = gridReader.readGridConfiguration(gridPath);

    CardDatabaseReader cardReader = new CardDatabaseReader();
    Set<Card> deck = cardReader.readDeckConfiguration(deckPath);

    tryStartGame(model, view, grid, deck);
  }

  /**
   * Handles an action when a player presses a card on the hand.
   */
  @Override
  public void handleCellClickForHand(int cardNum, Color color) {
    //nothing implemented for the console controller
  }

  /**
   * Handles an action when a player presses a grid cell.
   */
  @Override
  public void handleCellClickForGrid(int i, int pixelToCellVert) {
    //nothing implemented for the console controller
  }

  /**
   * Play a game of Triple Trios given a model with initial conditions.
   *
   * @param model a triple trio model
   */
  @Override
  public void playGame(TripleTrioModel model) throws IOException {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    // sets MVC
    this.model = model;
    TripleTrioView view = new TripleTrioTextView(model, this.output);
    this.scanner = new Scanner(userInput);

    // read from config files
    ArrayList<ArrayList<Cell>> grid = model.getOriginalGrid();

    Set<Card> deck = model.getDeck();

    tryStartGame(model, view, grid, deck);
  }


  private void tryStartGame(TripleTrioModel model, TripleTrioView view, ArrayList<ArrayList<Cell>> grid, Set<Card> deck) throws IOException {
    try {
      model.startGame(deck, grid);
    } catch (IllegalStateException | IllegalArgumentException e) {
      output.append(e.getMessage());
    }

    while (!model.isGameOver()) {

      view.render();
      String[] inputText = scanner.nextLine().split(" ");
      try {
        playMove(inputText);
        this.model.switchTurns();
      } catch (IllegalArgumentException e) {
        output.append(e.getMessage()).append("\nTry again.\n\n");
      }

    }

    view.displayFinalMessage(model.determineWinner());
  }


  private void playMove(String[] inputText) throws IOException {
    int x_position = Integer.parseInt(inputText[0]);
    int y_position = Integer.parseInt(inputText[1]);
    String cardName = inputText[2];

    boolean found = false;
    ArrayList<Card> playerHand = model.getPlayer().getHand();
    Card cardToPlace = null;
    for (Card card : playerHand) {
      if (card.getName().equals(cardName)) {
        model.getPlayer().removeCardFromHand(card);
        cardToPlace = card;
        found = true;
        break;
      }
    }

    if (!found) {
      throw new IllegalArgumentException("Card not found.");
    }

    model.placeCard(x_position - 1, y_position - 1, (CardImpl) cardToPlace);
    model.executeBattlePhase(x_position - 1, y_position - 1);
  }

}