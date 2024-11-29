package cs3500.threetrios.providers.view.tripletriadgui.components;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;
import cs3500.threetrios.providers.model.Direction;
import cs3500.threetrios.providers.model.PlayerType;

import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.controller.ViewFeatures;


/**
 * Represents the board panel for the game
 * triple trios. Displays holes, cells, and
 * cards based on the given model.
 */
public class BoardPanel extends JPanel {
  private final ReadOnlyTripleTriad model;
  private final List<ViewFeatures> featureListeners;
  private final Color HOLE_COLOR = new Color(255,255,197);
  private final Color CELL_COLOR = Color.DARK_GRAY;
  private final int height;
  private final int width;
  private final Map<PlayerType, Color> playerColors = Map.of(
          PlayerType.PLAYER_A, Color.RED,
          PlayerType.PLAYER_B, Color.BLUE
  );

  /**
   * Creates a board panel with the model and given dimensions.
   * @param model the game model
   * @param width the width of the panel
   * @param height the height of the panel
   * @throws IllegalArgumentException if model is null or dimensions are negative
   */
  public BoardPanel(ReadOnlyTripleTriad model, int width, int height) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height cannot be negative.");
    }

    this.model = model;
    this.width = width;
    this.height = height;
    this.featureListeners = new ArrayList<>();
    this.addMouseListener(new MouseEventsListener());
  }

  /**
   * Adding a feature listener to this view component.
   * @param features View Feature to add
   * @throws IllegalArgumentException if features is null
   */
  public void addFeatures(ViewFeatures features) {
    if (features == null) {
      throw new IllegalArgumentException("Features cannot be null.");
    }
    this.featureListeners.add(features);
  }

  /**
   * Advances the model by repainting when updates occur.
   */
  public void advance() {
    this.repaint();
  }

  /**
   * Returns the preferred physical size as a dimension.
   * @return Dimension of preferred physical size
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }

  /**
   * Returns the preferred model size as a dimension.
   * @return Dimension of preferred model size
   */
  private Dimension getPreferredModelSize() {
    return new Dimension(this.model.numBoardRows(), this.model.numBoardColumns());
  }

  /**
   * Returns the preferred logical size as a dimension.
   * @return Dimension of preferred logical size
   */
  private Dimension getPreferredLogicalSize() {
    return new Dimension(100, 100);
  }


  /**
   * Overrides paint component to draw all cells
   * that are present in the board.
   * @param graphics Graphics object to draw with
   */
  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics.create();
    g2d.transform(this.transformLogicalToPhysical());
    this.drawBoard(g2d);
  }

  /**
   * Draws each cell of the Board.
   * @param g2d Graphics
   */
  private void drawBoard(Graphics2D g2d) {
    for (int row = 0; row < this.model.numBoardRows(); row++) {
      for (int column = 0; column < this.model.numBoardColumns(); column++) {
        this.drawCells(g2d, row, column);
      }
    }
  }

  /**
   * Draws an individual cell at its location on the
   * screen, given by the row and column coordinate.
   * @param g2d graphics object to draw with
   * @param row row index (0-based)
   * @param column column index (0-based)
   */
  private void drawCells(Graphics2D g2d, int row, int column) {
    if (this.model.canPlayerPlayAtCoordinate(row, column)) {
      this.drawCell(g2d, row, column, false);
    } else {
      this.drawUnplayableCell(g2d, row, column);
    }
  }

  /**
   * Draws a cell at its location on the grid.
   * Specifically draws cells that are occupied or are holes.
   * @param g2d graphics object to draw with
   * @param row row index (0-based)
   * @param column column index (0-based)
   */
  private void drawUnplayableCell(Graphics2D g2d, int row, int column) {
    if (this.model.fetchBoard()[row][column].hasCard()) {
      this.drawCard(g2d, row, column);
    }
    else {
      this.drawCell(g2d, row, column, true);
    }
  }

  /**
   * Draws a cell at its location on the grid.
   * Specifically draws cells that are unoccupied and are playable,
   * or are holes.
   * Colors holes as grey and empty playable cells as yellow.
   * @param g2d graphics object to draw with
   * @param row row index (0-based)
   * @param column column index (0-based)
   * @param fill boolean to fill a hole cell
   */
  private void drawCell(Graphics2D g2d, int row, int column, boolean fill) {
    Dimension logicalLocation = transformModelToLogical(row, column);
    Dimension cellSize = transformModelToLogical(1, 1);
    Shape cell = new Rectangle(
            (int) logicalLocation.getWidth(),
            (int) logicalLocation.getHeight(),
            (int) cellSize.getWidth(),
            (int) cellSize.getHeight());
    if (!fill) {
      g2d.setColor(this.HOLE_COLOR);
      g2d.fill(cell);
    }
    g2d.setColor(this.CELL_COLOR);
    g2d.draw(cell);
  }

  /**
   * Draws card on the board based on coordinate.
   * @param g2d Graphics object to draw with
   * @param row Row index of Board
   * @param column Colum index of Board
   */
  private void drawCard(Graphics2D g2d, int row, int column) {
    Card card = this.model.cardAtCoordinate(row, column);
    String north = card.fetchNumAtDirection(Direction.NORTH).toString();
    String south = card.fetchNumAtDirection(Direction.SOUTH).toString();
    String east = card.fetchNumAtDirection(Direction.EAST).toString();
    String west = card.fetchNumAtDirection(Direction.WEST).toString();

    Dimension logicalPoint = this.transformModelToLogical(row, column);
    Dimension logicalSize = this.transformModelToLogical(1, 1);

    GraphicCard graphicCard = new GraphicCard(
            (int) logicalSize.getWidth(),
            (int) logicalSize.getHeight(),
            (int) logicalPoint.getWidth(),
            (int) logicalPoint.getHeight(),
            north, south, east, west);

    graphicCard.drawCard(g2d, playerColors.get(this.model.ownerAtCoordinate(row, column)));
  }

  /**
   * Transforms Dimension of model to logical coordinate.
   * @param row row index (0-based)
   * @param column column index (0-based)
   * @return new Dimension in logical coordinate
   */
  private Dimension transformModelToLogical(double row, double column) {
    double rowDim = getPreferredLogicalSize().getWidth() / this.getPreferredModelSize().getWidth();
    double colDim =
            getPreferredLogicalSize().getHeight() / this.getPreferredModelSize().getHeight();
    return new Dimension((int) (column * colDim), (int) (row * rowDim));
  }

  /**
   * Creates affine transform from logical to physical.
   * @return Affine transform in logical coordinates.
   */
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform transform = new AffineTransform();
    Dimension preferred = this.getPreferredLogicalSize();
    transform.scale(this.getWidth() / preferred.getWidth(),
            this.getHeight() / preferred.getHeight());
    return transform;
  }

  /**
   * Creates affine transform from physical to physical.
   * @return Affine transform in physical coordinates.
   */
  private AffineTransform transformPhysicalToModel() {
    AffineTransform transform = new AffineTransform();
    Dimension preferred = this.getPreferredModelSize();
    transform.scale(preferred.getHeight() / this.getWidth(),
            preferred.getWidth() / this.getHeight());

    return transform;
  }

  /**
   * A listener class for mouse events.
   */
  private class MouseEventsListener extends MouseInputAdapter {
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * This plays the selected card to the board at the location clicked.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      Point physicalClickPoint = e.getPoint();
      Point2D logicalClickPoint = transformPhysicalToModel().transform(physicalClickPoint,
              null);
      for (ViewFeatures listener : BoardPanel.this.featureListeners) {
        listener.playAtBoardPoint((int) logicalClickPoint.getY(), (int) logicalClickPoint.getX());
      }
    }
  }
}
