package cs3500.threetrios.providers.view.tripletriadgui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import cs3500.threetrios.providers.model.Direction;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;
import cs3500.threetrios.providers.model.card.Card;
import cs3500.threetrios.providers.controller.ViewFeatures;

/**
 * Represents a hand in the graphic view of a
 * game of Three Trios. Cards are displayed with the
 * first card at the top and the last card at the bottom.
 * Numbers are displayed in compass directions.
 * Player A's cards are red and Player B's cards are blue.
 * Clicked cards are highlighted and un-highlighted when
 * another card is clicked or when it is played to the board.
 */
public class HandPanel extends JPanel {
  private final ReadOnlyTripleTriad model;
  private final PlayerType player;
  private final List<ViewFeatures> featureListeners;
  private int handIdx = -1;
  private final int height;
  private final int width;
  private final Map<PlayerType, Color> playerColors = Map.of(
          PlayerType.PLAYER_A, Color.RED,
          PlayerType.PLAYER_B, Color.BLUE
  );

  /**
   * Creates a hand panel for the given player.
   * @param model the read only triple triad model
   * @param player the player type whose hand we are displaying
   * @throws IllegalArgumentException if model or player is null or dimensions are negative
   */
  public HandPanel(ReadOnlyTripleTriad model, PlayerType player, int width, int height) {
    if (model == null || player == null) {
      throw new IllegalArgumentException("Model and player cannot be null.");
    }
    if (width < 0 || height < 0) {
      throw new IllegalArgumentException("Width and height cannot be negative.");
    }
    this.model = model;
    this.player = player;
    this.width = width;
    this.height = height;
    this.featureListeners = new ArrayList<>();
    this.setBackground(Color.lightGray);
    this.addMouseListener(new MouseEventsListener());
  }

  /**
   * Returns the dimensions of the logical coordinates
   * (with (0,0) in the top-left and width x height of our logical size).
   * @return the logical dimension
   */
  private Dimension getLogicalSize() {
    return new Dimension(11, this.model.fetchHand(this.player).size() * 20);
  }

  /**
   * Returns the dimensions of the panel coordinates
   * (with (0,0) in upper-left and width x height in pixels).
   * @return the panel dimensions
   */
  public Dimension getPreferredSize() {
    return new Dimension(this.width, this.height);
  }

  /**
   * Returns the dimensions of the model coordinates
   * (with (0,0) in upper-left, width and height in board rows and columns).
   * @return the model dimensions
   */
  private Dimension getModelSize() {
    return new Dimension(1, this.model.fetchHand(this.player).size());
  }

  /**
   * Adds the given feature to this panel's list of features.
   * @param features a feature object this panel uses
   * @throws IllegalArgumentException if features is null
   */
  public void addFeatures(ViewFeatures features) {
    if (features == null) {
      throw new IllegalArgumentException("Features cannot be null.");
    }
    this.featureListeners.add(features);
  }

  /**
   * Resets this panel's selected hand index to -1,
   * indicating no selected card.
   */
  public void advance() {
    this.handIdx = -1;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D g2d = (Graphics2D) graphics.create();
    g2d.transform(this.transformLogicalToPhysical());

    List<Card> hand = this.model.fetchHand(this.player);
    for (int index = 0; index < hand.size(); index++) {
      this.drawCard(g2d, index, (index == this.handIdx));
    }
  }

  /**
   * Draws a single Three Trios card with the attack values
   * at their compass location. Cards of Player A are red
   * and cards of Player B are blue. A card may be colored
   * a shade darker to indicate it is currently selected.
   * @param g2d the graphics object
   * @param index the hand index of the card to draw
   * @param highlight whether to highlight this card
   */
  private void drawCard(Graphics2D g2d, int index, boolean highlight) {
    Card card = this.model.fetchHand(this.player).get(index);
    String north = card.fetchNumAtDirection(Direction.NORTH).toString();
    String south = card.fetchNumAtDirection(Direction.SOUTH).toString();
    String east = card.fetchNumAtDirection(Direction.EAST).toString();
    String west = card.fetchNumAtDirection(Direction.WEST).toString();

    Point2D modelPoint = new Point(0, index);
    Point2D logicalPoint = this.transformModelToLogical().transform(modelPoint, null);
    Point2D logicalSize = this.transformModelToLogical().transform(new Point(1, 1), null);

    GraphicCard graphicCard = new GraphicCard(
            (int) logicalSize.getX(),
            (int) logicalSize.getY(),
            (int) logicalPoint.getX(),
            (int) logicalPoint.getY(),
            north, south, east, west);

    if (highlight) {
      graphicCard.drawCard(g2d, playerColors.get(this.player).darker());
    }
    else {
      graphicCard.drawCard(g2d, playerColors.get(this.player));
    }
  }

  /**
   * Transformation that converts panel coordinates
   * (with (0,0) in upper-left and width x height in pixels)
   * into logical coordinates (with (0,0) in the top-left
   * and width x height of our logical size).
   * This is the inverse of transformLogicalToPhysical().
   * @return the logical to physical transformation
   */
  private AffineTransform transformPhysicalToLogical() {
    AffineTransform transformation = new AffineTransform();
    Dimension logical = this.getLogicalSize();
    transformation.scale(logical.getWidth() / this.getWidth(),
            logical.getHeight() / this.getHeight());
    return transformation;
  }

  /**
   * Transformation that converts logical coordinates
   * (with (0,0) in the top-left and width x height of our logical size)
   * into panel coordinates (with (0,0) in upper-left and width x height in pixels).
   * This is the inverse of transformPhysicalToLogical().
   * @return the physical to logical transformation
   */
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform transformation = new AffineTransform();
    Dimension logical = this.getLogicalSize();
    transformation.scale(this.getWidth() / logical.getWidth(),
            this.getHeight() / logical.getHeight());
    return transformation;
  }

  /**
   * Transformation that converts model coordinates (with (0,0) in upper-left,
   * width and height in board rows and columns) into logical coordinates
   * (with (0,0) in the top-left and width x height of our logical size).
   * This is the inverse of transformLogicalToModel().
   * @return the model to logical transformation
   */
  private AffineTransform transformModelToLogical() {
    AffineTransform transformation = new AffineTransform();
    Dimension logical = this.getLogicalSize();
    Dimension model = this.getModelSize();
    transformation.scale(logical.getWidth() / model.getWidth(),
            logical.getHeight() / model.getHeight());
    return transformation;
  }

  /**
   * Transformation that converts logical coordinates
   * (with (0,0) in the top-left and width x height of our logical size)
   * into model coordinates (with (0,0) in upper-left,
   * width and height in board rows and columns).
   * This is the inverse of transformModelToLogical().
   * @return the logical to model transformation
   */
  private AffineTransform transformLogicalToModel() {
    AffineTransform transformation = new AffineTransform();
    Dimension logical = this.getLogicalSize();
    Dimension model = this.getModelSize();
    transformation.scale(model.getWidth() / logical.getWidth(),
            model.getHeight() / logical.getHeight());
    return transformation;
  }

  /**
   * Repaints this component with the selected card highlighted.
   * @param cardIdx the card to highlight
   */
  public void selectedCard(int cardIdx) {
    this.handIdx = cardIdx;
    repaint();
  }

  /**
   * A listener class for mouse events. Selects a card if clicked on.
   */
  private class MouseEventsListener extends MouseInputAdapter {
    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * This sets the hand panel's selected card index
     * if and only if it is currently the turn of this hand panel's player.
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      Point physicalPoint = e.getPoint();
      Point2D logicalPoint = transformPhysicalToLogical().transform(physicalPoint, null);
      Point2D modelPoint = transformLogicalToModel().transform(logicalPoint, null);

      HandPanel.this.handIdx = (int) Math.floor(modelPoint.getY());
      for (ViewFeatures listener : HandPanel.this.featureListeners) {
        listener.selectedCard(HandPanel.this.handIdx, HandPanel.this.player);
      }
    }
  }
}
