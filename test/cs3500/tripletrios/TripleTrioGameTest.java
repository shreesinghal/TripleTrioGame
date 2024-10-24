package cs3500.tripletrios;

import cs3500.tripletrios.Controller.TripleTrioController;
import cs3500.tripletrios.Controller.TripleTrioControllerImpl;
import cs3500.tripletrios.Model.TripleTrioGameModel;
import cs3500.tripletrios.Model.TripleTrioModel;
import cs3500.tripletrios.View.TripleTrioView;
import cs3500.tripletrios.View.TripleTrioViewImpl;
import org.junit.Before;

/**
 * This class tests the implementation of the Triple Trio game.
 */
public class TripleTrioGameTest {

    @Before
    public void setUp() throws Exception {
        TripleTrioModel model = new TripleTrioGameModel();
        TripleTrioController controller = new TripleTrioControllerImpl();
        TripleTrioView view = new TripleTrioViewImpl();


    }
}
