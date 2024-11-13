package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 *A strategy for the Triple Trio game that prioritizes placing cards in the corners of the grid.
 *This "corner-first" approach is based on the advantage that cards in corner positions.
 *Only expose two of their four attack values, making them more challenging for opponents to flip.
 */
public class CornerStrategy implements TripleTrioStrategy {
  private final TripleTrioModel model;
  private final List<Posn> cornerPositions;

  public CornerStrategy(TripleTrioModel model) {
    this.model = model;
    this.cornerPositions = calculateCornerPositions();
  }

  /**
   * Prioritizes placing a card in a corner, and if all corners are occupied, choses the top leftest card
   */
  @Override
  public PlayerMove moveCard() {
    for (Posn corner : cornerPositions) {
      model.documentCheckOnGrid(corner);
      if (model.getCurrentGrid().get(corner.getY()).get(corner.getX()).isEmpty()) {
        Card chosenCard = selectBestCardForCorner(corner);
        return new PlayerMove(corner, this.model.getPlayer().getHand().indexOf(chosenCard));
      }
    }
    return fallbackMove();
  }

  /**
   * Calculates the positions of the corners based on grid dimensions.
   */
  private List<Posn> calculateCornerPositions() {
    int width = model.getGridWidth();
    int height = model.getGridHeight();
    List<Posn> corners = new ArrayList<>();
    corners.add(new Posn(0, 0)); // top-left
    corners.add(new Posn(width - 1, 0)); // top-right
    corners.add(new Posn(0, height - 1)); // bottom-left
    corners.add(new Posn(width - 1, height - 1)); // bottom-right
    return corners;
  }

  /**
   * Selects the best card for the given corner based on attack values for the correct direction.
   */
  private Card selectBestCardForCorner(Posn corner) {
    List<Card> hand = model.getPlayer().getHand();
    Card bestCard = null;
    int maxScore = 0;

    for (Card card : hand) {
      int cornerScore = 0;

      if (corner.getX() == 0) {       // left side
        if (corner.getY() == 0) {
          // Top-left: prioritize South and East
          cornerScore = card.getSouth() + card.getEast();
        } else if (corner.getY() == model.getGridHeight() - 1) {
          // Bottom-left: prioritize North and East
          cornerScore = card.getNorth() + card.getEast();
        }
      } else if (corner.getX() == model.getGridWidth() - 1) {   // right side
        if (corner.getY() == 0) {
          // Top-right: prioritize South and West
          cornerScore = card.getSouth() + card.getWest();
        } else if (corner.getY() == model.getGridHeight() - 1) {
          // Bottom-right: prioritize North and West
          cornerScore = card.getNorth() + card.getWest();
        }
      }

      if (cornerScore > maxScore) {
        bestCard = card;
        maxScore = cornerScore;
      }
    }

    return bestCard != null ? bestCard : hand.get(0);   // first card if none possible
  }

  /**
   * Chooses the uppermost, leftmost open position and the best card.
   */
  private PlayerMove fallbackMove() {
    List<Posn> openPositions = new ArrayList<>();
    int gridWidth = model.getGridWidth();
    int gridHeight = model.getGridHeight();

    for (int y = 0; y < gridHeight; y++) {
      for (int x = 0; x < gridWidth; x++) {
        if (model.getCurrentGrid().get(y).get(x).getCellType() == Cell.CellType.CARDCELL
          && model.getCurrentGrid().get(y).get(x).isEmpty()) {
          Card bestCard = model.getPlayer().getHand().get(0);
          return new PlayerMove(new Posn (x, y),
            this.model.getPlayer().getHand().indexOf(bestCard));

        }
      }
    }

    return null;    // this would be if there are no open cells at all to place a card at
  }
}
