package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Posn;

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


  private int cardWidth = 150;
  private int cardHeight = 150;

  private TripleTrioFeatureController features;

  private final Card card;
  private final Posn location;


  /**
   * Constructs a new `CardView` object, representing the card's shape and
   * position within the game's layout, based on the card's
   * logical coordinates and height.
   * @param card card that is being drawn
   * @param y the logical y-coordinate
   * @param cardHeight the height of the card
   */
  public CardView(Card card, int x, int y, int cardWidth, int cardHeight) {
    this.card = card;
    this.cardWidth = cardWidth;
    this.cardHeight = cardHeight;
    location = new Posn(x, y);
    drawRectangle(x, y, cardWidth, cardHeight);
  }

  private void drawRectangle(int x, int y,int cardWidth, int cardHeight) {
    moveTo(logicalToPixelWidth(x),logicalToPixelHeight(y));
    lineTo(cardWidth + logicalToPixelWidth(x), logicalToPixelHeight(y));
    lineTo(cardWidth + logicalToPixelWidth(x), cardHeight + logicalToPixelHeight(y));
    lineTo(logicalToPixelWidth(x), cardHeight + logicalToPixelHeight(y));
    closePath();
  }

  /**
   * REturns the card this cardview displays.
   * @return card
   */
  public Card getCard() {
    return this.card;
  }

  /**
   * Retrieves the card width.
   * @return width of card as int
   */
  public int getCardWidth() {

    return this.cardWidth;
  }


  /**
   * We are adding a multiplier so that each cell
   * is offset by the correct amount.
   * @param logical the correct space between cells
   * @return the amount of pixels to offset it by
   */
  private int logicalToPixelWidth(int logical) {
    //logical to physical
    return logical * this.cardWidth;
  }

  /**
   * We are adding a multiplier so that each cell
   * is offset by the correct amount.
   * @param logical the correct space between cells
   * @return the amount of pixels to offset it by
   */
  private int logicalToPixelHeight(int logical) {
    //logical to physical
    return logical * this.cardHeight;
  }





  /**
   * Gets the logical Size of the card.
   * @return Dimension of the card
   */
  public Dimension getLogicalSizeOfCard() {
    return new Dimension(this.cardWidth, this.cardHeight);
  }

  /**
   * Renders the `CardView` on the screen at the specified coordinates.
   * @param g2d a graphics object
   * @param x the x-coordinate of the card's top-left corner
   * @param y the y-coordinate of the card's top-left corner
   */
  public void draw(Graphics2D g2d, int x, int y) {
    drawRectangle(x, y, cardWidth, cardHeight);

    if (this.card.getColor() == CardColor.RED) {
      g2d.setColor(new Color(255,171,173,255));
    } else {
      g2d.setColor(new Color(72, 172, 255, 255));
    }

    g2d.fill(this);
    g2d.setColor(Color.black);
    g2d.draw(this);

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
