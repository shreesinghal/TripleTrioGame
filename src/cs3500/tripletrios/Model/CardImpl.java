package cs3500.tripletrios.Model;

import java.util.HashMap;
import java.util.Map;

public class CardImpl implements Card {

  String name;

  Map<Direction, Integer> attackValue = new HashMap<>();

  Map<CardImpl , Map<Direction,Integer>> card = new HashMap<>();




}
