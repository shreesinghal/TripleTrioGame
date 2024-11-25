package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioAIPlayerContr;
import cs3500.tripletrios.controller.TripleTrioFeatureController;
import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A JPanel subclass that represents the main grid in the Triple Trios game.
 * This panel is responsible for displaying the grid based on the current game state
 * as provided by a read-only model, which provides the necessary grid dimensions
 * and cell details.
 */
public class GridPanel extends JPanel {

  private final ReadOnlyTripleTrioModel model;

  private TripleTrioFeatureController features;

  private final Map<Point, CardView> placedCards; // Tracks cards placed on the grid


  /**
   * Creating an instance of the grid panel.
   * @param model a readonly model
   */
  public GridPanel(ReadOnlyTripleTrioModel model) {
    this.model = model;
    this.placedCards = new HashMap<>();
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   * @param features the controller
   */
  public void addClickListener(TripleTrioHumanPlayerContr features) {
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

    // draw the grid
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

    // Draw the placed cards
    for (Map.Entry<Point, CardView> entry : placedCards.entrySet()) {
      Point point = entry.getKey();
      CardView card = entry.getValue();
      card = new CardView(card.getCard(), point.x, point.y, cellWidth, cellHeight);
      card.draw(g2d, point.x * cellWidth, point.y * cellHeight);
    }

    g2d.dispose();

  }

  /**
   * Places a card on the grid.
   * @param x x coord
   * @param y y coord
   * @param card card to place
   */
  public void placeCardOnGrid(int x, int y, CardView card) {
    placedCards.put(new Point(x, y), card); // Store card at the specified grid cell
    repaint(); // Trigger a repaint to show the updated grid
  }

  /**
   * Gets the width of the grid.
   * @return width as an int
   */
  private int getCardWidthInGrid() {
    return this.getWidth() / model.getGridWidth();
  }


  /**
   * Gets the height of the grid.
   * @return height as a int
   */
  private int getCardHeightInGrid() {
    return this.getHeight() / model.getGridHeight();
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
    return logical * this.getCardWidthInGrid();
  }

  private int logicalToPixelHeight(int logical) {
    //logical to physical
    return logical * this.getCardHeightInGrid();
  }

  /**
   * Updates the grid with the current placed cards by clearing existing cards and
   * adding the new ones based on the current game state.
   */
  public void updateCardsOnGrid() {
    int cellWidth = this.getWidth() / model.getGridWidth();
    int cellHeight = this.getHeight() / model.getGridHeight();

    // populated palced cards
    placedCards.clear();
    for (int y = 0; y < model.getCurrentGrid().size(); y++) {
      for (int x = 0; x < model.getCurrentGrid().get(y).size(); x++) {
        Cell cell = model.getCurrentGrid().get(y).get(x);
        if (cell.getCellType() == Cell.CellType.CARDCELL
                && !cell.isEmpty()) {
          placedCards.put(new Point(x, y),
                  new CardView(cell.getCard(),
                          x,
                          y,
                          cellWidth,
                          cellHeight));
        }
      }
    }
    this.repaint();
  }

  /**
   * Adds a controller features to the grid.
   * @param controller2 features
   */
  public void addAIListener(TripleTrioAIPlayerContr controller2) {
    this.features = controller2;
  }


  /**
   * Mouse listener class to account for what actions happen when a user clicks their mouse.
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
      features.handleCellClickForGrid(pixelToCellHorizontal(e.getX()),
              pixelToCellVertical(e.getY()));
    }


    private int pixelToCellHorizontal(int coordValue) {
      return (coordValue / getDimensions().width + 1);
    }

    private int pixelToCellVertical(int coordValue) {
      return (coordValue / getDimensions().height + 1);
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
      //no implementation needed
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      //no implementation needed
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      //no implementation needed
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
      //no implementation needed
    }
  }

}


