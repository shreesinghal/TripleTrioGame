package cs3500.tripletrios.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TripleTrioVariantModel extends TripleTrioGameModel  {
  private final TripleTrioGameModel baseModel;
  private final boolean reverseRule;
  private final boolean fallenAceRule;

  public TripleTrioVariantModel(boolean reverse, boolean fallenAce) {
    this.baseModel = new TripleTrioGameModel();
    this.reverseRule = reverse;
    this.fallenAceRule = fallenAce;
  }

  // Delegate most methods to base model
  @Override
  public void startGame(Set<Card> deckOfCards, ArrayList<ArrayList<Cell>> grid) {
    baseModel.startGame(deckOfCards, grid);
  }

  // Override battle logic
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

    // South battle
    if (cardCanBattle(new Posn(xPos, yPos + 1))) {
      Card opponent = getCurrentGrid().get(yPos + 1).get(xPos).getCard();
      if (shouldFlip(card.getSouth(), opponent.getNorth())) {
        opponent.flipOwnership();
        flippedCards.add(new Posn(xPos, yPos + 1));
      }
    }

    // Similar logic for other directions...

    return flippedCards;
  }

  private boolean shouldFlip(int attackerValue, int defenderValue) {
    if (fallenAceRule && attackerValue == 1 && defenderValue == 10) {
      return !reverseRule;
    }
    if (reverseRule) {
      if (fallenAceRule && attackerValue == 10 && defenderValue == 1) {
        return true;
      }
      return attackerValue < defenderValue;
    }
    return attackerValue > defenderValue;
  }

  // Delegate other methods to base model
  @Override
  public WinningState getFinalState() {
    return baseModel.getFinalState();
  }

}