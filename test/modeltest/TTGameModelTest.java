package modeltest;

import cs3500.tripletrios.model.CardDatabaseReader;
import cs3500.tripletrios.model.GridConfigReader;
import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Direction;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test class for the TripleTrioGame model.
 */
public class TTGameModelTest {
  private TripleTrioModel model;
  private Set<Card> deck;
  private ArrayList<ArrayList<Cell>> grid;


  @Before
  public void setUp() {
    model = new TripleTrioGameModel();
    Appendable output = new StringBuilder();
    Readable userInput = new StringReader("1 1 Bird\n");
    TripleTrioControllerImpl controller = new TripleTrioControllerImpl(userInput, output);
    CardDatabaseReader cardReader = new CardDatabaseReader();
    GridConfigReader gridReader = new GridConfigReader();

    deck = cardReader.readDeckConfiguration("Configurations"
      + File.separator
      + "20deckConfig.txt");
    grid = gridReader.readGridConfiguration("Configurations"
      + File.separator
      + "3x3sqrGrid.txt");

  }

  @Test
  public void testStartGame() {


    model.startGame(deck, grid);
    assertEquals(true, model.isGameStarted());
  }

  @Test
  public void testPlayGameInvalidGridSize() {
    try {
      Set<Card> deck = new HashSet<>();
      ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

      // Create an even-sized grid
      for (int i = 0; i < 4; i++) {
        ArrayList<Cell> row = new ArrayList<>();
        for (int j = 0; j < 4; j++) {
          row.add(new Cell(Cell.CellType.CARDCELL));
        }
        grid.add(row);
      }

      model.startGame(deck, grid);
      fail("Expected IllegalArgumentException for even grid size");
    } catch (IllegalArgumentException e) {
      assertEquals("The grid size must be odd", e.getMessage());
    }
  }

  @Test
  public void testPlayGameInsufficientDeckSize() {
    Set<Card> deck = new HashSet<>();
    ArrayList<ArrayList<Cell>> grid = new ArrayList<>();

    // Create an odd-sized grid
    for (int i = 0; i < 3; i++) {
      ArrayList<Cell> row = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        row.add(new Cell(Cell.CellType.CARDCELL));
      }
      grid.add(row);
    }


    Map<Direction, Integer> attackVals = new HashMap<>(Map.of());
    attackVals.put(Direction.NORTH, 1);
    attackVals.put(Direction.SOUTH, 2);
    attackVals.put(Direction.EAST, 3);
    attackVals.put(Direction.WEST, 4);
    deck.add(new CardImpl("Birdie", attackVals));

    try {
      model.startGame(deck, grid);
      fail("Expected IllegalStateException for insufficient deck size");
    } catch (IllegalStateException e) {
      assertEquals("Deck size must be greater than grid size", e.getMessage());
    }
  }

}
