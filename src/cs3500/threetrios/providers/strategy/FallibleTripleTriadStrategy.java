package cs3500.threetrios.providers.strategy;

import java.util.Optional;
import cs3500.threetrios.providers.model.PlayerType;
import cs3500.threetrios.providers.model.ReadOnlyTripleTriad;

/**
 * A fallible strategy returns a move if one is possible,
 * or it may not find one.
 */
public interface FallibleTripleTriadStrategy {
  /**
   * Based on implemented strategy, plays a move
   * to the model. Tries to find a move if possible,
   * otherwise returns an empty Optional.
   * @throws IllegalArgumentException if model or player is null
   */
  <Move> Optional<Move> move(ReadOnlyTripleTriad model, PlayerType player);
}
