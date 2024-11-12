package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

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
  public Card chooseCard() {
    return null;
  }

  /**
   * A method that allows for placing a card strategically on the grid.
   */
  @Override
  public void placeCard() {
    Cell cell1 = model.getCurrentGrid().get(0).get(0);
    Cell cell2 = model.getCurrentGrid().get(model.getGridWidth()).get(0);
    Cell cell3 = model.getCurrentGrid().get(0).get(model.getGridHeight());
    Cell cell4 = model.getCurrentGrid().get(model.getGridWidth()).get(model.getGridHeight());
    if (!cell1.isEmpty() && cell1.getCellType() != Cell.CellType.HOLE) {

    }
    if (!cell2.isEmpty() && cell2.getCellType() != Cell.CellType.HOLE) {

    }
    if (!cell3.isEmpty() && cell3.getCellType() != Cell.CellType.HOLE) {

    }
    if (!cell4.isEmpty() && cell4.getCellType() != Cell.CellType.HOLE) {

    }

    // 0,0
    // grid width , 0
    //0, grid Height
    //grid width, grid height
  }



}
