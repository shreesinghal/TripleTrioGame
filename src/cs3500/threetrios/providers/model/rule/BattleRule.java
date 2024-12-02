package cs3500.threetrios.providers.model.rule;

import cs3500.threetrios.providers.model.Direction;
import cs3500.threetrios.providers.model.cell.Cell;

/**
 * Represents a Battle Rule. The way a game updates cells'
 * ownership and determines winning cards in a battle
 * depends on the implementation of concrete classes.
 */
public interface BattleRule {

  /**
   * Updates which cell is owned by which player after a move is enacted.

   * @param row Coordinate of current cell row
   * @param col Coordinate of current cell col
   * @throws IllegalArgumentException if the coordinates given are not on the board
   */
  void updateCellOwnership(int row, int col);

  /**
   * Enacts battles between cells.
   *
   * @param attacker Attacking Cell.
   * @param defender Defending Cell.
   * @param direction Direction of battle from attacker to defender.
   * @return True if Attacker loses.
   * @throws IllegalArgumentException if attacker cell, defender cell, or direction is null
   */
  boolean battle(Cell attacker, Cell defender, Direction direction);
}
