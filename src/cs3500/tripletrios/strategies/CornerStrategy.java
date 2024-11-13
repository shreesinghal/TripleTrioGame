package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import java.util.ArrayList;

public class CornerStrategy implements TripleTrioStrategy {

  private ReadOnlyTripleTrioModel model;

  private int chosenCardIndex;

  private Posn chosenLocation;


  private ArrayList<Cell> getOpenCorners() {

    ArrayList<Cell> openCorners = new ArrayList<>();
    Cell cell1 = model.getCurrentGrid().get(0).get(0);
    Cell cell2 = model.getCurrentGrid().get(0).get(model.getGridWidth());
    Cell cell3 = model.getCurrentGrid().get(model.getGridHeight()).get(0);
    Cell cell4 = model.getCurrentGrid().get(model.getGridHeight()).get(model.getGridWidth());

    if (isOpenToPlace(cell1)) {
      openCorners.add(cell1);
    }
    if (isOpenToPlace(cell2)) {
      openCorners.add(cell2);
    }
    if (isOpenToPlace(cell3)) {
      openCorners.add(cell3);
    }
    if (isOpenToPlace(cell4)) {
      openCorners.add(cell4);
    }
    return openCorners;
  }

  private static boolean isOpenToPlace(Cell cell1) {
    return !cell1.isEmpty() && cell1.getCellType() != Cell.CellType.HOLE;
  }

  /**
   * This shows a move by the AI player.
   * It gives a reasonably calculated PlayerMove that can be made to the game.
   *
   * @return move
   */
  @Override
  public PlayerMove moveCard() {
    ArrayList<Cell> openCorners = getOpenCorners();

    ArrayList<Integer> bestScores = new ArrayList<>(); // top left, top right, bottom left, bottom right
    ArrayList<Card> bestCards = new ArrayList<>(); // top left, top right, bottom left, bottom right
    // top left
    int biggestTopLeftScore = 0;
    for (Card card : this.model.getPlayer().getHand()) {
      int cardScore = (card.getEast() + card.getSouth()) / 2;
      if (cardScore > biggestTopLeftScore) {
        biggestTopLeftScore = cardScore;
        bestCards.add(card);
      }
    }
    bestScores.add(biggestTopLeftScore);

    // top right
    int biggestTopRightScore = 0;
    for (Card card : this.model.getPlayer().getHand()) {
      int cardScore = (card.getWest() + card.getSouth()) / 2;
      if (cardScore > biggestTopRightScore) {
        biggestTopRightScore = cardScore;
        bestCards.add(card);
      }
    }

    // Bottom left
    int biggestBottomLeftScore = 0;
    for (Card card : this.model.getPlayer().getHand()) {
      int cardScore = (card.getEast() + card.getNorth()) / 2;
      if (cardScore > biggestBottomLeftScore) {
        biggestBottomLeftScore = cardScore;
        bestCards.add(card);
      }
    }

    // Bottom right
    int biggestBottomRightScore = 0;
    for (Card card : this.model.getPlayer().getHand()) {
      int cardScore = (card.getWest() + card.getNorth()) / 2;
      if (cardScore > biggestBottomRightScore) {
        biggestBottomRightScore = cardScore;
        bestCards.add(card);
      }
    }

    while (!openCorners.isEmpty()) {
      int bestScore = bestScores.get(0);
      for (Integer score : bestScores) {
        if (score > bestScore) {
          bestScore = score;
        }
      }


    }

    return new PlayerMove(new Posn(1,2), 3);
  }
}
