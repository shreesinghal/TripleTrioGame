package cs3500.tripletrios.view;

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

/**
 * Interface to represent a single panel in the GUI view.
 */
public class TTPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;
  final int panelWidth, panelHeight;

  public TTPanel(ReadOnlyTripleTrioModel model) {
    this.model = model;

    this.panelWidth = model.getGridWidth() + 2;
    this.panelHeight = model.getGridHeight() + 2;
    this.addMouseListener(new ThreeTriosClick());
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    g2d.transform(getLogicalToPhysical());


    drawGrid(g2d, model.getGridWidth(), model.getGridHeight());
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



  //draw the grid and hand inside the panel





  private class ThreeTriosClick implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {

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
