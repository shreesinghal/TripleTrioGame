package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.model.card.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Direction;

import java.util.HashMap;
import java.util.Map;

/**
 * An adapter class that converts the provider's Card implementation to our Card interface.
 * It handles the translation of method calls and data structures between the two systems,
 * including differences in naming conventions and how card properties are stored and accessed.
 */
public class CardAdapterTheirsToOurs implements cs3500.tripletrios.model.Card {
  private final Card card;

  /**
   * Constructs a new CardAdapterTheirsToOurs.
   * This adapter wraps the provider's Card object to make it compatible with our Card interface.
   *
   * @param card The provider's Card object to be adapted.
   * @throws IllegalArgumentException if the provided card is null.
   */
  public CardAdapterTheirsToOurs(Card card) {
    if (card == null) {
      throw new IllegalArgumentException("Provided card cannot be null");
    }
    this.card = card;
  }

  /**
   * Returns the name of the current card.
   *
   * @return String of the name
   */
  @Override
  public String getName() {
    return card.fetchName();
  }

  /**
   * Returns the player that the Card belongs to.
   *
   * @return the Player
   */
  @Override
  public CardColor getColor() {
    // There is no equivalent method because
    // their model keeps track of the color based on
    // what player's hand the card is in, not within their
    // card class. So it's not possible to get the color now.
    return null;
  }

  /**
   * Switch ownership of the card from current to opposing player.
   */
  @Override
  public void flipOwnership() {
    //There is no equivalent method because
    //their card does not store the owner. It
    //is controlled externally through the model.
  }

  /**
   * Returns all the card's attack values.
   *
   * @return Map containing the 4 attack values
   */
  @Override
  public Map<Direction, Integer> getAttackValues() {
    Map<Direction, Integer> vals = new HashMap<>();
    vals.put(Direction.NORTH, getNorth());
    vals.put(Direction.SOUTH, getSouth());
    vals.put(Direction.EAST, getEast());
    vals.put(Direction.WEST, getWest());
    return vals;
  }

  /**
   * Returns the north attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getNorth() {
    return card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.NORTH)
            .fetchNumber();
  }

  /**
   * Returns the south attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getSouth() {

    return card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.SOUTH)
            .fetchNumber();

  }

  /**
   * Returns the east attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getEast() {

    return card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.EAST)
            .fetchNumber();

  }

  /**
   * Returns the west attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getWest() {

    return card.fetchNumAtDirection(cs3500.threetrios.providers.model.Direction.WEST)
            .fetchNumber();

  }

  @Override
  public void setCardColor(CardColor color) {
    // They do not store the color of the card
    // in their card class. Instead, the model handles this
    // so there is no equivalent method.
  }
}
