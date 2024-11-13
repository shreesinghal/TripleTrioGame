package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import java.util.ArrayList;

public class CornerStrategy implements TripleTrioStrategy {

  private ReadOnlyTripleTrioModel model;

  private Card chosenCard;

  private Posn chosenLocation;


  /**
   * A method that allows for strategically choosing which card to play.
   *
   * @return the card chosen to play
   */
  @Override
  public Card chooseCardFromHand() {
    return null;
  }

  @Override
  public void placeCardToGrid() {
    ArrayList<Cell> openCorners = getOpenCorners();
  }

  private ArrayList<Cell> getOpenCorners() {
    ArrayList<Cell> openCorners = new ArrayList<>();
    Cell cell1 = model.getCurrentGrid().get(0).get(0);
    Cell cell2 = model.getCurrentGrid().get(0).get(model.getGridWidth());
    Cell cell3 = model.getCurrentGrid().get(model.getGridHeight()).get(0);
    Cell cell4 = model.getCurrentGrid().get(model.getGridHeight()).get(model.getGridWidth());

    if (isOpenToPlace(cell1)) {
      openCorners.add(cell1);
    }
    if (isOpenToPlace(cell2)) {
      openCorners.add(cell2);
    }
    if (isOpenToPlace(cell3)) {
      openCorners.add(cell3);
    }
    if (isOpenToPlace(cell4)) {
      openCorners.add(cell4);
    }
    return openCorners;
  }

  private static boolean isOpenToPlace(Cell cell1) {
    return !cell1.isEmpty() && cell1.getCellType() != Cell.CellType.HOLE;
  }


}
