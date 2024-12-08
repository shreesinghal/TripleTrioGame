package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Posn;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Decorator that adds flip count hints to the grid panel
 */
public class GridPanelHintDecorator implements HintDecorator {
  private final GridPanel gridPanel;
  private final ReadOnlyTripleTrioModel model;
  private boolean enabled;
  private Card selectedCard;

  public GridPanelHintDecorator(GridPanel gridPanel, ReadOnlyTripleTrioModel model) {
    this.gridPanel = gridPanel;
    this.model = model;
    this.enabled = false;
  }

  @Override
  public void drawHints(Graphics2D g2d) {
    if (!enabled || selectedCard == null) {
      return;
    }

    int cellWidth = gridPanel.getWidth() / model.getGridWidth();
    int cellHeight = gridPanel.getHeight() / model.getGridHeight();

    // For each cell in grid
    for (int y = 0; y < model.getGridHeight(); y++) {
      for (int x = 0; x < model.getGridWidth(); x++) {
        Cell cell = model.getCurrentGrid().get(y).get(x);
        if (cell.getCellType() == Cell.CellType.CARDCELL && cell.isEmpty()) {
          // Calculate potential flips
          int flips = calculatePotentialFlips(x, y);
          drawHintNumber(g2d, x, y, flips, cellWidth, cellHeight);
        }
      }
    }
  }

  private int calculatePotentialFlips(int x, int y) {
    if (selectedCard == null) {
      return 0;
    }

    List<Posn> potentialFlips = new ArrayList<>();

    // Check each adjacent position using the same battle logic as the model
    if (x > 0 && canFlip(x-1, y) &&
            selectedCard.getWest() > model.getCurrentGrid().get(y).get(x-1).getCard().getEast()) {
      potentialFlips.add(new Posn(x-1, y));
    }

    if (x < model.getGridWidth()-1 && canFlip(x+1, y) &&
            selectedCard.getEast() > model.getCurrentGrid().get(y).get(x+1).getCard().getWest()) {
      potentialFlips.add(new Posn(x+1, y));
    }

    if (y > 0 && canFlip(x, y-1) &&
            selectedCard.getNorth() > model.getCurrentGrid().get(y-1).get(x).getCard().getSouth()) {
      potentialFlips.add(new Posn(x, y-1));
    }

    if (y < model.getGridHeight()-1 && canFlip(x, y+1) &&
            selectedCard.getSouth() > model.getCurrentGrid().get(y+1).get(x).getCard().getNorth()) {
      potentialFlips.add(new Posn(x, y+1));
    }

    // Count chain reactions
    int totalFlips = countChainReactions(potentialFlips, x, y);
    return totalFlips;
  }

  private boolean canFlip(int x, int y) {
    if (!isValidPosition(x, y)) {
      return false;
    }
    Cell cell = model.getCurrentGrid().get(y).get(x);
    return !cell.isEmpty() &&
            cell.getCard().getColor() != model.getPlayer().getColor();
  }

  private boolean isValidPosition(int x, int y) {
    return x >= 0 && x < model.getGridWidth() &&
            y >= 0 && y < model.getGridHeight() &&
            model.getCurrentGrid().get(y).get(x).getCellType() == Cell.CellType.CARDCELL;
  }

  private void drawHintNumber(Graphics2D g2d, int x, int y, int flips,
                              int cellWidth, int cellHeight) {
    if (flips >= 0) {
      g2d.setColor(new Color(0, 0, 0, 128));
      g2d.setFont(new Font("Arial", Font.BOLD, 20));
      String hint = String.valueOf(flips);
      int textX = x * cellWidth + cellWidth/2 - 10;
      int textY = y * cellHeight + cellHeight/2 + 10;
      g2d.drawString(hint, textX, textY);
    }
  }

  private int countChainReactions(List<Posn> initialFlips, int originalX, int originalY) {
    Set<Posn> allFlips = new HashSet<>(initialFlips);
    List<Posn> toProcess = new ArrayList<>(initialFlips);

    while (!toProcess.isEmpty()) {
      Posn current = toProcess.remove(0);
      List<Posn> chainFlips = getChainFlips(current.getX(), current.getY(), originalX, originalY);
      for (Posn flip : chainFlips) {
        if (allFlips.add(flip)) {
          toProcess.add(flip);
        }
      }
    }

    return allFlips.size();
  }

  private List<Posn> getChainFlips(int x, int y, int originalX, int originalY) {
    List<Posn> chainFlips = new ArrayList<>();
    Card flippedCard = model.getCurrentGrid().get(y).get(x).getCard();
    flippedCard.setCardColor(model.getPlayer().getColor());  // Temporarily flip for calculation

    flippedCard.setCardColor(model.getOppPlayer().getColor());  // Restore original color
    return chainFlips;
  }

  public void setSelectedCard(Card card) {
    this.selectedCard = card;
  }

  @Override
  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}