package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.TripleTrioModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A strategy for the Triple Trio game that aims to maximize the number of opponent cards flipped
 * in a single turn. This "max-flip" approach selects both the card from the player's hand and the
 * position on the grid to place it, with the goal of flipping the highest number of neighboring
 * opponent cards.
 */
public class MaximizeFlippedCardsStrat implements TripleTrioStrategy {
  private TripleTrioModel model;
  private Map<Posn, Map<Integer, Integer>> flipCounts = new HashMap<>();

  /**
   * This represents one type of strategy.
   * This strategy maximizes the number of flipped cards. This will be used by the AI player.
   * @param model representation of game state
   */
  public MaximizeFlippedCardsStrat(TripleTrioModel model) {
    this.model = model;
  }

  @Override
  public PlayerMove moveCard() {
    PlayerMove bestMove = null;
    int maxScore = 0;

    // for every card in the hand
    for (int cardIndex = 0; cardIndex < model.getPlayer().getHand().size(); cardIndex++) {
      Card currentCard = model.getPlayer().getHand().get(cardIndex);

      // check every cell in the grid
      for (int y = 0; y < model.getCurrentGrid().size(); y++) {
        for (int x = 0; x < model.getCurrentGrid().get(y).size(); x++) {
          model.documentCheckOnGrid(new Posn(x, y));

          if (!isValidPlacement(x, y)) {
            continue;
          }

          int score = executeBattlePhase(x, y, currentCard);

          if (score > maxScore) {
            maxScore = score;
            bestMove = new PlayerMove(new Posn(x, y), cardIndex);
          }
        }
      }
    }
    return bestMove;
  }

  public void setFlipCountForMove(Posn posn, int cardIndex, int flips) {
    flipCounts.putIfAbsent(posn, new HashMap<>());
    flipCounts.get(posn).put(cardIndex, flips);
  }

  /**
   * Executes the battle phase on the card at specified location.
   *
   * @param xPos x position of card
   * @param yPos y position of card
   * @param card the card that starts the execution
   */
  private int executeBattlePhase(int xPos, int yPos, Card card) {

    List<Posn> flippedCards = battleAdjacentCards(xPos, yPos, card);
    Map<Integer, Integer> cardFlips = flipCounts.get(new Posn(xPos, yPos));

    int numCardsFlipped = flippedCards.size();
    while (!flippedCards.isEmpty()) {
      for (int i = flippedCards.size() - 1; i >= 0; i--) {
        List<Posn> cardsToBeAdded = battleAdjacentCards(flippedCards.get(i).getX(),
                flippedCards.get(i).getY(),
            this.model.getCurrentGrid().
                    get(flippedCards.get(i).getY()).get(flippedCards.get(i).getX()).getCard());
        flippedCards.addAll(cardsToBeAdded);
        i += cardsToBeAdded.size();
        numCardsFlipped += cardsToBeAdded.size();
        flippedCards.remove(flippedCards.get(i));
        i--;
      }
    }
    return numCardsFlipped;
  }


  private List<Posn> battleAdjacentCards(int xPos, int yPos, Card card) {
    List<Posn> flippedCards = new ArrayList<>();

    if (card == null) {
      return Collections.emptyList();
    }

    Posn southLoc = new Posn(xPos, yPos + 1);
    Posn northLoc = new Posn(xPos, yPos - 1);
    Posn eastLoc = new Posn(xPos + 1, yPos);
    Posn westLoc = new Posn(xPos - 1, yPos);

    // this South vs bottom
    if (cardCanBattle(southLoc)
        && card.getSouth() > this.model.getCurrentGrid().get(yPos + 1).
            get(xPos).getCard().getNorth()) {
      this.model.getCurrentGrid().get(yPos + 1).get(xPos).getCard().flipOwnership();
      flippedCards.add(new Posn(xPos, yPos + 1));
    }

    // this West vs right
    if (cardCanBattle(westLoc)
        && card.getWest() > this.model.getCurrentGrid().get(yPos).get(xPos - 1).getCard().getEast()) {
      this.model.getCurrentGrid().get(yPos).get(xPos - 1).getCard().flipOwnership();
      flippedCards.add(new Posn(xPos - 1, yPos));
    }

    // this North vs top
    if (cardCanBattle(northLoc)
        && card.getNorth() > this.model.getCurrentGrid().get(yPos - 1).get(xPos).getCard().getSouth()) {
      this.model.getCurrentGrid().get(yPos - 1).
              get(xPos).getCard().flipOwnership();
      flippedCards.add(new Posn(xPos, yPos - 1));
    }

    // this East vs right
    if (cardCanBattle(eastLoc)
        && card.getEast() > this.model.getCurrentGrid().get(yPos).get(xPos + 1).getCard().getWest()) {
      this.model.getCurrentGrid().get(yPos).get(xPos + 1).getCard().flipOwnership();
      flippedCards.add(new Posn(xPos + 1, yPos));
    }

    return flippedCards;
  }

  private boolean cardCanBattle(Posn adjCardLoc) {
    return ensurePositionWithinBounds(new Posn(adjCardLoc.getX(), adjCardLoc.getY()))
      && this.model.getCurrentGrid().get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard() != null
      && this.model.getCurrentGrid().get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard().getColor()
      == this.model.getOppPlayer().getColor();
  }

  private boolean ensurePositionWithinBounds(Posn posn) {
    return posn.getX() >= 0
      && posn.getX() < this.model.getCurrentGrid().get(0).size()
      && posn.getY() >= 0
      && posn.getY() < this.model.getCurrentGrid().size()
      && this.model.getCurrentGrid().get(posn.getY()).
            get(posn.getX()).getCellType() != Cell.CellType.HOLE;
  }

  /**
   * Checks if a position is valid for placing a card.
   * @param x x-coordinate on the grid
   * @param y y-coordinate on the grid
   * @return true if the position is valid, false otherwise
   */
  private boolean isValidPlacement(int x, int y) {
    Cell cell = model.getCurrentGrid().get(y).get(x);
    return cell.getCellType() != Cell.CellType.HOLE && cell.isEmpty();
  }


}
