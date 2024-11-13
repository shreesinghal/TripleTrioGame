package cs3500.tripletrios.view;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.CardColor;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.model.WinningState;

import java.io.IOException;
import java.util.ArrayList;


/**
 * View that presents to the user the state of the game.
 */
public class TripleTrioTextView implements TripleTrioView {
  private TripleTrioModel model;
  private Appendable output;

  /**
   * Instantiates a view by taking a model and an output.
   * @param model TripleTrio game model
   * @param output output for the viewer
   */
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
    displayBoard(output);

    output.append("Hand: ").append("\n");
    for (Card card : model.getPlayer().getHand()) {
      output.append(card.getName())
          .append(" ")
          .append(card.getNorth() == 10 ? "A" : card.getNorth())
          .append(" ")
          .append(card.getSouth() == 10 ? "A" : card.getSouth())
          .append(" ")
          .append(card.getEast() == 10 ? "A" : card.getEast())
          .append(" ")
          .append(card.getWest() == 10 ? "A" : card.getWest())
          .append("\n");
    }

    output.append("\n" + "Player ")
      .append(this.model.getPlayer().getColor().toString())
      .append(", enter your next move in the format [x-position] [y-position] [card name]: \n");

    return output.toString();
  }

  private void displayBoard(StringBuilder output) {
    for (ArrayList<Cell> row : model.getCurrentGrid()) {
      for (Cell cell : row) {
        if (cell.getCellType() == Cell.CellType.HOLE) {
          output.append(" ");
        } else {
          if (cell.getCard() == null) {
            output.append("_");
          } else if (cell.getCard().getColor() == CardColor.RED) {
            output.append("R");
          } else if (cell.getCard().getColor() == CardColor.BLUE) {
            output.append("B");
          } else {
            throw new IllegalArgumentException("Invalid card color: " + cell.getCard().getColor());
          }
        }
      }
      output.append("\n");
    }
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
  public void displayFinalMessage(WinningState winner) throws IOException {

    switch (winner) {
      case RedWins:
        this.output.append("\nPlayer Red wins!");
        break;

      case BlueWins:
        this.output.append("\nPlayer Blue wins!");
        break;

      case Tie:
        this.output.append("\nThere has been a tie!");
        break;

      default:
        throw new IOException("Winner can not be determined");
    }

    displayBoard((StringBuilder) output);

  }
}