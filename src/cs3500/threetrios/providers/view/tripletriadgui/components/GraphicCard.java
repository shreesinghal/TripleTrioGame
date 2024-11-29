package cs3500.threetrios.providers.view.tripletriadgui.components;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;

/**
 * Represents a graphical card for a Three Trios GUI.
 * This keeps track of the width, height, x and y location
 * of the card, as well as the four attack values as strings.
 */
public class GraphicCard extends Path2D.Double {
  private final int width;
  private final int height;
  private final int xLoc;
  private final int yLoc;
  private final String northNumber;
  private final String southNumber;
  private final String eastNumber;
  private final String westNumber;

  /**
   * Creates a GraphicCard with the logical width and height
   * and the x,y logical coordinate location to place the card.
   * The four attack values are given as strings.
   * @param width the card width
   * @param height the card height
   * @param xLoc the x location
   * @param yLoc the y location
   * @param northNumber the northern attack value
   * @param southNumber the southern attack value
   * @param eastNumber the eastern attack value
   * @param westNumber the western attack value
   * @throws IllegalArgumentException if dimension or location values are negative
   * @throws IllegalArgumentException if attack values are null
   */
  public GraphicCard(int width, int height,
                     int xLoc, int yLoc,
                     String northNumber, String southNumber, String eastNumber, String westNumber) {
    super(new Rectangle(xLoc, yLoc, width, height));
    if (width < 0 || height < 0 || xLoc < 0 || yLoc < 0) {
      throw new IllegalArgumentException(
              "Dimension and coordinate values cannot be negative.");
    }
    if (northNumber == null || southNumber == null || eastNumber == null || westNumber == null) {
      throw new IllegalArgumentException("Attack values cannot be null.");
    }
    this.width = width;
    this.height = height;
    this.xLoc = xLoc;
    this.yLoc = yLoc;
    this.northNumber = northNumber;
    this.southNumber = southNumber;
    this.eastNumber = eastNumber;
    this.westNumber = westNumber;
  }

  /**
   * Draws a card with the four attack values
   * displayed over a card colored with the given
   * color representing the owner.
   * @param g2d the graphics object to draw with
   * @param color the color of the card
   * @throws IllegalArgumentException if graphics object or color is null
   */
  public void drawCard(Graphics2D g2d, Color color) {
    if (g2d == null || color == null) {
      throw new IllegalArgumentException("Graphics object and color cannot be null.");
    }

    g2d.setColor(color);
    g2d.fill(this);
    g2d.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(0.05F));
    g2d.draw(new Rectangle(xLoc, yLoc, width, height));
    g2d.setFont(new Font("Arial", Font.BOLD, Math.max(1, height / 4)));

    FontMetrics fontMetrics = g2d.getFontMetrics();
    double stringHeight = fontMetrics.getHeight() / 2.0;

    g2d.drawString(this.northNumber,
            (int) (this.xLoc + this.width / 2.0 - fontMetrics.stringWidth(this.northNumber) / 2.0),
            (int) (this.yLoc + this.height / 4.0));
    g2d.drawString(this.southNumber,
            (int) (this.xLoc + this.width / 2.0 - fontMetrics.stringWidth(this.southNumber) / 2.0),
            (int) (this.yLoc + 7 * this.height / 8.0));
    g2d.drawString(this.eastNumber,
            this.xLoc + this.width - fontMetrics.stringWidth(this.eastNumber),
            (int) (this.yLoc + this.height / 2.0 + stringHeight / 2.0));
    g2d.drawString(this.westNumber,
            this.xLoc,
            (int) (this.yLoc + this.height / 2.0 + stringHeight / 2.0));
  }
}
