import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.controller.TripleTrioGUIController;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameViewImpl;
import cs3500.tripletrios.configReaders.CardDatabaseReader;
import cs3500.tripletrios.configReaders.GridConfigReader;

import java.io.File;
import java.io.IOException;
import java.util.*;

public final class ThreeTrios {
  public static void main(String[] args) throws IOException {

    Set<Card> sampleDeck = CardDatabaseReader.readDeckConfiguration("Configurations"
      + File.separator
      + "20deckConfig.txt");

    ArrayList<ArrayList<Cell>> sampleOrigGrid = GridConfigReader.readGridConfiguration("Configurations"
      + File.separator
      + "3x3sqrGrid.txt");

    TripleTrioModel model = new TripleTrioGameModel();
    model.startGame(sampleDeck, sampleOrigGrid);

    ArrayList<Card> redPlayerHand = model.getPlayer().getHand();
    ArrayList<Card> bluePlayerHand = model.getOppPlayer().getHand();

    model.placeCard(0,0, redPlayerHand.get(0));
    model.placeCard(2,2, bluePlayerHand.get(0));

    TTFrame view = new TTFrameViewImpl(model);

    TripleTrioController controller = new TripleTrioGUIController(view);
    controller.playGame(model);

  }

}