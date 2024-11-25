package cs3500.tripletrios.model;

/**
 * Enum to represent the three possible
 * winning states of the game.
 */
public enum WinningState {

  RedWins,
  BlueWins,
  GameNotDone,
  Tie;


  /**
   * Returns the string version of the state.
   * @return string explaining the winning state.
   */
  public String toString() {
    switch (this) {
      case RedWins:
        return "Red is winner!";
      case BlueWins:
        return "Blue is winner!";
      case GameNotDone:
        return "Game in progress";
      case Tie:
        return "Tie!";
      default:
        return "Unspecified state";
    }
  }

}
