package cs3500.tripletrios.model;

import cs3500.tripletrios.strategies.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TripleTrioCombinedModel extends TripleTrioGameModel {
  private final BattleStrategy set1Strategy;
  private final BattleStrategy set2Strategy;

  public TripleTrioCombinedModel(Set<Card> deck, ArrayList<ArrayList<Cell>> grid,
                                 boolean reverse, boolean fallenAce,
                                 boolean same, boolean plus) {
    super(deck, grid);

    // Check both Set 2 rules aren't enabled simultaneously
    if (same && plus) {
      throw new IllegalArgumentException("Cannot use both Same and Plus rules simultaneously");
    }

    // Check that at least one Set 2 rule is enabled
    if (!same && !plus) {
      throw new IllegalArgumentException("Must enable exactly one rule from Set 2");
    }

    // Initialize Set 1 strategy (can have both, one, or none)
    if (reverse && fallenAce) {
      this.set1Strategy = new CombinedBattleStrategy(reverse, fallenAce);
    } else if (reverse) {
      this.set1Strategy = new ReverseBattleStrategy();
    } else if (fallenAce) {
      this.set1Strategy = new FallenAceBattleStrategy();
    } else {
      this.set1Strategy = new DefaultBattleStrategy();
    }

    // Initialize Set 2 strategy (must have exactly one)
    if (same) {
      this.set2Strategy = new SameBattleStrategy();
    } else {  // must be plus since we checked above
      this.set2Strategy = new PlusBattleStrategy();
    }
  }

  @Override
  public void executeBattlePhase(int xPos, int yPos) {
    Card placedCard = getCurrentGrid().get(yPos).get(xPos).getCard();
    if (placedCard == null) {
      return;
    }

    List<Posn> allCardsToFlip = new ArrayList<>();

    // Get adjacent cards and positions once
    Map<Direction, Card> adjacentCards = getAdjacentCards(xPos, yPos);
    Map<Direction, Posn> positions = getAdjacentPositions(xPos, yPos);

    // Collect cards to flip from Set 2 rules (Same/Plus)
    if (set2Strategy instanceof SameBattleStrategy) {
      allCardsToFlip.addAll(((SameBattleStrategy)set2Strategy)
              .checkSameCaptures(adjacentCards, positions, placedCard));
    } else if (set2Strategy instanceof PlusBattleStrategy) {
      allCardsToFlip.addAll(((PlusBattleStrategy)set2Strategy)
              .checkPlusCaptures(adjacentCards, positions, placedCard));
    }

    // Collect cards to flip from Set 1 rules
    allCardsToFlip.addAll(getCardsToFlipFromSet1Rules(xPos, yPos, placedCard));

    // Apply all flips at once
    flipCards(allCardsToFlip);
  }

  private List<Posn> getCardsToFlipFromSet1Rules(int xPos, int yPos, Card placedCard) {
    List<Posn> cardsToFlip = new ArrayList<>();

    // Check each direction for captures using set1Strategy
    checkAndAddCapturePosition(xPos, yPos + 1, placedCard.getSouth(), Direction.SOUTH, cardsToFlip);
    checkAndAddCapturePosition(xPos - 1, yPos, placedCard.getWest(), Direction.WEST, cardsToFlip);
    checkAndAddCapturePosition(xPos, yPos - 1, placedCard.getNorth(), Direction.NORTH, cardsToFlip);
    checkAndAddCapturePosition(xPos + 1, yPos, placedCard.getEast(), Direction.EAST, cardsToFlip);

    return cardsToFlip;
  }

  private void checkAndAddCapturePosition(int x, int y, int attackValue, Direction dir,
                                          List<Posn> cardsToFlip) {
    if (!isValidPosition(x, y)) {
      return;
    }

    Card targetCard = getCurrentGrid().get(y).get(x).getCard();
    if (targetCard != null && targetCard.getColor() != getPlayer().getColor()) {
      int defendValue = getOpposingValue(targetCard, dir);
      if (set1Strategy.canCapture(attackValue, defendValue)) {
        cardsToFlip.add(new Posn(x, y));
      }
    }
  }

  private Map<Direction, Card> getAdjacentCards(int xPos, int yPos) {
    Map<Direction, Card> adjacentCards = new HashMap<>();

    // Check each direction and add non-null cards
    if (isValidPosition(xPos, yPos - 1)) {
      Card card = getCurrentGrid().get(yPos - 1).get(xPos).getCard();
      if (card != null) {
        adjacentCards.put(Direction.NORTH, card);
      }
    }

    if (isValidPosition(xPos + 1, yPos)) {
      Card card = getCurrentGrid().get(yPos).get(xPos + 1).getCard();
      if (card != null) {
        adjacentCards.put(Direction.EAST, card);
      }
    }

    if (isValidPosition(xPos, yPos + 1)) {
      Card card = getCurrentGrid().get(yPos + 1).get(xPos).getCard();
      if (card != null) {
        adjacentCards.put(Direction.SOUTH, card);
      }
    }

    if (isValidPosition(xPos - 1, yPos)) {
      Card card = getCurrentGrid().get(yPos).get(xPos - 1).getCard();
      if (card != null) {
        adjacentCards.put(Direction.WEST, card);
      }
    }

    return adjacentCards;
  }

  private Map<Direction, Posn> getAdjacentPositions(int xPos, int yPos) {
    Map<Direction, Posn> positions = new HashMap<>();

    if (isValidPosition(xPos, yPos - 1)) {
      positions.put(Direction.NORTH, new Posn(xPos, yPos - 1));
    }
    if (isValidPosition(xPos + 1, yPos)) {
      positions.put(Direction.EAST, new Posn(xPos + 1, yPos));
    }
    if (isValidPosition(xPos, yPos + 1)) {
      positions.put(Direction.SOUTH, new Posn(xPos, yPos + 1));
    }
    if (isValidPosition(xPos - 1, yPos)) {
      positions.put(Direction.WEST, new Posn(xPos - 1, yPos));
    }

    return positions;
  }

  private int getOpposingValue(Card card, Direction dir) {
    switch(dir) {
      case SOUTH: return card.getNorth();
      case NORTH: return card.getSouth();
      case EAST: return card.getWest();
      case WEST: return card.getEast();
      default: throw new IllegalStateException("Invalid direction");
    }
  }

  private boolean isValidPosition(int x, int y) {
    return x >= 0 && x < getCurrentGrid().get(0).size()
            && y >= 0 && y < getCurrentGrid().size()
            && getCurrentGrid().get(y).get(x).getCellType() != Cell.CellType.HOLE;
  }

  private void flipCards(List<Posn> cardsToFlip) {
    for (Posn pos : cardsToFlip) {
      getCurrentGrid().get(pos.getY()).get(pos.getX()).getCard().flipOwnership();
    }
  }
}