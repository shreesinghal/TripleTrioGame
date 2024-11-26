package strategy;

import java.util.Optional;
import model.PlayerType;
import model.ReadOnlyTripleTriad;

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
  Optional<Move> move(ReadOnlyTripleTriad model, PlayerType player);
}
