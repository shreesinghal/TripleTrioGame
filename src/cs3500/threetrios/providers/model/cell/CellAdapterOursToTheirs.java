package cs3500.threetrios.providers.model.cell;

import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.model.card.CardAdapterOursToTheirs;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Cell.CellType;
import cs3500.tripletrios.model.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 An adapter class that converts our Cell implementation to the provider's Cell interface.
 This class bridges the gap between our cell representation and the provider's cell model,
 allowing interoperability between different implementations of the Triple Triad game.
 The adapter translates method calls and manages the conversion of card and ownership
 representations between the two different cell implementations.
 */
public class CellAdapterOursToTheirs implements Cell {

  private final cs3500.tripletrios.model.Cell ourCell;

  /**
   * Constructs a CellAdapterTheirsToOurs by adapting our Cell implementation
   * to the provider's Cell interface.
   * @param cell The Cell from our implementation to be adapted
   * @throws IllegalArgumentException if the provided cell is null
   */
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

    Map<Direction, Integer> attackValues = new HashMap<>();

    attackValues.put(Direction.NORTH,
            card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.NORTH)
                    .fetchNumber());
    attackValues.put(Direction.SOUTH,
            card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.SOUTH)
                    .fetchNumber());
    attackValues.put(Direction.EAST,
            card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.EAST)
                    .fetchNumber());
    attackValues.put(Direction.WEST,
            card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.WEST)
                    .fetchNumber());

    CardImpl newCard = new CardImpl(card.fetchName(), attackValues);

    newCard.setCardColor(card.fetchName().equals("PLAYER_A") ? CardColor.RED : CardColor.BLUE);

    ourCell.placeCard(newCard);

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

    if (ourCell.isEmpty()) {
      throw new IllegalStateException("Cannot change owner of an empty cell");
    }


    switch (owner) {
      case PLAYER_A:
        ourCell.getCard().setCardColor(CardColor.RED);
        break;
      case PLAYER_B:
        ourCell.getCard().setCardColor(CardColor.BLUE);
        break;
      default:
        throw new IllegalArgumentException("Invalid player type");
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
    if (ourCell.getCellType() == CellType.HOLE || !ourCell.isEmpty()) {
      return 0;
    }
    return 1;
  }
}
