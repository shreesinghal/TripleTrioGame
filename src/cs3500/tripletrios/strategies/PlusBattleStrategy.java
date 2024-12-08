package cs3500.tripletrios.strategies;


/**
 * Strategy for the Plus rule. Checks for matching sums.
 */
public class PlusBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    // For Plus rule, we'll handle the actual logic in the model
    // This is just for normal captures
    return attackerValue > defenderValue;
  }
}