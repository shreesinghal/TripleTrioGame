package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.model.card.CardAdapterOursToTheirs;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;

/**
 * Adapter to convert a provider's Cell to our Cell.
 */
public class CellAdapterTheirsToOurs extends Cell {
  private final cs3500.threetrios.providers.model.cell.Cell Cell;

  /**
   * Constructs an adapter wrapping their `Cell` implementation.
   *
   * @param Cell The instance of their `Cell` interface.
   * @throws IllegalArgumentException if the cell is null.
   */
  public CellAdapterTheirsToOurs(cs3500.threetrios.providers.model.cell.Cell Cell) {
    super(Cell.cardCapacity() == 0 ? CellType.HOLE : CellType.CARDCELL);
    if (Cell == null) {
      throw new IllegalArgumentException("Cell cannot be null.");
    }
    this.Cell = Cell;
    if (Cell.hasCard()) {
      placeCard(new CardAdapterTheirsToOurs(Cell.fetchCard()));
    }
  }

  @Override
  public Card getCard() {
    if (!Cell.hasCard()) {
      return null;
    }
    return new CardAdapterTheirsToOurs(Cell.fetchCard());
  }

  @Override
  public boolean isEmpty() {
    return !Cell.hasCard();
  }

  @Override
  public void placeCard(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card cannot be null.");
    }
    Cell.playCard(new CardAdapterOursToTheirs(card));
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof CellAdapterTheirsToOurs)) {
      return false;
    }
    return this.Cell.equals(((CellAdapterTheirsToOurs) obj));
  }

  @Override
  public int hashCode() {
    return Cell.hashCode();
  }
}
