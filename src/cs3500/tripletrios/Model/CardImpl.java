package cs3500.tripletrios.Model;

import java.util.HashMap;
import java.util.Map;

public class CardImpl implements Card {

  String name;
  Color cardColor;
  Map<Direction, Integer> attackValues = new HashMap<>();

  public CardImpl(Color cardColor, String name, Map<Direction, Integer> attackValues) {
    this.name = name;
    this.cardColor = cardColor;
    this.attackValues = attackValues;
  }

  /**
   * Returns the name of the current card.
   *
   * @return String of the name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the player that the Card belongs to.
   *
   * @return the Player
   */
  @Override
  public Color getColor() {
    return this.cardColor;
  }

  /**
   * Switch ownership of the card from current to opposing player
   */
  @Override
  public void flipOwnership() {
    if (cardColor == Color.RED) {
      this.cardColor = Color.BLUE;
    }
    else {
      this.cardColor = Color.RED;
    }
  }

  /**
   * Returns all the card's attack values.
   *
   * @return Map containing the 4 attack values
   */
  @Override
  public Map<Direction, Integer> getAttackValues() {
    return this.attackValues;
  }

}
