package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;
import cs3500.tripletrios.Model.CardImpl;
import cs3500.tripletrios.Model.Direction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public final class CardDatabaseReader {

  public CardDatabaseReader() {
  }

  public Set<Card> readDeckConfiguration(String deckPath) {
    Scanner scanner;
    try {
      scanner = new Scanner(new FileReader(deckPath));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    Set<Card> deck = new HashSet<>();
    while (scanner.hasNext()) {
      String cardDetails = scanner.nextLine();
      String[] details = cardDetails.split(" ");
      Map<Direction, Integer> attackVal = new HashMap<Direction, Integer>();
      attackVal.put(Direction.NORTH, (Objects.equals(details[1], "A")) ? 10 : Integer.parseInt(details[1]));
      attackVal.put(Direction.SOUTH, (Objects.equals(details[2], "A")) ? 10 : Integer.parseInt(details[2]));
      attackVal.put(Direction.EAST, (Objects.equals(details[3], "A")) ? 10 : Integer.parseInt(details[3]));
      attackVal.put(Direction.WEST, (Objects.equals(details[4], "A")) ? 10 : Integer.parseInt(details[4]));
      deck.add(new CardImpl(details[0], attackVal));
    }
    return deck;
  }
}