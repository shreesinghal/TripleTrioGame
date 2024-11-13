package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Posn;

/**
 * This class represents a move that a player can make. It is to be used by the AI player to
 * indidcate both the card chosen from the hand and the location it is placed at.
 */
public class PlayerMove {
  private int cardInHandInd;
  private Posn gridLocation;

  public PlayerMove(Posn gridLocation, int cardInHandInd) {
    this.gridLocation = gridLocation;
    this.cardInHandInd = cardInHandInd;
  }
}
