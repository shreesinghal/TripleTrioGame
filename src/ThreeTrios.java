import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;
import cs3500.tripletrios.view.TTFrame;
import cs3500.tripletrios.view.TTFrameImpl;
import cs3500.tripletrios.view.TripleTrioTextView;
import cs3500.tripletrios.view.TripleTrioView;

public final class ThreeTrios {
  public static void main(String[] args) {
    TripleTrioModel model = new TripleTrioGameModel();
    TTFrame view = new TTFrameImpl(model); //add the GUI based view
    view.makeVisible();
  }
}