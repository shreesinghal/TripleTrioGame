package viewtest;

import cs3500.tripletrios.model.CardDatabaseReader;
import cs3500.tripletrios.model.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Direction;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;
import cs3500.tripletrios.view.TripleTrioTextView;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test TripleTrio view.
 */
public class TTTextViewTest {

  private TripleTrioModel model;
  private Set<Card> deck;
  private ArrayList<ArrayList<Cell>> grid;
  private TripleTrioTextView view;
  private Appendable output;
  Map<Direction, Integer> birdValues;

  @Before
  public void setUp() {
    model = new TripleTrioGameModel();
    output = new StringBuilder();

    CardDatabaseReader cardReader = new CardDatabaseReader();
    GridConfigReader gridReader = new GridConfigReader();

    deck = cardReader.readDeckConfiguration("Configurations"
      + File.separator
      + "20deckConfig.txt");
    grid = gridReader.readGridConfiguration("Configurations"
      + File.separator
      + "3x3sqrGrid.txt");

    view = new TripleTrioTextView(model, output);

    birdValues = new HashMap<>();
    birdValues.put(Direction.NORTH, 1);
    birdValues.put(Direction.SOUTH, 2);
    birdValues.put(Direction.EAST, 3);
    birdValues.put(Direction.WEST, 4);
  }

  @Test
  public void testInitialDisplay() {
    model.startGame(deck, grid);
    String expectedOutput = "Player: RED\n"
        + "___\n"
        + "___\n"
        + "___\n"
        + "Hand: \n";

    String[] actual = view.toString().split("\n");
    StringBuilder actualOutput = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      actualOutput.append(actual[i]).append("\n");
    }
    assertEquals(expectedOutput, actualOutput.toString());
  }

  @Test
  public void testDisplayAfterCardPlacement() {
    model.startGame(deck, grid);
    model.placeCard(0, 0, new CardImpl("Bird", birdValues));
    String expectedOutput = "Player: RED\n"
        + "___\n"
        + "___\n"
        + "___\n"
        + "Hand: \n";

    String[] actual = view.toString().split("\n");
    StringBuilder actualOutput = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      actualOutput.append(actual[i]).append("\n");
    }
    assertEquals(expectedOutput, actualOutput.toString());
  }

  @Test
  public void testDisplayFinalMessage() throws IOException {
    model.startGame(deck, grid);
    view.displayFinalMessage(WinningState.RedWins);
    String expectedOutput = "\nPlayer Red wins!"
        + "___\n"
        + "___\n"
        + "___\n";

    assertEquals(expectedOutput, output.toString());
  }
}
