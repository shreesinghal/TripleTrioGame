package controller.player;

import controller.PlayerFeatures;
import model.PlayerType;

public interface Player {
  /**
   * Adds a PlayerFeature as a listener to this object. Allows
   * for asynchronous communication,
   * @param listener PlayerFeature
   */
  void addListeners(PlayerFeatures listener);

  /**
   * Communicate asynchronously with model.
   * @param playerType player this player represents.
   */
  void notifyTurn(PlayerType playerType);

}
