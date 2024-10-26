package cs3500.tripletrios.Model;

import java.util.ArrayList;

public interface Player {

    /**
     * Returns the color of the player.
     * @return color of the player
     */
    Color getColor();

    /**
     * Returns the players hand.
     * @return players hand
     */
    ArrayList<Card> getHand();



}
