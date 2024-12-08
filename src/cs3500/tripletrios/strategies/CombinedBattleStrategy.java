package cs3500.tripletrios.strategies;

public class CombinedBattleStrategy implements BattleStrategy {
  private final boolean reverseRule;
  private final boolean fallenAceRule;

  public CombinedBattleStrategy(boolean reverseRule, boolean fallenAceRule) {
    this.reverseRule = reverseRule;
    this.fallenAceRule = fallenAceRule;
  }

  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    // Handle Fallen Ace rule
    if (fallenAceRule && attackerValue == 1 && defenderValue == 10) {
      return !reverseRule; // In reverse mode, 10 beats 1 instead
    }

    if (reverseRule) {
      // Special case when both rules are active: 10 beats 1
      if (fallenAceRule && attackerValue == 10 && defenderValue == 1) {
        return true;
      }
      return attackerValue < defenderValue;
    }

    // Standard rule
    return attackerValue > defenderValue;
  }
}