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
    }
    return "Unspecified state";
  }

}
