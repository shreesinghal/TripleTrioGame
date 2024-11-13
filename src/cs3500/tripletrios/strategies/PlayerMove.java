package cs3500.tripletrios.strategies;


import cs3500.tripletrios.model.Posn;

/**
 * This class represents a move that a player can make. It is to be used by the AI player to
 * indicate both the card chosen from the hand and the location it is placed at.
 */
public class PlayerMove {
  private int cardInHandInd;
  private Posn gridLocation;

  public PlayerMove(Posn gridLocation, int cardInHandInd) {
    this.gridLocation = gridLocation;
    this.cardInHandInd = cardInHandInd;
  }

  /**
   * Returns the x coordinate of the place the card should be placed.
   * @return x coord
   */
  public int getX() {
    return gridLocation.getX();
  }

  /**
   * Returns the Y coordinate of the place the card should be placed.
   * @return y coord
   */
  public int getY() {
    return gridLocation.getY();
  }

  /**
   * Gets the card calculated to play by the ai.
   * @return the card to play
   */
  public int getCardInd() {
    return this.cardInHandInd;
  }
}
