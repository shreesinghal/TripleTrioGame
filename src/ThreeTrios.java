import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.controller.TripleTrioGUIController;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameImpl;
import cs3500.tripletrios.view.TripleTrioTextView;
import cs3500.tripletrios.view.TripleTrioView;

import java.io.File;
import java.io.IOException;

public final class ThreeTrios {
  public static void main(String[] args) throws IOException {

    /*
      public TripleTrioGameModel(ArrayList<ArrayList<Cell>> grid,
                             ArrayList<ArrayList<Cell>> originalGrid,
                             Set<Card> deck, Player currPlayer,
                             Player opposingPlayer,
                             boolean gameStarted,
                             ArrayList<Card> deckList) {
     */

    TripleTrioModel model = new TripleTrioGameModel( /* many params */ );
    TTFrame view = new TTFrameImpl(model); //add the GUI based view
    TripleTrioController controller = new TripleTrioGUIController(view);

    /*
    ASK: how to call/create all components of the game in the main
     */
    controller.playGame(model, "Configurations"
        + File.separator
        + "20deckConfig.txt",
      "Configurations"
        + File.separator
        + "3x3sqrGrid.txt");
  }
}