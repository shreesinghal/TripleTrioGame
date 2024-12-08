package cs3500.tripletrios.model;

import cs3500.tripletrios.strategies.BattleStrategy;
import cs3500.tripletrios.strategies.BattleStrategyFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A variant of the Triple Trio game model that implements the Same and Plus rules.
 * This model extends the base game by adding two new battle rules that can be applied
 * before the normal battle phase:
 *
 * <p>Same Rule: If at least two adjacent cards share the same value in the opposing direction
 * of the placed card, then any of those same-value cards that belong to the opponent are flipped.
 * For example, if a card with North=5 is placed, and two adjacent cards both have South=5,
 * those cards would be flipped if they belong to the opponent.</p>
 *
 * <p>Plus Rule: If at least two adjacent cards sum to the same total when adding their values
 * with the placed card's value in opposing directions, then any of those cards that belong to
 * the opponent are flipped. For example, if a card with North=3 is placed, and one adjacent card
 * has South=7 while another has South=7, both would be flipped (as 3+7=10 in both cases) if they
 * belong to the opponent.</p>
 *
 * <p>Note: These variant rules only apply before the combo step. During the combo step,
 * only normal battle rules are used.</p>
 */
public class TripleTrioSamePlusModel extends TripleTrioGameModel {
  private final BattleStrategy battleStrategy;
  private final boolean useSameRule;
  private final boolean usePlusRule;

  /**
   * Creates a new model with the specified battle strategy.
   */
  public TripleTrioSamePlusModel(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid,
                                 boolean sameRule, boolean plusRule) {
    super(deckOfCards, grid);
    if (sameRule && plusRule) {
      throw new IllegalArgumentException("Cannot use Same and Plus rules simultaneously");
    }
    this.battleStrategy = BattleStrategyFactory.createStrategy(sameRule, plusRule);
    this.useSameRule = sameRule;
    this.usePlusRule = plusRule;
  }

  @Override
  public void executeBattlePhase(int xPos, int yPos) {
    List<Posn> flippedCards = new ArrayList<>();

    // First handle special rules (Same or Plus)
    if (useSameRule) {
      flippedCards.addAll(handleSameRule(xPos, yPos));
    } else if (usePlusRule) {
      flippedCards.addAll(handlePlusRule(xPos, yPos));
    }

    super.executeBattlePhase(xPos, yPos);
  }

  private List<Posn> handleSameRule(int xPos, int yPos) {
    List<Posn> flippedCards = new ArrayList<>();
    Card placedCard = getCurrentGrid().get(yPos).get(xPos).getCard();
    Map<Integer, List<Posn>> matchingValues = new HashMap<>();

    // Check each direction
    checkMatchingValue(placedCard.getSouth(), xPos, yPos + 1, Direction.NORTH, matchingValues);
    checkMatchingValue(placedCard.getWest(), xPos - 1, yPos, Direction.EAST, matchingValues);
    checkMatchingValue(placedCard.getNorth(), xPos, yPos - 1, Direction.SOUTH, matchingValues);
    checkMatchingValue(placedCard.getEast(), xPos + 1, yPos, Direction.WEST, matchingValues);

    // Flip matching cards belonging to opponent
    for (List<Posn> matches : matchingValues.values()) {
      if (matches.size() >= 2) {
        for (Posn pos : matches) {
          Card card = getCurrentGrid().get(pos.getY()).get(pos.getX()).getCard();
          if (card != null && card.getColor() == getOppPlayer().getColor()) {
            card.flipOwnership();
            flippedCards.add(pos);
          }
        }
      }
    }

    return flippedCards;
  }

  private void checkMatchingValue(int placedValue, int x, int y, Direction dir,
                                  Map<Integer, List<Posn>> matchingValues) {
    Posn pos = new Posn(x, y);
    if (!isValidPosition(pos)) {
      return;
    }

    Card adjCard = getCurrentGrid().get(y).get(x).getCard();
    if (adjCard == null) {
      return;
    }

    int adjValue = getDirectionValue(adjCard, dir);
    if (placedValue == adjValue) {
      matchingValues.computeIfAbsent(placedValue, k -> new ArrayList<>())
              .add(pos);
    }
  }

  private List<Posn> handlePlusRule(int xPos, int yPos) {
    List<Posn> flippedCards = new ArrayList<>();
    Card placedCard = getCurrentGrid().get(yPos).get(xPos).getCard();
    Map<Integer, List<Posn>> matchingSums = new HashMap<>();

    // Check each direction
    checkMatchingSum(placedCard.getSouth(), xPos, yPos + 1, Direction.NORTH, matchingSums);
    checkMatchingSum(placedCard.getWest(), xPos - 1, yPos, Direction.EAST, matchingSums);
    checkMatchingSum(placedCard.getNorth(), xPos, yPos - 1, Direction.SOUTH, matchingSums);
    checkMatchingSum(placedCard.getEast(), xPos + 1, yPos, Direction.WEST, matchingSums);

    // Flip matching cards belonging to opponent
    for (List<Posn> matches : matchingSums.values()) {
      if (matches.size() >= 2) {
        for (Posn pos : matches) {
          Card card = getCurrentGrid().get(pos.getY()).get(pos.getX()).getCard();
          if (card != null && card.getColor() == getOppPlayer().getColor()) {
            card.flipOwnership();
            flippedCards.add(pos);
          }
        }
      }
    }

    return flippedCards;
  }

  private void checkMatchingSum(int placedValue, int x, int y, Direction dir,
                                Map<Integer, List<Posn>> matchingSums) {
    Posn pos = new Posn(x, y);
    if (!isValidPosition(pos)) {
      return;
    }

    Card adjCard = getCurrentGrid().get(y).get(x).getCard();
    if (adjCard == null) {
      return;
    }

    int adjValue = getDirectionValue(adjCard, dir);
    int sum = placedValue + adjValue;

    matchingSums.computeIfAbsent(sum, k -> new ArrayList<>())
            .add(pos);
  }

  private int getDirectionValue(Card card, Direction dir) {
    switch (dir) {
      case NORTH: return card.getNorth();
      case SOUTH: return card.getSouth();
      case EAST: return card.getEast();
      case WEST: return card.getWest();
      default: throw new IllegalArgumentException("Invalid direction");
    }
  }

  private boolean isValidPosition(Posn pos) {
    ArrayList<ArrayList<Cell>> grid = getCurrentGrid();
    return pos.getY() >= 0 && pos.getY() < grid.size()
            && pos.getX() >= 0 && pos.getX() < grid.get(0).size()
            && grid.get(pos.getY()).get(pos.getX()).getCellType() != Cell.CellType.HOLE;
  }
}