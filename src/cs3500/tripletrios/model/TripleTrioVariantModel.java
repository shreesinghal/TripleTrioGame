package cs3500.tripletrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * A variant of the Triple Trio game model that implements reverse and fallen ace rules.
 */
public class TripleTrioVariantModel extends TripleTrioGameModel {
  private final boolean reverseRule;
  private final boolean fallenAceRule;

  /**
   * Creates a new variant model with specified rules and initial game state.
   * @param deckOfCards the deck to use for the game
   * @param grid the initial grid configuration
   * @param reverse true if reverse rule is active
   * @param fallenAce true if fallen ace rule is active
   */
  public TripleTrioVariantModel(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid,
                                boolean reverse, boolean fallenAce) {
    super(deckOfCards, grid);
    this.reverseRule = reverse;
    this.fallenAceRule = fallenAce;
  }

  /**
   * Creates a new variant model with specified rules.
   * @param reverse true if reverse rule is active
   * @param fallenAce true if fallen ace rule is active
   */
  public TripleTrioVariantModel(boolean reverse, boolean fallenAce) {
    super();
    this.reverseRule = reverse;
    this.fallenAceRule = fallenAce;
  }

  @Override
  public void executeBattlePhase(int xPos, int yPos) {
    List<Posn> flippedCards = battleAdjacentCards(xPos, yPos);
    while (!flippedCards.isEmpty()) {
      for (int i = flippedCards.size() - 1; i >= 0; i--) {
        List<Posn> cardsToBeAdded = battleAdjacentCards(
                flippedCards.get(i).getX(),
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
    Card card = getCurrentGrid().get(yPos).get(xPos).getCard();

    if (card == null) {
      return Collections.emptyList();
    }

    Posn[] directions = {
            new Posn(xPos, yPos + 1),  // South
            new Posn(xPos - 1, yPos),  // West
            new Posn(xPos, yPos - 1),  // North
            new Posn(xPos + 1, yPos)   // East
    };

    for (int i = 0; i < 4; i++) {
      Posn adjLoc = directions[i];
      if (cardCanBattle(adjLoc)) {
        int attackValue = getCardValue(card, i);
        int defendValue = getOpponentCardValue(adjLoc, (i + 2) % 4);
        if (shouldFlip(attackValue, defendValue)) {
          getCurrentGrid().get(adjLoc.getY()).get(adjLoc.getX()).getCard().flipOwnership();
          flippedCards.add(adjLoc);
        }
      }
    }

    return flippedCards;
  }

  private boolean shouldFlip(int attackerValue, int defenderValue) {
    // Handle Fallen Ace rule
    if (fallenAceRule && attackerValue == 1 && defenderValue == 10) {
      return !reverseRule; // In reverse mode, 10 beats 1 instead
    }

    if (reverseRule) {
      // Special case when both rules are active: 10 beats 1
      if (fallenAceRule && attackerValue == 10 && defenderValue == 1) {
        return true;
      }
      return attackerValue < defenderValue;
    }

    return attackerValue > defenderValue;
  }

  private int getCardValue(Card card, int direction) {
    switch (direction) {
      case 0: return card.getSouth();
      case 1: return card.getWest();
      case 2: return card.getNorth();
      case 3: return card.getEast();
      default: throw new IllegalArgumentException("Invalid direction");
    }
  }

  private int getOpponentCardValue(Posn loc, int direction) {
    Card opponentCard = getCurrentGrid().get(loc.getY()).get(loc.getX()).getCard();
    return getCardValue(opponentCard, direction);
  }

  boolean cardCanBattle(Posn adjCardLoc) {
    return ensurePositionWithinBounds(adjCardLoc)
            && getCurrentGrid().get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard() != null
            && getCurrentGrid().get(adjCardLoc.getY()).get(adjCardLoc.getX()).getCard().getColor()
            == getOppPlayer().getColor();
  }

  private boolean ensurePositionWithinBounds(Posn posn) {
    return posn.getX() >= 0
            && posn.getX() < getCurrentGrid().get(0).size()
            && posn.getY() >= 0
            && posn.getY() < getCurrentGrid().size()
            && getCurrentGrid().get(posn.getY()).get(posn.getX()).getCellType() != Cell.CellType.HOLE;
  }
}