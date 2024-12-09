import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
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

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class tests the hint decorator class that adds hints when h is pressed.
 */
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

    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
            + File.separator
            + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
            .readGridConfiguration("Configurations"
                    + File.separator
                    + "3x3sqrGrid.txt");

    TripleTrioModel model = new TripleTrioGameModel(sampleDeck, sampleOrigGrid);
    model.startGUIGame();
    Player player = model.getPlayer();
    TTFrame view = new TTFrameViewImpl(model, player);
    TripleTrioFeatureController controller = new TripleTrioHumanPlayerContr(model, player, view);

    // Test hint toggle
    Assert.assertFalse(controller.areHintsEnabled(CardColor.RED));
    controller.toggleHints(CardColor.RED);
    Assert.assertTrue(controller.areHintsEnabled(CardColor.RED));
  }
}