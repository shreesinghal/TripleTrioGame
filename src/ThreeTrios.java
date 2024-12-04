import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;
import cs3500.threetrios.providers.model.TripleTriad;
import cs3500.threetrios.providers.view.ViewAdapterTheirsToOurs;
import cs3500.threetrios.providers.view.tripletriadgui.ThreeTriosView;
import cs3500.threetrios.providers.view.tripletriadgui.TripleTriadView;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.PlayerHumanImpl;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.threetrios.providers.model.ModelAdapterOursToTheirs;
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
public final class ThreeTrios {

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


    // first make our model
    TripleTrioModel ourModel = new TripleTrioGameModel(sampleDeck, sampleOrigGrid);

    // make their model
    TripleTriad theirModel = new ModelAdapterOursToTheirs(ourModel);

    Player player1 = new PlayerHumanImpl(ourModel.getPlayer().getHand(), CardColor.RED);
    Player player2 = new PlayerHumanImpl(ourModel.getOppPlayer().getHand(), CardColor.BLUE);

    // make their view
    TripleTriadView providerView1 = new ThreeTriosView(theirModel, "Player 1 View");
    TripleTriadView providerView2 = new ThreeTriosView(theirModel, "Player 2 View");

    // make our view
    TTFrame ourViewPlayer1 = new ViewAdapterTheirsToOurs(providerView1, theirModel);
    TTFrame viewPlayer2 = new ViewAdapterTheirsToOurs(providerView2, theirModel);

    // make our controller
    TripleTrioFeatureController controller1 = new TripleTrioHumanPlayerContr(ourModel,
      player1,
      ourViewPlayer1);

    TripleTrioFeatureController controller2 = new TripleTrioHumanPlayerContr(ourModel,
            player2,
            viewPlayer2);
    ourViewPlayer1.addListeners(controller1);
    viewPlayer2.addListeners(controller2);
  }
}