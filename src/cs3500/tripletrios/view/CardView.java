package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class CardView extends Path2D.Double {

  static int cardWidth = 20;
  static int cardHeight = 20;

  private Card card;

  public CardView(Card card) {
    this.card = card;
    moveTo(0,0);
    lineTo(cardWidth, 0);
    lineTo(cardWidth, cardHeight);
    lineTo(0, cardHeight);
    closePath();

  }

  static public Dimension getLogicalSizeOfCard() {
    return new Dimension(cardWidth, cardHeight);
  }

  public void draw(Graphics2D g2d, int x, int y) {
    g2d.setColor(this.card.getColor().getColor());
    g2d.fill(this);

    g2d.setColor(Color.black);
    Font font = new Font("Arial", Font.BOLD, 18);
    g2d.setFont(font);

    FontMetrics metrics = g2d.getFontMetrics(font);
    g2d.drawString(String.valueOf(this.card.getNorth()), x + cardWidth / 2, y);
    g2d.drawString(String.valueOf(this.card.getSouth()), x + cardWidth / 2, cardHeight + y - 5);
    g2d.drawString(String.valueOf(this.card.getEast()), x + cardWidth - 5, y + cardHeight / 2);
    g2d.drawString(String.valueOf(this.card.getWest()), x, y + cardHeight / 2);
  }


}
