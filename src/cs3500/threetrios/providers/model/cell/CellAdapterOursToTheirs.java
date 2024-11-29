package cs3500.threetrios.providers.model.cell;

import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.model.card.CardAdapterOursToTheirs;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Cell.CellType;

public class CellAdapterOursToTheirs implements Cell {

  private cs3500.tripletrios.model.Cell ourCell;


  public CellAdapterOursToTheirs(cs3500.tripletrios.model.Cell cell) {
    if (cell == null) {
      throw new IllegalArgumentException("Cell cannot be null");
    }

    this.ourCell = cell;
  }

  /**
   * Returns which player has won the card in this cell.
   *
   * @return The player type of the card that owns the card in the cell
   */
  @Override
  public PlayerType fetchOwner() {
    if (ourCell.getCard().getColor() == CardColor.RED) {
      return PlayerType.PLAYER_A;
    } else {
      return PlayerType.PLAYER_B;
    }
  }

  /**
   * Returns true if this cell has a card.
   *
   * @return true if this cell has a card.
   */
  @Override
  public boolean hasCard() {
    return !ourCell.isEmpty();
  }

  /**
   * Returns a copy of the card that is in the cell.
   * If there is no card in the cell, return null.
   *
   * @return a copy of the card that is in the cell
   * @throws IllegalStateException if cell has no card.
   */
  @Override
  public Card fetchCard() {
    if (ourCell.isEmpty()) {
      throw new IllegalStateException("Cell has no card");
    }
    return new CardAdapterOursToTheirs(ourCell.getCard());
  }

  /**
   * Given a card, sets current card in cell.
   *
   * @param card Card
   * @throws IllegalStateException    if cell is not playable
   *                                  This is dictated by implementations.
   * @throws IllegalArgumentException if card is null
   */
  @Override
  public void playCard(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Card is null");
    }


  }

  /**
   * Sets owner of the current cell to the new owner.
   *
   * @param owner PlayerType of new owner.
   * @throws IllegalArgumentException of owner is null
   * @throws IllegalArgumentException if the owner is not a playable player type
   */
  @Override
  public void changeOwner(PlayerType owner) {
    if (owner == null) {
      throw new IllegalArgumentException("Owner is null");
    }


  }

  /**
   * Returns a copy of the cell.
   * Mutating the card in the returned cell does not
   * affect the original cell's card.
   *
   * @return a copy of this cell
   */
  @Override
  public Cell copyCell() {
    return new CellAdapterOursToTheirs(ourCell);
  }

  /**
   * Returns the number of cards that can be played to this cell.
   *
   * @return number of cards that can be played to this cell
   */
  @Override
  public int cardCapacity() {
    return 0;
  }
}
