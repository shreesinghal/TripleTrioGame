package cs3500.tripletrios.controller;

import cs3500.tripletrios.configreaders.CardDatabaseReader;
import cs3500.tripletrios.configreaders.GridConfigReader;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.strategies.CornerStrategy;
import cs3500.tripletrios.strategies.TripleTrioStrategy;
import cs3500.tripletrios.view.TTFrame;

import java.util.ArrayList;
import java.util.Set;

public class TripleTrioAIPlayer extends TripleTrioHumanPlayerContr {
  
  private TripleTrioStrategy strategy;

  /**
   * Constructor that instantiates a controller that takes in a GUI view.
   *
   * @param view a GUI view.
   */  
  public TripleTrioAIPlayer(TTFrame view) {
    super(view);
    this.strategy = new CornerStrategy(this.model);
  }


  @Override
  public void playGame(TripleTrioModel model) {
    super.playGame(model);
    view.makeVisible();
  }

  /**
   * Play a game of Triple Trios given a model with initial conditions.
   * @param model a triple trio model
   */
  public void playGameWithModel(TripleTrioModel model) {
    super.playGame(model);
    view.makeVisible();
  }
}
