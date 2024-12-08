package cs3500.threetrios.providers.controller.player;

import cs3500.threetrios.providers.controller.PlayerFeatures;
import cs3500.threetrios.providers.model.PlayerType;

/**
 Represents a player in the Triple Triad game, providing mechanisms for interaction
 with game features and turn management.
 This interface defines the core behaviors required for a player in the game,
 including the ability to register listeners and be notified of their turn.
 */
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
