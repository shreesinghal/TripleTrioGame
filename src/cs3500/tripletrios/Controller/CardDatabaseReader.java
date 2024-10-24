package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Card;

import java.io.File;
import java.util.ArrayList;

public class CardDatabaseReader implements ConfigReader {
    File cardDatabaseFile;

    public CardDatabaseReader(String path) {
        cardDatabaseFile = new File(path);
    }

    public Map<Card> generateDeckOfCardsList() {

    }
}
