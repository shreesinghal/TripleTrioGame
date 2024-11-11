package cs3500.tripletrios.view;

import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class HandPanel extends JPanel {

  private TripleTrioController features;

  private ReadOnlyTripleTrioModel model;
  private final Player player;
  private Graphics2D g2d;
  private ArrayList<CardView> cardViewsInHand;
  private int highlightedCard = -1;


  public HandPanel(ReadOnlyTripleTrioModel model, Player player) {
    this.player = player;
    this.model = model;
    this.cardViewsInHand = new ArrayList<>();
  }

  public void addClickListener(TripleTrioController features) {
    this.features = features;
    this.addMouseListener(new TTHandClick());
  }


  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    //clears the panel, makes sure any old drawings on it are removed

    g2d = (Graphics2D) g;


    for (int i = 0; i < this.player.getHand().size(); i++) {
      //we are looping through the players hand to draw each card in hand as a cardView object

      CardView cardView = new CardView(this.player.getHand().get(i),
        i,
        this.getHeight() / this.model.getPlayer().getHand().size());
      // ^^ make the card height dependent on the panel height (this.height) ^^

      if (player.getColor().getColor() == Color.RED) {
        g2d.setColor(new Color(255,171,173,255));
      } else {
        g2d.setColor(new Color(72, 172, 255, 255));
      }
      g2d.fill(cardView);

      g2d.setColor(Color.BLACK);
      if (i == highlightedCard - 1) {
        g2d.setStroke(new BasicStroke(10));
      }
      cardView.draw(g2d,0,i * CardView.cardHeight);
      g2d.draw(cardView);

      g2d.setStroke(new BasicStroke(1));
      cardViewsInHand.add(cardView);
    }


  }

  /**
   * Gets the dimensions needed to make a hand on GUI view.
   * @return dimensions of the hand
   */
  public Dimension getPixelDimensions() {
    return new Dimension(CardView.cardWidth, CardView.cardHeight * this.model.getPlayer().getHand().size());
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
      System.out.println("You clicked at " + 1 + " " + pixelToCell(e.getY()) + " in the "
        + player.getColor() + " hand.");
      features.handleCellClickForHand(e, player.getColor());
      if (player.getColor() == model.getPlayer().getColor()) {
        highlightHandCard(pixelToCell(e.getY()));
      }
    }

    private void highlightHandCard(int cardNumber) {
      if (highlightedCard == cardNumber) {
        highlightedCard = -1;
      } else {
        highlightedCard = cardNumber;
      }
      repaint();
    }

    private int pixelToCell(int Coord) {
      return Coord / CardView.getLogicalSizeOfCard().height + 1;
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
