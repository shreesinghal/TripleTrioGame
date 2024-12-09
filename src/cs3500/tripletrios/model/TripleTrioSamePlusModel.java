package cs3500.tripletrios.model;

import cs3500.tripletrios.strategies.BattleStrategy;
import cs3500.tripletrios.strategies.BattleStrategyFactory;
import cs3500.tripletrios.strategies.PlusBattleStrategy;
import cs3500.tripletrios.strategies.SameBattleStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A variant of the Triple Trio game model that implements the Same and Plus rules.
 * This model extends the base game by adding two new battle rules that can be applied
 * before the normal battle phase.
 */
public class TripleTrioSamePlusModel extends TripleTrioGameModel {
  private final BattleStrategy battleStrategy;
  private final Map<Direction, Card> adjacentCards;
  private final Map<Direction, Posn> positions;

  /**
   * Creates a new Triple Trio game model with variant rules.
   *
   * @param deck the set of cards to be used in the game
   * @param grid the initial game grid configuration
   * @param sameRule whether to apply the Same rule
   * @param plusRule whether to apply the Plus rule
   * @throws IllegalArgumentException if both Same and Plus rules are enabled simultaneously
   */
  public TripleTrioSamePlusModel(Set<Card> deck, ArrayList<ArrayList<Cell>> grid,
                                 boolean sameRule, boolean plusRule) {
    super(deck, grid);
    if (sameRule && plusRule) {
      throw new IllegalArgumentException("Cannot apply both Same and Plus rules simultaneously");
    }
    this.battleStrategy = BattleStrategyFactory.createStrategy(sameRule, plusRule);
    this.adjacentCards = new HashMap<>();
    this.positions = new HashMap<>();
  }


  /**
   * Executes the battle phase for a card placed at the specified position.
   * Applies variant rules (Same or Plus) before executing the normal battle phase.
   *
   * @param xPos the x-coordinate of the placed card
   * @param yPos the y-coordinate of the placed card
   */
  @Override
  public void executeBattlePhase(int xPos, int yPos) {
    Card placedCard = getCurrentGrid().get(yPos).get(xPos).getCard();
    if (placedCard == null) {
      return;
    }

    // Clear previous state and gather adjacent cards
    adjacentCards.clear();
    positions.clear();
    gatherAdjacentCards(xPos, yPos);

    // Check for special rule captures first
    List<Posn> cardsToFlip = new ArrayList<>();
    if (battleStrategy instanceof SameBattleStrategy) {
      cardsToFlip = ((SameBattleStrategy)battleStrategy)
              .checkSameCaptures(adjacentCards, positions, placedCard);
    } else if (battleStrategy instanceof PlusBattleStrategy) {
      cardsToFlip = ((PlusBattleStrategy)battleStrategy)
              .checkPlusCaptures(adjacentCards, positions, placedCard);
    }

    // Flip captured cards
    for (Posn pos : cardsToFlip) {
      getCurrentGrid().get(pos.getY()).get(pos.getX()).getCard().flipOwnership();
    }

    // Execute normal battle phase
    super.executeBattlePhase(xPos, yPos);
  }

  /**
   * Gathers all adjacent cards and their positions for rule checking.
   *
   * @param xPos x-coordinate of the placed card
   * @param yPos y-coordinate of the placed card
   */
  private void gatherAdjacentCards(int xPos, int yPos) {
    // Check North
    if (isValidPosition(xPos, yPos - 1)) {
      Card northCard = getCurrentGrid().get(yPos - 1).get(xPos).getCard();
      if (northCard != null) {
        adjacentCards.put(Direction.NORTH, northCard);
        positions.put(Direction.NORTH, new Posn(xPos, yPos - 1));
      }
    }

    // Check South
    if (isValidPosition(xPos, yPos + 1)) {
      Card southCard = getCurrentGrid().get(yPos + 1).get(xPos).getCard();
      if (southCard != null) {
        adjacentCards.put(Direction.SOUTH, southCard);
        positions.put(Direction.SOUTH, new Posn(xPos, yPos + 1));
      }
    }

    // Check East
    if (isValidPosition(xPos + 1, yPos)) {
      Card eastCard = getCurrentGrid().get(yPos).get(xPos + 1).getCard();
      if (eastCard != null) {
        adjacentCards.put(Direction.EAST, eastCard);
        positions.put(Direction.EAST, new Posn(xPos + 1, yPos));
      }
    }

    // Check West
    if (isValidPosition(xPos - 1, yPos)) {
      Card westCard = getCurrentGrid().get(yPos).get(xPos - 1).getCard();
      if (westCard != null) {
        adjacentCards.put(Direction.WEST, westCard);
        positions.put(Direction.WEST, new Posn(xPos - 1, yPos));
      }
    }
  }

  /**
   * Checks if a position is valid on the game grid.
   *
   * @param x the x-coordinate to check
   * @param y the y-coordinate to check
   * @return true if the position is valid and not a hole, false otherwise
   */
  private boolean isValidPosition(int x, int y) {
    return x >= 0 && x < getCurrentGrid().get(0).size()
            && y >= 0 && y < getCurrentGrid().size()
            && getCurrentGrid().get(y).get(x).getCellType() != Cell.CellType.HOLE;
  }
}