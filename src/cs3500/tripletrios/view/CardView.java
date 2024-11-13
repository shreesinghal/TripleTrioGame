package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

/**
 * Implements the GUI view for the Triple Trios game, displaying the main game board,
 * player hands, and managing visual updates based on the current game state.
 */
public class CardView extends Path2D.Double {

  static int cardWidth = 150;
  static int cardHeight = 150;

  private TripleTrioController features;

  private Card card;


  /**
   * Constructs a new `CardView` object, representing the card's shape and
   * position within the game's layout, based on the card's
   * logical coordinates and height.
   * @param card card that is being drawn
   * @param y the logical y-coordinate
   * @param cardHeight the height of the card
   */
  public CardView(Card card, int y, int cardHeight) {
    this.card = card;
    this.cardHeight = cardHeight;
    moveTo(0,logicalToPixel(y));
    lineTo(cardWidth, logicalToPixel(y));
    lineTo(cardWidth, cardHeight + logicalToPixel(y));
    lineTo(0, cardHeight + logicalToPixel(y));
    closePath();

  }



  /**
   * We are adding a multiplier so that each cell
   * is offset by the correct amount.
   * @param logical the correct space between cells
   * @return the amount of pixels to offset it by
   */
  private int logicalToPixel(int logical) {
    //logical to physical
    return logical * CardView.cardHeight;
  }





  /**
   * Gets the logical Size of the card.
   * @return Dimension of the card
   */
  public static Dimension getLogicalSizeOfCard() {
    return new Dimension(cardWidth, cardHeight);
  }

  /**
   * Renders the `CardView` on the screen at the specified coordinates.
   * @param g2d a graphics object
   * @param x the x-coordinate of the card's top-left corner
   * @param y the y-coordinate of the card's top-left corner
   */
  public void draw(Graphics2D g2d, int x, int y) {
    if (this.card.getColor() == CardColor.RED) {
      g2d.setColor(new Color(255,171,173,255));
    } else {
      g2d.setColor(new Color(72, 172, 255, 255));
    }

    g2d.fill(this);

    g2d.setColor(Color.black);
    Font font = new Font("Arial", Font.BOLD, 18);
    g2d.setFont(font);
    g2d.drawString(String.valueOf(card.getNorth() == 10 ? "A" :
            card.getNorth()), x + cardWidth / 2, y + 18);
    g2d.drawString(String.valueOf(card.getSouth() == 10 ? "A" :
            card.getSouth()), x + cardWidth / 2, cardHeight + y - 5);
    g2d.drawString(String.valueOf(card.getEast() == 10 ? "A" :
            card.getEast()), x + cardWidth - 18, y + cardHeight / 2);
    g2d.drawString(String.valueOf(card.getWest() == 10 ? "A" :
            card.getWest()), x + 5, y + cardHeight / 2);
  }




}
