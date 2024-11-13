package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A JPanel subclass that represents the main grid in the Triple Trios game.
 * This panel is responsible for displaying the grid based on the current game state
 * as provided by a read-only model, which provides the necessary grid dimensions
 * and cell details.
 */
public class GridPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;

  private TripleTrioController features;


  /**
   * Creating an instance of the grid panel.
   * @param model a readonly model
   */
  public GridPanel(ReadOnlyTripleTrioModel model) {
    this.model = model;
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   * @param features the controller
   */
  public void addClickListener(TripleTrioController features) {
    this.features = features;
    this.addMouseListener(new TTGridPanelClick());
  }

  /**
   * Renders a grid panel on the screen according to dimensions from the model.
   * Panel is resizeable, adjusting to the height and width of the current frame size.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;

    // make the cell width/height dependent on the panel width/height (this.width/height)
    int cellWidth = this.getWidth() / model.getGridWidth();
    int cellHeight = this.getHeight() / model.getGridHeight();

    ArrayList<ArrayList<Cell>> grid = this.model.getOriginalGrid();
    for (int y = 0; y < grid.size(); y++) {
      for (int x = 0; x < grid.get(0).size(); x++) {
        // set the color
        if (grid.get(y).get(x).getCellType() == Cell.CellType.CARDCELL) {
          g2d.setColor(new Color(212, 200, 50, 255));
        } else {
          g2d.setColor(new Color(192,192,192,255));
        }

        // set the size and draw the border
        g2d.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
      }
    }

    g2d.dispose();

  }

  /**
   * Gets the dimensions needed to make the grid on GUI view.
   * @return dimensions of the grid
   */
  public Dimension getDimensions() {
    return new Dimension(this.getWidth() / model.getGridWidth(),
      this.getHeight() / model.getGridHeight());
  }

  /**
   * We are adding a multiplier so that each cell
   * is offset by the correct amount.
   * @param logical the correct space between cells
   * @return the amount of pixels to offset it by
   */
  private int logicalToPixelWidth(int logical) {
    //logical to physical
    return logical * CardView.cardWidth;
  }

  private int logicalToPixelHeight(int logical) {
    //logical to physical
    return logical * CardView.cardHeight;
  }


  /**
   * Mouselistener class to account for what actions happen when a user clicks their mouse.
   * This is so when a user clicks on the GUI view, and action occurs.
   */

  private class TTGridPanelClick implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      //System.out.println("You clicked at " + pixelToCellHoriz(e.getX()) + " " + pixelToCellVert(e.getY()) + " of the grid.");
      try {
        features.handleCellClickForGrid(pixelToCellHoriz(e.getX()), pixelToCellVert(e.getY()));
      } catch (IOException ex) {
        System.out.print("Card couldn't be placed in grid.");
      }
    }


    private int pixelToCellHoriz(int Coord) {
      return (Coord / getDimensions().width + 1);
    }

    private int pixelToCellVert(int Coord) {
      return (Coord / getDimensions().height + 1);
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


