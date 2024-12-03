package cs3500.threetrios.providers.model;

import cs3500.threetrios.providers.controller.ModelFeatures;
import cs3500.tripletrios.controller.TripleTrioModelListener;
import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;

public class ListenerModelAdapterTheirsToOurs implements TripleTrioModelListener {
  private final ModelFeatures theirListener;
  private final TripleTrioModel ourModel;

  public ListenerModelAdapterTheirsToOurs(ModelFeatures listener, TripleTrioModel ourModel) {
    theirListener = listener;
    this.ourModel = ourModel;
  }


  /**
   * Notifies that it's a player's turn.
   * Notifies which player is allowed to act next.
   *
   * @param color the name of the current player
   */
  @Override
  public void onPlayerTurn(CardColor color) {
    theirListener.notifyTurn();
  }

  /**
   * Notifies that a card was placed.
   *
   * @param x    the x-coordinate of the placed card
   * @param y    the y-coordinate of the placed card
   * @param card card being placed
   */
  @Override
  public void onCardPlaced(int x, int y, Card card) {
    // there is no equivalent method
    // This is because our implementation breaks a turn into two parts
    // (1) choosing a card and (2) placing it down
    // their implementation combines the two in a single step,
    // so there is no equivalent method because their implementaiton
    // doesn't break their step into two publicly.
  }

  /**
   * Notifies that the game state has updated.
   */
  @Override
  public void onGameStateUpdated() {
    WinningState winner = ourModel.determineWinner();

    switch (winner) {
      case RedWins: theirListener.notifyEndGame(PlayerType.PLAYER_A);
      case BlueWins: theirListener.notifyEndGame(PlayerType.PLAYER_B);
      case Tie: theirListener.notifyEndGame(PlayerType.TIE);
      default: theirListener.notifyStartGame();
    }
  }
}
