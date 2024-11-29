package cs3500.threetrios.providers.model.card;

/**
 * An enumeration of card numbers for cards in a game of Triple Triad.
 * Numbers include 1-9 inclusive and A (with a value of 10).
 */
public enum CardNumber {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  A(10);

  private final int number;

  CardNumber(int number) {
    this.number = number;
  }

  /**
   * Returns the string representation of this number.
   * @return the string representation
   */
  @Override
  public String toString() {
    if (this.equals(CardNumber.A)) {
      return "A";
    }
    return Integer.toString(this.number);
  }

  /**
   * Returns 0, 1, or -1, based on the given card number using their integer values.
   * @param cardNumber other card number.
   * @return 0, 1, or -1
   * @throws IllegalArgumentException if card number is null
   */
  public int compareCardNumber(CardNumber cardNumber) {
    if (cardNumber == null) {
      throw new IllegalArgumentException("Card number cannot be null.");
    }

    return Integer.compare(this.number, cardNumber.number);
  }

  /**
   * Returns the number value of the CardNumber.
   * @return the number of this enum constant.
   */
  public int fetchNumber() {
    return this.number;
  }
}
