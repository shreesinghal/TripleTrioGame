package cs3500.tripletrios.strategies;

/**
 * Strategy for the Same rule. Checks for matching values.
 */
public class SameBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    // For Same rule, we'll handle the actual logic in the model
    // This is just for normal captures
    return attackerValue > defenderValue;
  }
}
