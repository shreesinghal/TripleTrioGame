import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.*;
import cs3500.tripletrios.view.GridPanel;
import cs3500.tripletrios.view.GridPanelHintDecorator;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class GridPanelHintDecoratorTest {
  private TripleTrioModel model;
  private GridPanel gridPanel;
  private GridPanelHintDecorator decorator;
  private Card testCard;

  @Before
  public void setUp() {
    model = new TripleTrioGameModel();
    gridPanel = new GridPanel(model);
    decorator = new GridPanelHintDecorator(gridPanel, model);

    // Create card using CardImpl
    Map<Direction, Integer> attackValues = new HashMap<>();
    attackValues.put(Direction.NORTH, 1);
    attackValues.put(Direction.SOUTH, 2);
    attackValues.put(Direction.EAST, 3);
    attackValues.put(Direction.WEST, 4);
    testCard = new CardImpl("test", attackValues);
    testCard.setCardColor(CardColor.RED);
  }

  @Test
  public void testHintDecoratorInitialization() {
    Assert.assertNotNull(decorator);
    Assert.assertFalse(decorator.isEnabled());
  }

  @Test
  public void testEnableDisableHints() {
    decorator.setEnabled(true);
    Assert.assertTrue(decorator.isEnabled());

    decorator.setEnabled(false);
    Assert.assertFalse(decorator.isEnabled());
  }

  @Test
  public void testControllerIntegration() {
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
    model.startGame(deck, grid);

    Map<Direction, Integer> aa = new HashMap<>(Map.of());
    aa.put(Direction.NORTH, 1);
    aa.put(Direction.SOUTH, 2);
    aa.put(Direction.EAST, 3);
    aa.put(Direction.WEST, 4);
    ArrayList<Card> cards = new ArrayList<>();
    cards.add(new CardImpl("card1", aa));
    Player player = new PlayerHumanImpl(cards,
            CardColor.RED);
    TTFrame view = new TTFrameViewImpl(model, player);
    TripleTrioFeatureController controller = new TripleTrioHumanPlayerContr(model, player, view);

    // Test hint toggle
    Assert.assertFalse(controller.areHintsEnabled(CardColor.RED));
    controller.toggleHints(CardColor.RED);
    Assert.assertTrue(controller.areHintsEnabled(CardColor.RED));
  }
}