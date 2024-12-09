package cs3500.tripletrios.strategies;
public interface BattleStrategy {
  /**
   * Determines if an attacking card can capture a defending card
   * @param attackerValue value of attacking card's side
   * @param defenderValue value of defending card's side
   * @return true if capture is successful
   */
  boolean canCapture(int attackerValue, int defenderValue);


}
