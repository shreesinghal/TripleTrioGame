package cs3500.tripletrios.strategies;

public class CombinedBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    if (attackerValue == 10 && defenderValue == 1) {
      return true; // Reverse of Fallen Ace
    }
    return attackerValue < defenderValue; // Reverse rule
  }
}
