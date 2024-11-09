package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;

import java.awt.*;
import java.awt.geom.Path2D;

public class CardView extends Path2D.Double {

  static int cardWidth = 150;
  static int cardHeight = 150;
  //don't give them a value and take a value into the constructor
  //either don't make it static or have updater method to change the value

  private Card card;

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
  static public Dimension getLogicalSizeOfCard() {
    return new Dimension(cardWidth, cardHeight);
  }

  public void draw(Graphics2D g2d, int x, int y) {
    g2d.setColor(this.card.getColor().getColor());
    g2d.fill(this);

    g2d.setColor(Color.black);
    Font font = new Font("Arial", Font.BOLD, 18);
    g2d.setFont(font);
    g2d.drawString(String.valueOf(this.card.getNorth()), x + cardWidth / 2, y + 18);
    g2d.drawString(String.valueOf(this.card.getSouth()), x + cardWidth / 2, cardHeight + y - 5);
    g2d.drawString(String.valueOf(this.card.getEast()), x + cardWidth - 18, y + cardHeight / 2);
    g2d.drawString(String.valueOf(this.card.getWest()), x + 5, y + cardHeight / 2);
  }


}
