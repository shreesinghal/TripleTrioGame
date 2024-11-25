package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioHumanPlayerContr;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * A JPanel subclass representing the player's hand in the Triple Trios game.
 * This panel displays a visual representation of the cards in a player's hand
 * in the GUI view.
 */
public class HandPanel extends JPanel {

  private TripleTrioHumanPlayerContr features;
  private ReadOnlyTripleTrioModel model;
  private Player player;
  private ArrayList<CardView> cardViewsInHand;
  private int highlightedCardNum = -1;

  private CardView sampleCardView;


  /**
   * Creates a hand panel for a specified player by displaying
   * the players hand of cards.
   * @param model the representation of the game
   * @param player the player owning the hand
   */
  public HandPanel(ReadOnlyTripleTrioModel model, Player player) {
    this.player = player;
    this.model = model;
    this.cardViewsInHand = new ArrayList<>();
    this.sampleCardView = new CardView(model.getPlayer().getHand().get(0),
            1,
            1,
            150,
            this.getHeight() / model.getGridHeight());
  }

  /**
   * Set up the controller to handle click events in this view.
   * Only reacts to clicks.
   * @param features the controller
   */
  public void addClickListener(TripleTrioHumanPlayerContr features) {
    this.features = features;
    this.addMouseListener(new TTHandClick());
  }


  /**
   * Renders a hand panel on the screen according to dimensions from the model.
   * Panel is resizeable, adjusting to the height and width of the current frame size.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    int cardsToDisplay = this.player.getHand().size();

    if (cardsToDisplay > 0) {
      int cardHeight = this.getHeight() / cardsToDisplay;
      // display each card in the hand
      for (int i = 0; i < cardsToDisplay; i++) {
        CardView cardView = new CardView(this.player.getHand().get(i), 0, i, 150, cardHeight);

        // set the color
        if (player.getColor() == CardColor.RED) {
          g2d.setColor(new Color(255,171,173,255));
        } else {
          g2d.setColor(new Color(72, 172, 255, 255));
        }
        g2d.fill(cardView);

        g2d.setColor(Color.BLACK);

        // Highlight the selected card
        if (i == highlightedCardNum) {
          g2d.setStroke(new BasicStroke(10));
        }

        // Draw the card view
        cardView.draw(g2d, 0, i * cardHeight);
        g2d.draw(cardView);

        g2d.setStroke(new BasicStroke(1));
        cardViewsInHand.add(cardView);
      }
    }
  }


  /**
   * Gets the dimensions needed to make a hand on GUI view.
   * @return dimensions of the hand
   */
  public Dimension getPixelDimensions() {
    return new Dimension(sampleCardView.getCardWidth(),
            this.getHeight() / model.getPlayer().getHand().size());
  }

  /**
   * Unhighlights the highlighted card.
   */
  public void unHighlight() {
    highlightedCardNum = -1;
  }

  /**
   * Highlights the specified card.
   * @param cardNum cardnumber to move
   */
  public void highlightHandCard(int cardNum) {
    if (highlightedCardNum == cardNum) {
      highlightedCardNum = -1;
    } else {
      highlightedCardNum = cardNum;
    }
    System.out.println("Highlighted card index: " + highlightedCardNum);
    repaint();
  }

  public void removeCard(Card selectedCard) {
    unHighlight();
    CardView cardViewToRemove = new CardView(selectedCard, 0, 0, 0,0);
    for (CardView cardView : cardViewsInHand) {
      if (selectedCard.equals(cardView.getCard())) {
        cardViewToRemove = cardView;
      }
    }
    cardViewsInHand.remove(cardViewToRemove);
    this.repaint();
  }


  /**
   * Private class to handle clicks to hand.
   */
  private class TTHandClick implements MouseListener {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      features.handleCellClickForHand(pixelToCell(e.getY()), player.getColor());
    }



    private int pixelToCell(int coordValue) {
      int cardsToDisplay = player.getHand().size();
      int cardHeight = getHeight() / cardsToDisplay;
      return coordValue / cardHeight;
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
      //no implementation
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
      //no implementation
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
      //no implementation
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
      //no implementation
    }
  }


}
