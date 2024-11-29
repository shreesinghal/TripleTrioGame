package cs3500.threetrios.providers.model.card;

import cs3500.threetrios.providers.model.Direction;
import cs3500.tripletrios.model.Card;
/**
 * This is an adapter class to adapt our Card interface
 * to ur customers Card interface.
 */
public class CardAdapterOursToTheirs implements cs3500.threetrios.providers.model.card.Card {

  private final Card ourCard;

  public CardAdapterOursToTheirs(Card ourCard) {
    if (ourCard == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    this.ourCard = ourCard;
  }

  /**
   * Compares this card to the given card.
   *
   * @param other     Card to compare
   * @param direction which direction to compare numbers
   * @return -1 if other is greater, 1 if this card is greater,
   * and 0 if the cards are equal
   * @throws IllegalArgumentException if other or direction is null
   */
  @Override
  public int compareTo(cs3500.threetrios.providers.model.card.Card other, Direction direction) {
    return 0;
  }

  /**
   * Returns the name of this card.
   *
   * @return the name of this card.
   */
  @Override
  public String fetchName() {
    return ourCard.getName();
  }

  /**
   * Returns a copy of this card.
   *
   * @return a copy of this card
   */
  @Override
  public cs3500.threetrios.providers.model.card.Card copyCard() {
    return new CardAdapterOursToTheirs(ourCard);
  }

  /**
   * Returns the number of this card at the given direction.
   *
   * @param direction the direction (North, East, South, West)
   * @return the card's number
   * @throws IllegalArgumentException if direction is null
   */
  @Override
  public CardNumber fetchNumAtDirection(Direction direction) {
    if (direction == null) {
      throw new IllegalArgumentException("Direction cannot be null");
    }


    int attackValue = ourCard.getAttackValues().get(direction);

    switch (attackValue) {
      case 1: return CardNumber.ONE;
      case 2: return CardNumber.TWO;
      case 3: return CardNumber.THREE;
      case 4: return CardNumber.FOUR;
      case 5: return CardNumber.FIVE;
      case 6: return CardNumber.SIX;
      case 7: return CardNumber.SEVEN;
      case 8: return CardNumber.EIGHT;
      case 9: return CardNumber.NINE;
      case 10: return CardNumber.A;
      default:
        throw new IllegalArgumentException("Invalid attack value: " + attackValue);
    }



  }
}
