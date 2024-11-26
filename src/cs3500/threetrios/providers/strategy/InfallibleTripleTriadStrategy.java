package strategy;

import model.PlayerType;
import model.ReadOnlyTripleTriad;

/**
 * An infallible strategy must return a move.
 */
public interface InfallibleTripleTriadStrategy {
  /**
   * Based on implemented strategy, plays a move
   * to the model.
   * @throws IllegalArgumentException if model or player is null
   */
  Move move(ReadOnlyTripleTriad model, PlayerType player);
}
