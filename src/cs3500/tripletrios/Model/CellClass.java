package cs3500.tripletrios.Model;

public class CellClass implements Cells{

  boolean empty;

  /**
   * Constructor that initializes a card as empty.
   */
  public void Cells() {
    this.empty = true;
  }

  /**
   * This method determines whether a cell is occupied by another card already.
   *
   * @return true is the cell is unoccupied, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * This method determines what type of cell this cell is.
   * returns if the cell is a hole or a card cell
   */
  @Override
  public void cellType() {

  }
}
