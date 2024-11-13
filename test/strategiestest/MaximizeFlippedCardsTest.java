package strategiestest;
import cs3500.tripletrios.configReaders.CardDatabaseReader;
import cs3500.tripletrios.configReaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.strategies.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class MaximizeFlippedCardsTest {

  private MockModel mockModel;
  private MaximizeFlippedCardsStrat strategy;
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

    strategy = new MaximizeFlippedCardsStrat(mockModel);
  }

  @Test
  public void testMoveCardFindsBestPlacement() {

    strategy.setFlipCountForMove(new Posn(1, 1),
      0,
      3);
    strategy.setFlipCountForMove(new Posn(2, 2),
      1,
      5);

    PlayerMove bestMove = strategy.moveCard();

    assertEquals(new PlayerMove(new Posn(2, 2), 1), bestMove);
  }
}
