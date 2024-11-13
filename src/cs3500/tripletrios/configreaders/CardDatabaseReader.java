package cs3500.tripletrios.configreaders;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardImpl;
import cs3500.tripletrios.model.Direction;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Class that reads cards from  card configuration files.
 */
public class CardDatabaseReader {


  /**
   * Method which reads through the card database
   * and returns a deck used in the game.
   * @param deckPath the path to the deck
   * @return returns a set of cards
   */
  public static Set<Card> readDeckConfiguration(String deckPath) {
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
      Map<Direction, Integer> attackVal = new HashMap<>();
      attackVal.put(Direction.NORTH, (Objects.equals(details[1], "A"))
              ? 10
              : Integer.parseInt(details[1]));
      attackVal.put(Direction.SOUTH, (Objects.equals(details[2], "A"))
              ? 10
              : Integer.parseInt(details[2]));
      attackVal.put(Direction.EAST, (Objects.equals(details[3], "A"))
              ? 10
              : Integer.parseInt(details[3]));
      attackVal.put(Direction.WEST, (Objects.equals(details[4], "A"))
              ? 10
              : Integer.parseInt(details[4]));
      deck.add(new CardImpl(details[0], attackVal));
    }
    return deck;
  }
}