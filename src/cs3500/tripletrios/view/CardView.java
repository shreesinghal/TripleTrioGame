package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class CardView extends Path2D.Double {

  static int cardWidth = 20;
  static int cardHeight = 20;

  public CardView(Card card) {


  }

  static public Dimension getLogicalSizeOfCard() {
    return new Dimension(cardWidth, cardHeight);
  }

}
