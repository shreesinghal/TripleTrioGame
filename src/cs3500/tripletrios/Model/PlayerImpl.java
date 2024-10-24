package cs3500.tripletrios.Model;

import java.util.ArrayList;

public class PlayerImpl implements Player {

    ArrayList<Card> hand = new ArrayList<Card>();

    Color color;

    /**
     * Creates a new player with a hand of cards.
     * @param hand list of cards
     * @param color color of player
     */
    public PlayerImpl(ArrayList<Card> hand, Color color) {
        this.hand = hand;
        this.color = color;

    }


    /**
     * Returns the color of the player.
     *
     * @return color of the player
     */
    @Override
    public Color getColor() {
        return this.color;
    }

}
