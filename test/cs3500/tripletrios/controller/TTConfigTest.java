package cs3500.tripletrios.controller;

import cs3500.tripletrios.configReaders.CardDatabaseReader;
import cs3500.tripletrios.configReaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Direction;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test class for configuration files.
 */
public class TTConfigTest {
  private TripleTrioController controller;
  private CardDatabaseReader cardReader;
  private GridConfigReader gridReader;

  Appendable output = new StringBuilder();
  Readable reader = new BufferedReader(new InputStreamReader(System.in));

  /**
   * Set up for the tests.
   * @throws Exception if an exception is caught
   */
  @Before
  public void setUp() throws Exception {
    controller = new TripleTrioControllerImpl(reader, output);
    cardReader = new CardDatabaseReader();
    gridReader = new GridConfigReader();
  }


  @Test
  public void readGridConfiguration3x3SqrTest() {
    ArrayList<ArrayList<Cell>> actualGrid = gridReader.readGridConfiguration("Configurations"
        + File.separator
        + "3x3sqrGrid.txt");
    ArrayList<ArrayList<Cell>> expectedGrid = new ArrayList<>();

    for (int i = 0; i < 3; i++) {
      ArrayList<Cell> row = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        row.add(new Cell(Cell.CellType.CARDCELL));
      }
      expectedGrid.add(row);
    }

    assertEquals(expectedGrid, actualGrid);
  }

  @Test
  public void readGridConfiguration3x3SwHoleTest() {
    ArrayList<ArrayList<Cell>> actualGrid = gridReader.readGridConfiguration("Configurations"
        + File.separator
        + "3x3wHoleGrid.txt");

    ArrayList<ArrayList<Cell>> expectedGrid = new ArrayList<>();
    ArrayList<Cell> row1 = new ArrayList<>();

    row1.add(new Cell(Cell.CellType.CARDCELL));
    row1.add(new Cell(Cell.CellType.CARDCELL));
    row1.add(new Cell(Cell.CellType.CARDCELL));
    ArrayList<Cell> row2 = new ArrayList<>();

    row2.add(new Cell(Cell.CellType.CARDCELL));
    row2.add(new Cell(Cell.CellType.HOLE));
    row2.add(new Cell(Cell.CellType.CARDCELL));
    ArrayList<Cell> row3 = new ArrayList<>();

    row3.add(new Cell(Cell.CellType.CARDCELL));
    row3.add(new Cell(Cell.CellType.CARDCELL));
    row3.add(new Cell(Cell.CellType.CARDCELL));
    expectedGrid.add(row1);
    expectedGrid.add(row2);
    expectedGrid.add(row3);


    assertEquals(expectedGrid, actualGrid);
  }

  @Test
  public void readDeckConfiguration20deckConfigTest() {
    Set<Card> actualDeck = cardReader.readDeckConfiguration("Configurations"
        + File.separator
        + "3cardDeck");

    Set<Card> expectedDeck = new HashSet<>();

    Map<Direction, Integer> birdValues = new HashMap<>();
    birdValues.put(Direction.NORTH, 1);
    birdValues.put(Direction.SOUTH, 2);
    birdValues.put(Direction.EAST, 3);
    birdValues.put(Direction.WEST, 4);
    expectedDeck.add(new CardImpl("Bird", birdValues));

    Map<Direction, Integer> jagValues = new HashMap<>();
    jagValues.put(Direction.NORTH, 9);
    jagValues.put(Direction.SOUTH, 8);
    jagValues.put(Direction.EAST, 9);
    jagValues.put(Direction.WEST, 4);
    expectedDeck.add(new CardImpl("Jaguar", jagValues));

    Map<Direction, Integer> cheetahValues = new HashMap<>();
    cheetahValues.put(Direction.NORTH, 2);
    cheetahValues.put(Direction.SOUTH, 4);
    cheetahValues.put(Direction.EAST, 9);
    cheetahValues.put(Direction.WEST, 3);
    expectedDeck.add(new CardImpl("Cheetah", cheetahValues));

    assertEquals(actualDeck, expectedDeck);
  }
}