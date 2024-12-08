package cs3500.tripletrios.strategies;


/**
 * Factory for creating battle strategies based on game rules.
 */
public class BattleStrategyFactory {
  public static BattleStrategy createStrategy(boolean sameRule, boolean plusRule) {
    if (sameRule && plusRule) {
      throw new IllegalArgumentException("Cannot use Same and Plus rules simultaneously");
    }
    if (sameRule) {
      return new SameBattleStrategy();
    }
    if (plusRule) {
      return new PlusBattleStrategy();
    }
    return new DefaultBattleStrategy();
  }
}

