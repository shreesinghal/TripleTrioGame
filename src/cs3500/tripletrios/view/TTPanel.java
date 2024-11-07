package cs3500.tripletrios.view;

import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Interface to represent a single panel in the GUI view.
 */
public class TTPanel extends JPanel {

//  private final ReadOnlyTripleTrioModel model;

  public TTPanel() {


  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);



  }

  private void drawGrid(Graphics2D g2d, int x, int y) {

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
