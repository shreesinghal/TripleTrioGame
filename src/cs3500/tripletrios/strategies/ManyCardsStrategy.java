package cs3500.tripletrios.strategies;


import cs3500.tripletrios.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManyCardsStrategy implements TripleTrioStrategy {

  private TripleTrioModel model;

  private int chosenCardIdx;

  private Posn chosenLocation;







  



  /**
   * This shows a move by the AI player.
   * It gives a reasonably calculated PlayerMove that can be made to the game.
   *
   * @return move
   */
  @Override
  public PlayerMove moveCard() {





    return new PlayerMove(chosenLocation, chosenCardIdx);
  }


  private ArrayList<ArrayList<Integer>> checkCardScoreInGrid() {
    ArrayList<ArrayList<Integer>> cardScoreInGrid = new ArrayList<>(this.model.getCurrentGrid().size());

    for (int x = 0; x < this.model.getOriginalGrid().size(); x++) {
      for (int y = 0; y < this.model.getOriginalGrid().get(x).size(); y++) {
        if (this.model.getCurrentGrid().get(y).get(x).equals(Cell.CellType.HOLE) || !this.model.getCurrentGrid().get(y).get(x).isEmpty()) {
          cardScoreInGrid.get(x).set(y, -1);
        } else {
          for (Card card : this.model.getPlayer().getHand()) {
            this.model.placeCard(x,y,card);
            this.model.executeBattlePhase(x,y);


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
   */
  private void executeBattlePhase(int xPos, int yPos) {
    List<Posn> flippedCards = battleAdjacentCards(xPos, yPos);
    while (!flippedCards.isEmpty()) {
      for (int i = flippedCards.size() - 1; i >= 0; i--) {
        List<Posn> cardsToBeAdded = battleAdjacentCards(flippedCards.get(i).getX(),
                flippedCards.get(i).getY());
        flippedCards.addAll(cardsToBeAdded);
        i += cardsToBeAdded.size();
        flippedCards.remove(flippedCards.get(i));
        i--;
      }
    }
  }


  private List<Posn> battleAdjacentCards(int xPos, int yPos) {
    List<Posn> flippedCards = new ArrayList<>();
    Card card = this.model.getCurrentGrid().get(yPos).get(xPos).getCard();

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
