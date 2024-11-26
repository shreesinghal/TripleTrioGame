package model;

/**
 * Enumerations for player types of a game of Triple Triad.
 * A NULL_PLAYER represents the lack of a playable cell.
 * PLAYER_A AND PLAYER_B are the only valid players.
 * An EMPTY_PLAYER represents no player in a playable cell.
 */
public enum PlayerType {
  NULL_PLAYER,
  TIE,
  EMPTY_PLAYER,
  PLAYER_A,
  PLAYER_B;
}
