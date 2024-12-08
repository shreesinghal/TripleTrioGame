import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.PlayerHumanImpl;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.TripleTrioSamePlusModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class ThreeTriosSamePlus {
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
    boolean sameRule = false;
    boolean plusRule = false;
    for (String arg : args) {
      switch (arg.toLowerCase()) {
        case "same":
          sameRule = true;
          break;
        case "plus":
          plusRule = true;
          break;
      }
    }

    // Create appropriate model based on arguments
    TripleTrioModel model;
    if (sameRule || plusRule) {
      model = new TripleTrioSamePlusModel(sampleDeck, sampleOrigGrid, sameRule, plusRule);
    } else {
      model = new TripleTrioGameModel(sampleDeck, sampleOrigGrid);
    }


    model.startGUIGame();


    Player player1 = new PlayerHumanImpl(model.getPlayer().getHand(), CardColor.RED);
    Player player2 = new PlayerHumanImpl(model.getOppPlayer().getHand(), CardColor.BLUE);


    TTFrame viewPlayer1 = new TTFrameViewImpl(model, player1);
    TTFrame viewPlayer2 = new TTFrameViewImpl(model, player2);


    TripleTrioFeatureController controller1 = new TripleTrioHumanPlayerContr(model,
            player1,
            viewPlayer1);
    TripleTrioFeatureController controller2 = new TripleTrioHumanPlayerContr(model,
            player2,
            viewPlayer2);

    viewPlayer1.addListeners(controller1);
    viewPlayer2.addListeners(controller2);

  }
}
