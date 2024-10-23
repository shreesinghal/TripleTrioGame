package cs3500.tripletrios.Model;

import java.util.ArrayList;

public class PlayerImpl implements Player {

    ArrayList<Card> hand = new ArrayList<Card>();

    Color color;

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
