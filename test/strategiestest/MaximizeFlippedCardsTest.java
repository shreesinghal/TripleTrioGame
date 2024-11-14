package strategiestest;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.strategies.MaximizeFlippedCardsStrat;
import cs3500.tripletrios.strategies.MockModel;
import cs3500.tripletrios.strategies.PlayerMove;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Test class to test the Maximize Flipped Cards strategy.
 */
public class MaximizeFlippedCardsTest {


  private MaximizeFlippedCardsStrat strategy;


  @Before
  public void setup() {

    ArrayList<ArrayList<Cell>> grid;
    MockModel mockModel;

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

  @Test
  public void testMoveCardFindsBestPlacementWhenRollbackShouldBeCalled() {


    strategy.setFlipCountForMove(new Posn(1, 1),
        0,
        0);
    strategy.setFlipCountForMove(new Posn(2, 2),
        1,
        0);

    PlayerMove bestMove = strategy.moveCard();

    assertEquals(new PlayerMove(new Posn(1,1), 0), bestMove);
  }
}
