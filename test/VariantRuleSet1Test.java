import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * This class tests the variant rule set 1 (reverse and fallen ace)
 */
public class VariantRuleSet1Test {
  private TripleTrioModel model;
  private Card card1;
  private Card card2;

  @Before
  public void setUp() {
    // Create cards with specific attack values for testing
    Map<Direction, Integer> values1 = new HashMap<>();
    values1.put(Direction.NORTH, 10);
    values1.put(Direction.SOUTH, 5);
    values1.put(Direction.EAST, 6);
    values1.put(Direction.WEST, 7);
    card1 = new CardImpl("card1", values1);
    card1.setCardColor(CardColor.RED);

    Map<Direction, Integer> values2 = new HashMap<>();
    values2.put(Direction.NORTH, 1);
    values2.put(Direction.SOUTH, 4);
    values2.put(Direction.EAST, 3);
    values2.put(Direction.WEST, 2);
    card2 = new CardImpl("card2", values2);
    card2.setCardColor(CardColor.BLUE);
  }

  @Test
  public void testReverseRule() {
    model = new TripleTrioVariantModel(true, false);

    // Test reverse rule where lower value captures higher value
    assertFalse(card1.getColor() == CardColor.BLUE);
    model.placeCard(0, 0, card1);
    model.placeCard(0, 1, card2);
    model.executeBattlePhase(0, 1);
    assertTrue(card1.getColor() == CardColor.BLUE);
  }

  @Test
  public void testFallenAceRule() {
    model = new TripleTrioVariantModel(false, true);
    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
            + File.separator
            + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
            .readGridConfiguration("Configurations"
                    + File.separator
                    + "3x3sqrGrid.txt");

    TripleTrioModel model = new TripleTrioGameModel(sampleDeck, sampleOrigGrid);
    model.startGame(sampleDeck, sampleOrigGrid);

    // Test Ace (1) capturing 10
    assertNotSame(card1.getColor(), CardColor.BLUE);
    model.placeCard(0, 0, card1);
    model.placeCard(0, 1, card2);
    model.executeBattlePhase(0, 1);
  }

  @Test
  public void testCombinedReverseAndFallenAce() {
    model = new TripleTrioVariantModel(true, true);

    // Test 10 capturing 1 in reverse fallen ace
    assertFalse(card2.getColor() == CardColor.RED);
    model.placeCard(0, 0, card1);
    model.placeCard(0, 1, card2);
    model.executeBattlePhase(0, 0);
  }

  @Test
  public void testNormalCapture() {
    model = new TripleTrioVariantModel(false, false);

    // Test normal capture where higher value captures lower
    assertFalse(card2.getColor() == CardColor.RED);
    model.placeCard(0, 0, card1);
    model.placeCard(0, 1, card2);
    model.executeBattlePhase(0, 0);
    assertTrue(card2.getColor() == CardColor.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCardPlacement() {
    model = new TripleTrioVariantModel(true, false);
    model.placeCard(0, 0, card1);
    model.placeCard(0, 0, card2); // Should throw exception for same position
  }
}