import cs3500.tripletrios.controller.TripleTrioAIPlayerContr;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.PlayerAIImpl;
import cs3500.tripletrios.model.PlayerHumanImpl;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.strategies.CornerStrategy;
import cs3500.tripletrios.strategies.MaximizeFlippedCardsStrat;
import cs3500.tripletrios.strategies.TripleTrioStrategy;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;
import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

/**
 * Main class for running the game with the GUI view.
 * This main class will create a whole visual game with a GUI
 * view for the users to interact with and play the game.
 */
public final class ThreeTriosHumanAI2 {

  /**
   * Main method where we play the TripleTrio game.
   * @param args the arguments a user passes in
   */
  public static void main(String[] args) {

    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
      + File.separator
      + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
      .readGridConfiguration("Configurations"
        + File.separator
        + "3x3sqrGrid.txt");

    TripleTrioModel model = new TripleTrioGameModel(sampleDeck, sampleOrigGrid);
    model.startGUIGame();
    Player player1 = new PlayerHumanImpl(model.getPlayer().getHand(), CardColor.RED);
    TripleTrioStrategy cornerStrategy = new MaximizeFlippedCardsStrat(model);
    PlayerAIImpl aiPlayer2 = new PlayerAIImpl(model.getOppPlayer().getHand(),
      CardColor.BLUE, cornerStrategy);
    TTFrame viewPlayer1 = new TTFrameViewImpl(model, player1);
    TTFrame viewAIPlayer2 = new TTFrameViewImpl(model, aiPlayer2);
    TripleTrioFeatureController controller1 = new TripleTrioHumanPlayerContr(model,
      player1,
      viewPlayer1);
    TripleTrioFeatureController controller2 = new TripleTrioAIPlayerContr(model,
      aiPlayer2,
      viewAIPlayer2);
    viewPlayer1.addListeners(controller1);
    viewAIPlayer2.addListeners(controller2);

  }

}