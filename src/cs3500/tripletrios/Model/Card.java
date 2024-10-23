package cs3500.tripletrios.Model;

import java.util.Map;

public interface Card {

    /**
     * Returns the name of the current card.
     * @return String of the name
     */
    String getName();

    /**
     * Returns the player that the Card belongs to.
     * @return the Player
     */
    Color getColor();

    /**
     * Switch ownership of the card from current to opposing player
     */
    void flipOwnership();

    /**
     * Returns all the card's attack values.
     * @return Map containing the 4 attack values
     */
    Map<Direction, Integer> getAttackValues();



}
