package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Direction;
import cs3500.tripletrios.model.Posn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SameBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    return attackerValue > defenderValue;
  }

  public List<Posn> checkSameCaptures(Map<Direction, Card> adjacentCards,
                                      Map<Direction, Posn> positions,
                                      Card placedCard) {
    Map<Integer, List<Posn>> matchingValues = new HashMap<>();

    // First pass: collect values from adjacent cards
    for (Direction dir : Direction.values()) {
      Card adjCard = adjacentCards.get(dir);
      if (adjCard == null) {
        continue;
      }

      // Get the value that faces the placed card
      int value = getOpposingValue(adjCard, dir);
      matchingValues.computeIfAbsent(value, k -> new ArrayList<>())
              .add(positions.get(dir));
    }

    // Second pass: find cards to flip
    List<Posn> cardsToFlip = new ArrayList<>();
    for (Map.Entry<Integer, List<Posn>> entry : matchingValues.entrySet()) {
      if (entry.getValue().size() >= 2) {
        for (Posn pos : entry.getValue()) {
          for (Map.Entry<Direction, Posn> dirEntry : positions.entrySet()) {
            if (dirEntry.getValue().equals(pos)) {
              Card card = adjacentCards.get(dirEntry.getKey());
              if (card.getColor() != placedCard.getColor()) {
                cardsToFlip.add(pos);
              }
              break;
            }
          }
        }
      }
    }
    return cardsToFlip;
  }

  private int getOpposingValue(Card card, Direction dir) {
    switch(dir) {
      case EAST: return card.getWest();
      case WEST: return card.getEast();
      case NORTH: return card.getSouth();
      case SOUTH: return card.getNorth();
      default: throw new IllegalStateException("Invalid direction");
    }
  }
}