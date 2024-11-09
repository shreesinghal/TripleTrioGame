package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class GridPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;

  private int gridHeight;



  public GridPanel(ReadOnlyTripleTrioModel model, int height) {
    this.gridHeight = height;
    this.model = model;
    CardView.cardHeight = (this.gridHeight / this.model.getPlayer().getHand().size());
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    ArrayList<ArrayList<Cell>> grid = this.model.getOriginalGrid();
    for (int y = 0; y < grid.size(); y++) {
      for (int x = 0; x < grid.get(0).size(); x++) {
        if (grid.get(y).get(x).getCellType() == Cell.CellType.CARDCELL) {
          g2d.setColor(Color.YELLOW);
        } else {
          g2d.setColor(Color.GRAY);
        }

        g2d.fillRect(this.logicalToPixelWidth(x), this.logicalToPixelHeight(y), CardView.cardWidth,
                CardView.cardHeight);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(this.logicalToPixelWidth(x), this.logicalToPixelHeight(y), CardView.cardWidth,
                CardView.cardHeight);
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


