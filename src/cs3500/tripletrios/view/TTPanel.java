package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Set;

/**
 * Interface to represent a single panel in the GUI view.
 */
public class TTPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;
  final int panelWidth, panelHeight;

  private ArrayList<CardView> deckViewList;

  public TTPanel(ReadOnlyTripleTrioModel model) {
    this.model = model;
    Set<Card> deck = model.getDeck();
    ArrayList<CardView> deckViewList = new ArrayList<>();
    for (Card card : deck) {
      deckViewList.add(new CardView(card));
    }

    this.panelWidth = model.getGridWidth() + 2;
    this.panelHeight = model.getGridHeight() + 2;
    this.addMouseListener(new ThreeTriosClick());
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.transform(getLogicalToPhysical());

    //drawing the grid
    drawGrid(g2d, model.getGridWidth(), model.getGridHeight());
    //drawing hands
    drawHands(g2d, model.getPlayer().getHand().size());

    //drawing on the hands


    //drawing on the grid
    ArrayList<CardView> deckViewListCopy = new ArrayList<CardView>(deckViewList);
    for (int y = 0; y < this.model.getCurrentGrid().size(); y++ ) {
      for (int x = 0; x < this.model.getCurrentGrid().get(0).size(); x++) {
        if (!this.model.getCurrentGrid().get(y).get(x).isEmpty()) {
          deckViewListCopy.get(0).draw(g2d, x, y);
          deckViewListCopy.remove(0);
        }
      }
    }

    //create a helper method for when we want to draw a card on the panel that paint component calls
    //REPAINT() --> calls paintComponent to redraw WITH the new changes added
  }

  private void drawGrid(Graphics2D g2d, int rows, int cols) {
    // draw horizontal lines
    for (int y = 0; y <= rows; y++) {
      drawLine(g2d,
        new Point(CardView.cardWidth, CardView.cardHeight * y),
        new Point(panelWidth - CardView.cardWidth, CardView.cardHeight * y));
    }

    // draw vertical lines
    for (int x = 0; x <= cols; x++) {
      drawLine(g2d,
        new Point(CardView.cardWidth * x, 0),
        new Point(CardView.cardWidth * x, panelHeight));
    }
  }

  private void drawHands(Graphics2D g2d, int numCards) {
    // draw the hand on the left side of the grid
    for (int i = 0; i <= numCards; i++) {
      drawLine(g2d,
              new Point(0, CardView.cardHeight * i),
              new Point(CardView.cardWidth, CardView.cardHeight * i));
    }

    // draw the hand on the left side of the grid
    for (int i = 0; i <= numCards; i++) {
      drawLine(g2d,
              new Point(panelWidth -  CardView.cardWidth, CardView.cardHeight * i),
              new Point(panelWidth, CardView.cardHeight * i));
    }

  }

  private void drawLine(Graphics2D g2d, Point2D start, Point2D end) {
    Point2D logicalStart = getModelToLogical().transform(start, null);
    Point2D logicalEnd = getModelToLogical().transform(end, null);
    g2d.drawLine((int)logicalStart.getY(), (int)logicalStart.getX(), (int)logicalStart.getY(), (int)logicalEnd.getX());
  }

  private AffineTransform getModelToLogical() {
    AffineTransform xform = new AffineTransform();
    Dimension logicalDims = CardView.getLogicalSizeOfCard();
    xform.scale(logicalDims.getWidth() / this.panelWidth, logicalDims.getHeight() / this.panelHeight);
    //this allows you to scale
    return xform;
  }


  private AffineTransform getLogicalToPhysical() {
    AffineTransform xform = new AffineTransform();
    Dimension logicalDims = CardView.getLogicalSizeOfCard();
    xform.scale(this.getWidth() / logicalDims.getWidth(), this.getHeight() / logicalDims.getHeight());
    return xform;
  }








  private class ThreeTriosClick implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      //call a handlecellclick in the controller
      //this is a method which will then highlight the
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
  }


}
