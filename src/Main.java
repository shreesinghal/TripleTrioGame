import cs3500.tripletrios.controller.TripleTrioController;
import cs3500.tripletrios.controller.TripleTrioControllerImpl;
import cs3500.tripletrios.model.TripleTrioGameModel;
import cs3500.tripletrios.model.TripleTrioModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {

        TripleTrioModel model = new TripleTrioGameModel();
        BufferedReader readable = new BufferedReader(new InputStreamReader(System.in));
        TripleTrioController controller = new TripleTrioControllerImpl(readable, System.out);
        try {
            controller.playGame(model, "Configurations"
                    + File.separator
                    + "20deckConfig.txt",
                "Configurations"
                    + File.separator
                    + "3x3sqrGrid.txt");
        } catch (IOException e) {
            throw new IOException();
        }
    }
}