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
import static org.junit.Assert.assertTrue;

/**
 * This class tests the variant rule set 2 (same and plus)
 */
public class VariantRuleSet2Test {
  private TripleTrioModel model;
  private Card card1;
  private Card card2;
  private Card card3;
  private Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
          + File.separator
          + "20deckConfig.txt");

  private ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
          .readGridConfiguration("Configurations"
                  + File.separator
                  + "3x3sqrGrid.txt");

  @Before
  public void setUp() {
    // Create cards for testing Same and Plus rules
    Map<Direction, Integer> values1 = new HashMap<>();
    values1.put(Direction.NORTH, 7);
    values1.put(Direction.SOUTH, 3);
    values1.put(Direction.EAST, 10);
    values1.put(Direction.WEST, 9);
    card1 = new CardImpl("card1", values1);
    card1.setCardColor(CardColor.RED);

    Map<Direction, Integer> values2 = new HashMap<>();
    values2.put(Direction.NORTH, 3);
    values2.put(Direction.SOUTH, 7);
    values2.put(Direction.EAST, 9);
    values2.put(Direction.WEST, 4);
    card2 = new CardImpl("card2", values2);
    card2.setCardColor(CardColor.BLUE);

    Map<Direction, Integer> values3 = new HashMap<>();
    values3.put(Direction.NORTH, 8);
    values3.put(Direction.SOUTH, 4);
    values3.put(Direction.EAST, 1);
    values3.put(Direction.WEST, 9);
    card3 = new CardImpl("card3", values3);
    card3.setCardColor(CardColor.BLUE);
  }

  @Test
  public void testSameRule() {
    model = new TripleTrioSamePlusModel(sampleDeck, sampleOrigGrid, true, false);

    // Test Same rule where matching values in opposing directions trigger flips
    model.placeCard(1, 1, card1);  // Center
    model.placeCard(1, 0, card2);  // North
    model.placeCard(2, 1, card3);  // East

    // card2 and card3 should flip to red because they share same values
    assertTrue(card2.getColor() == CardColor.RED);
    assertTrue(card3.getColor() == CardColor.RED);
  }

  @Test
  public void testPlusRule() {
    model = new TripleTrioSamePlusModel(sampleDeck, sampleOrigGrid, false, true);

    // Test Plus rule where sums of opposing values match
    model.placeCard(1, 1, card1);  // Center
    model.placeCard(1, 0, card2);  // North
    model.placeCard(2, 1, card3);  // East

    // Cards should flip if their sums with placed card match
    assertTrue(card2.getColor() == CardColor.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRuleCombination() {
    // Cannot enable both Same and Plus rules
    model = new TripleTrioSamePlusModel(sampleDeck, sampleOrigGrid, true, true);
  }

  @Test
  public void testNoSpecialRules() {
    model = new TripleTrioSamePlusModel(sampleDeck, sampleOrigGrid, false, false);

    // Test normal capture behavior when no special rules are active
    model.placeCard(1, 1, card1);
    model.placeCard(1, 0, card2);

    // Only normal captures should occur
    assertFalse(card2.getColor() == CardColor.RED);
  }
}