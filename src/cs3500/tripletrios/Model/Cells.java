package cs3500.tripletrios.Model;

/**
 * This is an interface to keep track of the cells.
 * This interface shows one cell and its properties.
 */
public interface Cells {


  /**
   * This method determines whether a cell is occupied by another card already.
   * @return true is the cell is unoccupied, false otherwise
   */
  boolean isEmpty();

  /**
   * This method determines what type of cell this cell is.
   * returns if the cell is a hole or a card cell
   */
   void cellType();





}
