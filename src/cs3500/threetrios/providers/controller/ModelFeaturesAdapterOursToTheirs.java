package cs3500.threetrios.providers.controller;

import cs3500.threetrios.providers.model.PlayerType;

public class ModelFeaturesAdapterOursToTheirs implements ModelFeatures{
  /**
   * Notify controller that it is their
   * turn to make a move in the model.
   */
  @Override
  public void notifyTurn() {

  }

  /**
   * Notify controller of the game starting.
   */
  @Override
  public void notifyStartGame() {

  }

  /**
   * Notify controller of the game ending with
   * the winner.
   *
   * @param winner
   */
  @Override
  public void notifyEndGame(PlayerType winner) {

  }

  /**
   * Assign a model to the player.
   *
   * @param playerType player to assign
   */
  @Override
  public void assignPlayerType(PlayerType playerType) {

  }
}
