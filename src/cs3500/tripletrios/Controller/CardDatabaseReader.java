package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;
import cs3500.tripletrios.Model.CardImpl;
import cs3500.tripletrios.Model.Direction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CardDatabaseReader {

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
      attackVal.put(Direction.NORTH, Integer.valueOf(details[1]));
      attackVal.put(Direction.SOUTH, Integer.valueOf(details[2]));
      attackVal.put(Direction.EAST, Integer.valueOf(details[3]));
      attackVal.put(Direction.WEST, Integer.valueOf(details[4]));
      deck.add(new CardImpl(details[0], attackVal));
    }
    return deck;
  }
}
