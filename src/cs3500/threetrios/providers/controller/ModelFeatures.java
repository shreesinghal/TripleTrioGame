package cs3500.threetrios.providers.controller;

import cs3500.threetrios.providers.model.PlayerType;

/**
 * Features for Model to interact with
 * controller. A model must be able to tell
 * players when it is their turn and assign players to roles.
 * Allows for asynchronous control.
 */
public interface ModelFeatures {

  /**
   * Notify controller that it is their
   * turn to make a move in the model.
   */
  void notifyTurn();

  /**
   * Notify controller of the game starting.
   */
  void notifyStartGame();

  /**
   * Notify controller of the game ending with
   * the winner.
   */
  void notifyEndGame(PlayerType winner);

  /**
   * Assign a model to the player.
   * @param playerType player to assign
   */
  void assignPlayerType(PlayerType playerType);
}
