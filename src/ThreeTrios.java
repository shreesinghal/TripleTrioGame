import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;
import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;

import java.io.File;
import java.io.IOException;
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
   * @throws IOException if something goes wrong with the game
   */
  public static void main(String[] args) throws IOException {

    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
        + File.separator
        + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader
            .readGridConfiguration("Configurations"
        + File.separator
        + "3x3sqrGrid.txt");

    TripleTrioModel model = new TripleTrioGameModel();
    model.startGame(sampleDeck, sampleOrigGrid);

    ArrayList<Card> redPlayerHand = model.getPlayer().getHand();
    ArrayList<Card> bluePlayerHand = model.getOppPlayer().getHand();

    model.placeCard(0,0, redPlayerHand.get(0));
    model.placeCard(2,2, bluePlayerHand.get(0));

    TTFrame view = new TTFrameViewImpl(model);

    TripleTrioFeatureController controller = new TripleTrioHumanPlayerContr(view);
    //controller.playGameWithModel(model);

  }

}