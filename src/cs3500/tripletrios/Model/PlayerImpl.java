package cs3500.tripletrios.Model;

import java.util.ArrayList;

public class PlayerImpl implements Player {

    private ArrayList<Card> hand = new ArrayList<Card>();
    private final Color color;



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

    /**
     * Returns the players hand.
     * @return players hand
     */
    @Override
    public ArrayList<Card> getHand() {
        return this.hand;
    }

    /**
     * Removes a card from the hand.
     * @param card the card wanting to be removed
     */
    @Override
    public void removeCardFromHand(CardImpl card) {
        this.hand.remove(card);
    }


}