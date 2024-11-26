package model;

import java.util.ArrayList;

import model.card.Card;

/**
 * Represents a player's hand for a game of Triple Triad.
 */
public interface TripleTriadHand {
  /**
   * Returns the card at the given index in the hand and removes it
   * from the Hand.
   *
   * @param handIdx the index of the card (0-based)
   * @return the card at the index
   * @throws IllegalArgumentException if handIdx < 0 or handIdx >= hand.size()
   */
  Card fetchCard(int handIdx);

  /**
   * Returns a copy of the list of cards in this hand.
   *
   * @return a copy of the list of cards in this hand.
   */
  ArrayList<Card> fetchCards();

  /**
   * Returns the number of cards currently in the hand.
   *
   * @return the number of cards currently in the hand.
   */
  int fetchNumCards();


  /**
   * Returns whether this hand is equal to another hand.
   * Hands are equal if their List of cards are equal.
   * @param other the other object
   * @return if the hands are equal
   */
  @Override
  boolean equals(Object other);

  /**
   * Returns the hashcode for this hand.
   * The hashcode is based on the hash of the list of cards.
   * @return the hashcode for this hand
   */
  @Override
  int hashCode();
}
