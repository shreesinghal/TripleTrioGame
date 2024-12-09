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

import static org.junit.Assert.assertTrue;

/**
 *  This class tests the combined variants model.
 */
public class CombinedVariantRulesTest {
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
    // Create test cards with specific values for testing both rule sets
    Map<Direction, Integer> values1 = new HashMap<>();
    values1.put(Direction.NORTH, 1);  // For Fallen Ace
    values1.put(Direction.SOUTH, 4);
    values1.put(Direction.EAST, 7);   // For Same rule
    values1.put(Direction.WEST, 7);   // For Same rule
    card1 = new CardImpl("card1", values1);
    card1.setCardColor(CardColor.RED);

    Map<Direction, Integer> values2 = new HashMap<>();
    values2.put(Direction.NORTH, 10); // For Fallen Ace
    values2.put(Direction.SOUTH, 3);
    values2.put(Direction.EAST, 7);   // For Same rule
    values2.put(Direction.WEST, 2);
    card2 = new CardImpl("card2", values2);
    card2.setCardColor(CardColor.BLUE);

    Map<Direction, Integer> values3 = new HashMap<>();
    values3.put(Direction.NORTH, 5);
    values3.put(Direction.SOUTH, 5);  // For Plus rule sum
    values3.put(Direction.EAST, 7);   // For Same rule
    values3.put(Direction.WEST, 3);
    card3 = new CardImpl("card3", values3);
    card3.setCardColor(CardColor.BLUE);
  }

  @Test
  public void testReverseWithSame() {
    model = new TripleTrioCombinedModel(sampleDeck, sampleOrigGrid, false, false, true, false);

    // Test Reverse rule with Same rule
    model.placeCard(1, 1, card1);  // Center
    model.placeCard(1, 0, card2);  // North
    model.placeCard(2, 1, card3);  // East

    // Cards should flip due to Same rule (all have East=7)
    // and then Reverse rule should apply for normal battles
    assertTrue(card2.getColor() == CardColor.RED);
    assertTrue(card3.getColor() == CardColor.RED);
  }

  @Test
  public void testFallenAceWithPlus() {
    model = new TripleTrioCombinedModel(sampleDeck, sampleOrigGrid, false, true, false, true);

    // Test Fallen Ace rule with Plus rule
    model.placeCard(1, 1, card1);
    model.placeCard(1, 0, card2);

    // Card should flip due to Plus rule sums
    // and Fallen Ace should apply (1 captures 10)
    assertTrue(card2.getColor() == CardColor.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRuleCombination() {
    // Cannot enable both Same and Plus rules
    model = new TripleTrioCombinedModel(sampleDeck, sampleOrigGrid,true, true, true, true);
  }

  @Test
  public void testReverseAndFallenAceWithSame() {
    model = new TripleTrioCombinedModel(sampleDeck, sampleOrigGrid,true, true, true, false);

    // Test both Set 1 rules with Same rule
    model.placeCard(1, 1, card1);
    model.placeCard(1, 0, card2);

    // Should apply Same rule first, then combined Reverse/Fallen Ace
    assertTrue(card2.getColor() == CardColor.RED);
  }
}
