package cs3500.tripletrios.controller;

import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.strategies.CornerStrategy;
import cs3500.tripletrios.strategies.PlayerMove;
import cs3500.tripletrios.strategies.TripleTrioStrategy;
import cs3500.tripletrios.view.TTFrame;


public class TripleTrioAIPlayerContr extends TripleTrioHumanPlayerContr {
  
  private TripleTrioStrategy strategy;

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   *
   * @param view a GUI view.
   */  
  public TripleTrioAIPlayerContr(TTFrame view, TripleTrioStrategy strategy) {
    super(view);
    if (strategy == null) {
      throw new IllegalArgumentException("strategy cannot be null");
    }
    this.strategy = strategy;
  }

  @Override
  protected void onTurnNotification() {
    if (!model.getPlayer().isHuman()) {
      System.out.println("AI is calculating its move...");
      PlayerMove move = strategy.moveCard();
      playMove(move.getX(), move.getY());
    }
  }


  @Override
  public void playGame(TripleTrioModel model) {
    super.playGame(model);
    view.makeVisible();
  }

//  /**
//   * Play a game of Triple Trios given a model with initial conditions.
//   * @param model a triple trio model
//   */
//  public void playGameWithModel(TripleTrioModel model) {
//    super.playGame(model);
//    view.makeVisible();
//  }
}
