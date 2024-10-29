package cs3500.tripletrios.View;

import cs3500.tripletrios.Model.*;

import java.io.IOException;
import java.util.ArrayList;



public class TripleTrioTextView implements TripleTrioView {
  private TripleTrioModel model;
  private Appendable output;

  public TripleTrioTextView(TripleTrioModel model, Appendable output) {
    if (model == null) {
      throw new IllegalArgumentException("model cannot be null");
    }

    if (output == null) {
      throw new IllegalArgumentException("output cannot be null");
    }

    this.model = model;
    this.output = output;
  }



  /**
   * Display the current State of the game.
   */
  @Override
  public String toString() {
    StringBuilder output = new StringBuilder();
    output.append("Player: ").append(model.getPlayer().getColor()).append("\n");
    for (ArrayList<Cell> row : model.getGrid()) {
      for (Cell cell : row) {
        if (cell.getCellType() == Cell.CellType.HOLE) {
          output.append(" ");
        } else {
          if (cell.getCard() == null) {
            output.append("_");
          } else if (cell.getCard().getColor() == Color.RED) {
            output.append("R");
          } else if (cell.getCard().getColor() == Color.BLUE) {
            output.append("B");
          } else {
            throw new IllegalArgumentException("Invalid card color");
          }
        }
      }
      output.append("\n");
    }

    output.append("Hand: ").append("\n");
    for (Card card : model.getDeck()) {
      output.append(card.getName())
          .append(" ")
          .append(card.getNorth())
          .append(" ")
          .append(card.getSouth())
          .append(" ")
          .append(card.getEast())
          .append(" ")
          .append(card.getWest())
          .append("\n");
    }

    return output.toString();
  }

  /**
   * Renders output to the screen.
   *
   * @throws IOException if output is improper.
   */
  @Override
  public void render() throws IOException {
    if (this.output != null) {
      this.output.append(this.toString());
    } else {
      throw new IOException("No proper object to render");
    }

  }

  /**
   * Displays the winner along with a final message.
   * @param winner the winner of the game
   */
  @Override
  public void dispalyFinalMessage(WinningState winner) throws IOException {

    switch (winner) {
      case WinningState.RedWins:
        this.output.append("\nPlayer Red wins!");
        break;

      case WinningState.BlueWins:
        this.output.append("\nPlayer Blue wins!");
        break;

      case WinningState.Tie:
        this.output.append("\nThere has been a tie!");
        break;

      default:
        throw new IOException("Winner can not be determined");
    }

  }
}