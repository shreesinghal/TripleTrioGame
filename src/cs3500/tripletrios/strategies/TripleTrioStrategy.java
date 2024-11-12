package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;

public interface TripleTrioStrategy {

  /**
   * A method that allows for strategically choosing which card to play.
   * @return the card chosen to play
   */
  public Card chooseCard();


  /**
   * A method that allows for placing a card strategically on the grid.
   */
  public void placeCard();

}
