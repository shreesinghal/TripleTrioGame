package cs3500.tripletrios.strategies;


import cs3500.tripletrios.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A strategy for the Triple Trio game that aims to maximize the number of opponent cards flipped
 * in a single turn. This "max-flip" approach selects both the card from the player's hand and the
 * position on the grid to place it, with the goal of flipping the highest number of neighboring
 * opponent cards.
 */

public class ManyCardsStrategy implements TripleTrioStrategy {

  private TripleTrioModel model;


  /**
   * Creates an instance of the strategy.
   * @param model representation of game state
   */
  public ManyCardsStrategy (TripleTrioModel model) {
    this.model = model;
  }

  /**
   * This shows a move by the AI player.
   * It gives a reasonably calculated PlayerMove that can be made to the game.
   *
   * @return move
   */
  @Override
  public PlayerMove moveCard() {
    ArrayList<ArrayList<PlayerMove>> calculatedCardScores = checkCardScoreInGrid();

    int maxScore = 0;
    int bestHandIdx = 0;
    for (int i = 0; i < calculatedCardScores.size(); i++) {
      for (int j = 0; j < calculatedCardScores.get(i).size(); j++) {
        if (maxScore < calculatedCardScores.get(j).get(i).getCardInd()) {
          maxScore = calculatedCardScores.get(j).get(i).getCardInd();
          bestHandIdx = calculatedCardScores.get(j).get(i).getY();
        }
      }
    }

    for (int i = 0; i < calculatedCardScores.size(); i++) {
      if (calculatedCardScores.get(i).contains(maxScore)) {
        return new PlayerMove(new Posn(calculatedCardScores.get(i).indexOf(maxScore), i), bestHandIdx );
      }
    }

    return null; //should never reach here
  }


  /**
   * This finds how many cards are flipped for every card in hand placed in every cell.
   * @return an 2D arraylist of PlayerMove objects
   */
  private ArrayList<ArrayList<PlayerMove>> checkCardScoreInGrid() {
    ArrayList<ArrayList<PlayerMove>> cardScoreInGrid = new ArrayList<>();

    for (int i = 0; i < this.model.getCurrentGrid().size(); i++) {
      cardScoreInGrid.add(new ArrayList<>());
      for (int j = 0; j < this.model.getCurrentGrid().get(i).size(); j++) {
        cardScoreInGrid.get(i).add(new PlayerMove(new Posn(0,0), 0));
      }
    }


    for (int y = 0; y < this.model.getOriginalGrid().size(); y++) {
      for (int x = 0; x < this.model.getOriginalGrid().get(0).size(); x++) {
        if (this.model.getCurrentGrid().get(y).get(x).getCellType().equals(Cell.CellType.HOLE)
                || !this.model.getCurrentGrid().get(y).get(x).isEmpty()) {
          cardScoreInGrid.get(x).set(y, new PlayerMove(new Posn(-1,-1), -1));
        } else {
          int bestCardScore = executeBattlePhase(0, 0, this.model.getPlayer().getHand().get(0));
          for (int c = 0; c < this.model.getPlayer().getHand().size(); c++) {
            int score = executeBattlePhase(x,y,this.model.getPlayer().getHand().get(c));
            if (score > bestCardScore) {
              bestCardScore = score;

            }
            cardScoreInGrid.get(y).set(y,new PlayerMove(new Posn(0, c), bestCardScore ));
          }
        }
      }

    }
    return cardScoreInGrid;
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
    int numCardsFlipped = flippedCards.size();
    while (!flippedCards.isEmpty()) {
      for (int i = flippedCards.size() - 1; i >= 0; i--) {
        List<Posn> cardsToBeAdded = battleAdjacentCards(flippedCards.get(i).getX(),
                flippedCards.get(i).getY(),
                this.model.getCurrentGrid().get(flippedCards.get(i).getY()).get(flippedCards.get(i).getX()).getCard());
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
            && card.getSouth() >this.model.getCurrentGrid().get(yPos + 1).get(xPos).getCard().getNorth()) {
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
      this.model.getCurrentGrid().get(yPos - 1).get(xPos).getCard().flipOwnership();
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
            && this.model.getCurrentGrid().get(posn.getY()).get(posn.getX()).getCellType() != Cell.CellType.HOLE;
  }






}
