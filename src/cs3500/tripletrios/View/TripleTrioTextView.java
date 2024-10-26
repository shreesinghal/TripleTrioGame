package cs3500.tripletrios.View;

import cs3500.tripletrios.Model.TripleTrioModel;

public class TripleTrioTextView implements TripleTrioView {
  private TripleTrioModel model;
  private Appendable output;

  public TripleTrioTextView(TripleTrioModel model, Appendable output) {
    if (model == null || output == null) {
      throw new IllegalArgumentException("model or output cannot be null");
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



      return output.toString();
    }
}
