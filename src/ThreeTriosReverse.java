import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.*;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class ThreeTriosReverse {
  public static void main(String[] args) {
    // Read configurations
    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
            + File.separator
            + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
            .readGridConfiguration("Configurations"
                    + File.separator
                    + "3x3sqrGrid.txt");

    // Parse command line arguments
    boolean reverse = false;
    boolean fallenAce = false;
    for (String arg : args) {
      switch (arg.toLowerCase()) {
        case "--reverse":
          reverse = true;
          break;
        case "--fallen-ace":
          fallenAce = true;
          break;
      }
    }

    // Select and initialize model based on arguments
    TripleTrioModel model;
    if (reverse || fallenAce) {
      model = new TripleTrioVariantModel(reverse, fallenAce);
    } else {
      model = new TripleTrioGameModel();
    }

    // Initialize the game with deck and grid
    model.startGame(sampleDeck, sampleOrigGrid);

    // Create players
    Player player1 = new PlayerHumanImpl(model.getPlayer().getHand(), CardColor.RED);
    Player player2 = new PlayerHumanImpl(model.getOppPlayer().getHand(), CardColor.BLUE);

    // Create views
    TTFrame viewPlayer1 = new TTFrameViewImpl(model, player1);
    TTFrame viewPlayer2 = new TTFrameViewImpl(model, player2);

    // Create and set up controllers
    TripleTrioFeatureController controller1 = new TripleTrioHumanPlayerContr(model,
            player1,
            viewPlayer1);
    TripleTrioFeatureController controller2 = new TripleTrioHumanPlayerContr(model,
            player2,
            viewPlayer2);

    // Add listeners
    viewPlayer1.addListeners(controller1);
    viewPlayer2.addListeners(controller2);
  }
}