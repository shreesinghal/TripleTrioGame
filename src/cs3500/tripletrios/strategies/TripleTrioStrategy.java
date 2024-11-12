package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;

public interface TripleTrioStrategy {
  public Card chooseCardFromHand();
  public void placeCardToGrid();

}
