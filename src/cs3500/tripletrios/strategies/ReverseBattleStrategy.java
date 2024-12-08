package cs3500.tripletrios.strategies;

public class ReverseBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    return attackerValue < defenderValue;
  }
}
