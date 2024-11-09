package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.ReadOnlyTripleTrioModel;

import javax.swing.JPanel;
import java.awt.*;

public class HandPanel extends JPanel {

  private ReadOnlyTripleTrioModel model;
  private final Player player;

  public HandPanel(ReadOnlyTripleTrioModel model, Player player) {
    this.player = player;
    this.model = model;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    //going to have to scale graphic2D based on the dimension width and height
    //.scale function --> get dimensions of the current size and scale the


    for (int i = 0; i < this.player.getHand().size(); i++) {
      CardView cardView = new CardView(this.player.getHand().get(i),
        i,
        this.getHeight() / this.model.getPlayer().getHand().size());
      // ^^ make the card height dependent on the panel height (this.height) ^^
      g2d.setColor(player.getColor().getColor());
      g2d.fill(cardView);
      g2d.setColor(Color.BLACK);
      cardView.draw(g2d,0,i * CardView.cardHeight);
      g2d.draw(cardView);

    }


  }

  /**
   * Gets the dimensions needed to make a hand on GUI view.
   * @return dimensions of the hand
   */
  public Dimension getDimensions() {
    return new Dimension(CardView.cardWidth, CardView.cardHeight * this.model.getPlayer().getHand().size());
  }

  //mouselistener class --> highlighting the border


}
