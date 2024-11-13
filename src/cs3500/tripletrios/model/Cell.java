package cs3500.tripletrios.model;

import java.util.Objects;

/**
* Cell class to represents cells in the game.
*/
public final class Cell {

  private boolean hasCard;
  private Card card;

  /**
  * Enum for the type of cell.
  */
  public static enum CellType {
      HOLE,
      CARDCELL;
  }

  CellType cellType;

  /**
  * Creates a new empty Cell on the grid.
  * @param type specified cell type
  */
  public Cell(CellType type) {
    hasCard = false;
    card = null;
    this.cellType = type;
  }

  /**
  * Getter method to find the type of the cell.
  * @return CelType object for whatever cell type
  */
  public CellType getCellType() {
    return cellType;
  }

  /**
  * Returns if the cell has a card or not.
  * @return true if has card and vice versa
  */
  public boolean isEmpty() {
    return !hasCard;
  }

  /**
  * Puts a card into the cell.
  * @param card card to put in cell
  */
  public void placeCard(Card card) {
    this.card = card;
    hasCard = true;
  }

  /**
  * Getter method to return a card.
  * @return Card object
  */
  public Card getCard() {
    return this.card;
  }


  @Override
  public boolean equals(Object obj) {
    return obj instanceof Cell
            && this.cellType == ((Cell) obj).cellType
            && this.hasCard == ((Cell) obj).hasCard
            && this.card == ((Cell) obj).card;
  }

  @Override
  public int hashCode() {
    return Objects.hash(hasCard, card);
  }

}