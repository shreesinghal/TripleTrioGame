package cs3500.threetrios.providers.model.card;

import cs3500.threetrios.providers.model.Direction;
import cs3500.tripletrios.model.Card;

/**
 An adapter class that converts our Card implementation to the provider's Card interface.
 This class bridges the gap between our card representation and the provider's card model,
 allowing interoperability between different implementations of the Triple Triad game.
 The adapter translates method calls and manages the conversion of card properties
 such as name, attack values, and comparisons between the two different card implementations.
 */
public class CardAdapterOursToTheirs implements cs3500.threetrios.providers.model.card.Card {

  private final Card ourCard;

  /**
   * Constructs a CardAdapterOursToTheirs by adapting our Card implementation to
   * the provider's Card interface.
   * @param ourCard The Card from our implementation to be adapted
   * @throws IllegalArgumentException if the provided card is null
   */
  public CardAdapterOursToTheirs(Card ourCard) {
    if (ourCard == null) {
      throw new IllegalArgumentException("Card cannot be null");
    }
    this.ourCard = ourCard;
  }

  /**
   * Compares this card to the given card.
   * @param other Card to compare
   * @param direction which direction to compare numbers
   * @return val
   */
  @Override
  public int compareTo(cs3500.threetrios.providers.model.card.Card other, Direction direction) {
    if (other == null || direction == null) {
      throw new IllegalArgumentException("Card or direction cannot be null");
    }

    Direction ourDirection;
    switch (direction) {
      case NORTH:
        ourDirection = Direction.NORTH;
        break;
      case SOUTH:
        ourDirection = Direction.SOUTH;
        break;
      case EAST:
        ourDirection = Direction.EAST;
        break;
      case WEST:
        ourDirection = Direction.WEST;
        break;
      default:
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }

    int thisAttackValue = ourCard.getAttackValues().get(ourDirection);
    int otherAttackValue = ((CardAdapterOursToTheirs) other).ourCard.getAttackValues()
            .get(ourDirection);

    // Compare the attack values
    return Integer.compare(thisAttackValue, otherAttackValue);

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
