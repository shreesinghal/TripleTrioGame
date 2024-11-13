package cs3500.tripletrios.strategies;

import cs3500.tripletrios.model.Card;
import cs3500.tripletrios.model.Cell;
import cs3500.tripletrios.model.Player;
import cs3500.tripletrios.model.TripleTrioGameModel;

import java.util.ArrayList;
import java.util.Set;


public class MockModel extends TripleTrioGameModel {

  private ArrayList<ArrayList<Cell>> grid;
  private ArrayList<ArrayList<Cell>> originalGrid;

  private Set<Card> deck;
  private Player currPlayer;
  private Player opposingPlayer;
  private boolean gameStarted;
  private ArrayList<Card> deckList;




}
