package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.model.card.CardAdapterOursToTheirs;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;

/**
 * Adapter class that converts the provider's Cell implementation to our Cell model.
 * This adapter allows the provider's Cell to be used where our Cell class is expected,
 * bridging the gap between the two different implementations.
 */
public class CellAdapterTheirsToOurs extends Cell {
  private final cs3500.threetrios.providers.model.cell.Cell Cell;

  /**
   * Constructs an adapter wrapping their `cell` implementation.
   * @param cell The instance of their `cell` interface.
   * @throws IllegalArgumentException if the cell is null.
   */
  public CellAdapterTheirsToOurs(cs3500.threetrios.providers.model.cell.Cell cell) {
    super(cell.cardCapacity() == 0 ? CellType.HOLE : CellType.CARDCELL);
    if (cell == null) {
      throw new IllegalArgumentException("cell cannot be null.");
    }
    this.Cell = cell;
    if (cell.hasCard()) {
      placeCard(new CardAdapterTheirsToOurs(cell.fetchCard()));
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
