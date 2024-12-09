package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Direction;
import cs3500.tripletrios.model.Posn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Strategy for checking Plus rule captures.
 */
public class PlusBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    // Standard capture logic remains for normal battles
    return attackerValue > defenderValue;
  }

  /**
   * Checks if cards should be captured according to the Plus rule.
   *
   * @param adjacentCards Map of adjacent cards by direction
   * @param placedCard The card that was just placed
   * @return List of positions where cards should be flipped
   */
  public List<Posn> checkPlusCaptures(Map<Direction, Card> adjacentCards,
                                      Map<Direction, Posn> positions,
                                      Card placedCard) {
    Map<Integer, List<Direction>> sumDirections = new HashMap<>();

    // Calculate sums for each direction
    for (Direction dir : Direction.values()) {
      Card adjCard = adjacentCards.get(dir);
      if (adjCard == null || adjCard.getColor() ==
              placedCard.getColor()) {
        continue;
      }

      int placedValue = getAttackValue(placedCard, dir);
      int adjValue = getOpposingValue(adjCard, dir);
      int sum = placedValue + adjValue;
      sumDirections.computeIfAbsent(sum, k -> new ArrayList<>()).add(dir);
    }

    // Find matching sums and collect opponent cards to flip
    List<Posn> cardsToFlip = new ArrayList<>();
    for (List<Direction> matchingDirs : sumDirections.values()) {
      if (matchingDirs.size() >= 2) {
        for (Direction dir : matchingDirs) {
          Card adjCard = adjacentCards.get(dir);
          if (adjCard.getColor() != placedCard.getColor()) {
            cardsToFlip.add(positions.get(dir));
          }
        }
      }
    }
    return cardsToFlip;
  }

  private int getAttackValue(Card card, Direction dir) {
    switch (dir) {
      case NORTH: return card.getNorth();
      case SOUTH: return card.getSouth();
      case EAST: return card.getEast();
      case WEST: return card.getWest();
      default: throw new IllegalStateException("Invalid direction");
    }
  }

  private int getOpposingValue(Card card, Direction dir) {
    switch (dir) {
      case NORTH: return card.getSouth();
      case SOUTH: return card.getNorth();
      case EAST: return card.getWest();
      case WEST: return card.getEast();
      default: throw new IllegalStateException("Invalid direction");
    }
  }
}