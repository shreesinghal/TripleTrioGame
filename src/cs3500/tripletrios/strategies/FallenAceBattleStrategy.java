package cs3500.tripletrios.strategies;


public class FallenAceBattleStrategy implements BattleStrategy {
  @Override
  public boolean canCapture(int attackerValue, int defenderValue) {
    return (attackerValue == 1 && defenderValue == 10) || attackerValue > defenderValue;
  }
}

