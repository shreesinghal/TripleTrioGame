import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TripleTrioTextView;
import cs3500.tripletrios.view.TripleTrioView;

public final class ThreeTrios {
  public static void main(String[] args) {
    TripleTrioModel model = new TripleTrioGameModel();
    TripleTrioView view = new TripleTrioTextView(model); //add the GUI based view
    view.setVisible(true);
  }
}