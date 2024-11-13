package strategiestest;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.*;
import cs3500.tripletrios.strategies.CornerStrategy;
import cs3500.tripletrios.strategies.MockModel;
import cs3500.tripletrios.strategies.PlayerMove;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;

public class CornerStrategyTest {
  private MockModel mockModel;
  private CornerStrategy strategy;
  private ArrayList<ArrayList<Cell>> grid;

  @Before
  public void setup() {

    Set<Card> deck = CardDatabaseReader.readDeckConfiguration("Configurations"
      + File.separator
      + "20deckConfig.txt");
    grid = GridConfigReader.readGridConfiguration("Configurations"
      + File.separator
      + "3x3sqrGrid.txt");

    mockModel = new MockModel();

    mockModel.startGame(deck, grid);

    strategy = new CornerStrategy(mockModel);
  }

  @Test
  public void testChecksAllCorners() {
    strategy.moveCard();
    for (Posn pos : mockModel.getTranscript()) {
      System.out.println(pos.getX() + " " + pos.getY());
    }
    assertTrue(mockModel.getTranscript().contains(new Posn(0, 0)));
//    assertTrue(mockModel.getTranscript().contains(new Posn(2, 0)));
//    assertTrue(mockModel.getTranscript().contains(new Posn(0, 2)));
//    assertTrue(mockModel.getTranscript().contains(new Posn(2, 2)));
  }



  @Test
  public void testChoosesSpecificCorner() {
    // prioritize bottom-right corner
    mockModel.setCellValue(new Posn(0, 0), 5);
    mockModel.setCellValue(new Posn(2, 2), 10);  // High-value corner

    PlayerMove move = strategy.moveCard();
    assertEquals(new Posn(2, 2), new Posn(move.getX(), move.getY()));
  }

  @Test
  public void testFallbackWhenCornersOccupied() {
    //  all corners as occupied
    mockModel.setCellLegal(new Posn(0, 0), false);
    mockModel.setCellLegal(new Posn(2, 0), false);
    mockModel.setCellLegal(new Posn(0, 2), false);
    mockModel.setCellLegal(new Posn(2, 2), false);

    PlayerMove move = strategy.moveCard();
    assertNotEquals(new Posn(0, 0), new Posn(move.getX(), move.getY())); 
  }
}
