package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;
import cs3500.tripletrios.Model.CardImpl;
import cs3500.tripletrios.Model.Cell;
import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioTextView;

import java.io.IOException;
import java.util.*;

public class TripleTrioControllerImpl implements TripleTrioController {

  private TripleTrioModel model;

  private TripleTrioTextView view;

  private Readable userInput;

  private Appendable output;

  private Scanner gameScanner;

  private ArrayList<ArrayList<Cell>> grid;

  private Set<Card> deck;

  private boolean gameStarted;
  private boolean gameOver;
  private Scanner scanner;

  public TripleTrioControllerImpl(Readable userInput, Appendable output) {
    if (userInput == null || output == null) {
      throw new IllegalArgumentException("User input or output cannot be null");
    }

    this.userInput = userInput;
    this.output = output;
    this.scanner = new Scanner(this.userInput);
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
    gameStarted = true;

    view.render();

    //try {
      while (!model.isGameOver()  && model.isGameStarted()) {
        output.append("\nEnter your next move in the format [x-position] [y-position] [card name]: \n");

        String[] inputText = scanner.next().split(" ");

        playMove(inputText);

      }
    //} catch (IllegalStateException | IllegalArgumentException e) {
      //throw new IllegalStateException("Error in playing the game.");
    //}

  }

  private void playMove(String[] inputText) {
    int x_position = Integer.parseInt(inputText[0]);
    int y_position = Integer.parseInt(inputText[1]);
    String cardName = inputText[2];

    ArrayList<Card> playerHand = model.getPlayer().getHand();
    Card cardToPlace = null;
    for (Card card : playerHand) {
      if (card.getName().equals(cardName)) {
        model.getPlayer().removeCardFromHand((CardImpl) card);
        cardToPlace = card;
      }
    }
    
    model.placeCard(x_position, y_position, (CardImpl) cardToPlace);

    model.executeBattlePhase(x_position, y_position);
    

  }


}
