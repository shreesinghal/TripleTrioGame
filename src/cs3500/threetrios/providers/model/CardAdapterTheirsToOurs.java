package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.model.card.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Direction;

import java.util.Map;

public class CardAdapterTheirsToOurs implements cs3500.tripletrios.model.Card {
  private final Card card;
  public CardAdapterTheirsToOurs(Card card) {
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
    // THere is no equivalent method because
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

  }

  /**
   * Returns all the card's attack values.
   *
   * @return Map containing the 4 attack values
   */
  @Override
  public Map<Direction, Integer> getAttackValues() {
    return null;
  }

  /**
   * Returns the north attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getNorth() {
    return 0;
  }

  /**
   * Returns the south attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getSouth() {
    return 0;
  }

  /**
   * Returns the east attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getEast() {
    return 0;
  }

  /**
   * Returns the west attack value.
   *
   * @return attack value as an int
   */
  @Override
  public int getWest() {
    return 0;
  }

  @Override
  public void setCardColor(CardColor color) {

  }
}
