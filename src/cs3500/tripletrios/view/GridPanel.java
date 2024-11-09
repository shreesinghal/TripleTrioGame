package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;


  public GridPanel(ReadOnlyTripleTrioModel model) {
    this.model = model;
  }

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
          g2d.setColor(new Color(212, 200, 0, 255));
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
    return new Dimension(CardView.cardWidth * model.getGridWidth(),
            CardView.cardHeight * model.getGridHeight());
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







  //mouse listener class --> moving from teh hand to the grid

}


