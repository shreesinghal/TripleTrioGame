import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.controller.TripleTrioGUIController;
import cs3500.tripletrios.model.*;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameImpl;
import cs3500.tripletrios.view.TripleTrioTextView;
import cs3500.tripletrios.view.TripleTrioView;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

//    ArrayList<ArrayList<Cell>> expectedGrid = new ArrayList<>();
//    ArrayList<Cell> row1 = new ArrayList<>();
//
//    row1.add(new Cell(Cell.CellType.CARDCELL));
//    row1.add(new Cell(Cell.CellType.CARDCELL));
//    row1.add(new Cell(Cell.CellType.CARDCELL));
//    ArrayList<Cell> row2 = new ArrayList<>();
//
//    row2.add(new Cell(Cell.CellType.CARDCELL));
//    row2.add(new Cell(Cell.CellType.HOLE));
//    row2.add(new Cell(Cell.CellType.CARDCELL));
//    ArrayList<Cell> row3 = new ArrayList<>();
//
//    row3.add(new Cell(Cell.CellType.CARDCELL));
//    row3.add(new Cell(Cell.CellType.CARDCELL));
//    row3.add(new Cell(Cell.CellType.CARDCELL));
//    expectedGrid.add(row1);
//    expectedGrid.add(row2);
//    expectedGrid.add(row3);
//
//    Set<Card> expectedDeck = new HashSet<>();
//
//    Map<Direction, Integer> birdValues = new HashMap<>();
//    birdValues.put(Direction.NORTH, 1);
//    birdValues.put(Direction.SOUTH, 2);
//    birdValues.put(Direction.EAST, 3);
//    birdValues.put(Direction.WEST, 4);
//    expectedDeck.add(new CardImpl("Bird", birdValues));
//
//    Map<Direction, Integer> jagValues = new HashMap<>();
//    jagValues.put(Direction.NORTH, 9);
//    jagValues.put(Direction.SOUTH, 8);
//    jagValues.put(Direction.EAST, 9);
//    jagValues.put(Direction.WEST, 4);
//    expectedDeck.add(new CardImpl("Jaguar", jagValues));
//
//    Map<Direction, Integer> cheetahValues = new HashMap<>();
//    cheetahValues.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("Cheetah", cheetahValues));
//
//    Map<Direction, Integer> x = new HashMap<>();
//    x.put(Direction.NORTH, 2);
//    x.put(Direction.SOUTH, 4);
//    x.put(Direction.EAST, 9);
//    x.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("x", x));
//
//    Map<Direction, Integer> y = new HashMap<>();
//    y.put(Direction.NORTH, 2);
//    y.put(Direction.SOUTH, 4);
//    y.put(Direction.EAST, 9);
//    y.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("y", y));
//
//    Map<Direction, Integer> z = new HashMap<>();
//    z.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("z", z));
//
//    Map<Direction, Integer> a = new HashMap<>();
//    cheetahValues.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("a", a));
//
//    Map<Direction, Integer> b = new HashMap<>();
//    cheetahValues.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("b", b));
//
//    Map<Direction, Integer> c = new HashMap<>();
//    cheetahValues.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("c", c));
//
//    Map<Direction, Integer> d = new HashMap<>();
//    cheetahValues.put(Direction.NORTH, 2);
//    cheetahValues.put(Direction.SOUTH, 4);
//    cheetahValues.put(Direction.EAST, 9);
//    cheetahValues.put(Direction.WEST, 3);
//    expectedDeck.add(new CardImpl("d", d));
//
//    ArrayList<Card> cards = new ArrayList<>();
//    cards.addAll(expectedDeck);
//
//
//    Set<Card> currPlayerHand = new HashSet<>();
//    currPlayerHand.add(cards.get(0));
//    currPlayerHand.add(cards.get(1));
//    currPlayerHand.add(cards.get(2));
//    Player currPlayer = new PlayerImpl(expectedDeck );
//
//    TripleTrioModel model = new TripleTrioGameModel( );
//    TTFrame view = new TTFrameImpl(model); //add the GUI based view
//    TripleTrioController controller = new TripleTrioGUIController(view);
//
//    /*
//    ASK: how to call/create all components of the game in the main
//     */
//    controller.playGame(model, "Configurations"
//        + File.separator
//        + "20deckConfig.txt",
//      "Configurations"
//        + File.separator
//        + "3x3sqrGrid.txt");
//  }
}