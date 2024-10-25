package cs3500.tripletrios.Controller;

import cs3500.tripletrios.Model.Cell;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GridConfigReader {

  public ArrayList<ArrayList<Cell>> readGridConfiguration(String filePath) {
    Scanner scanner;
    try {
      scanner = new Scanner(new FileReader(filePath));
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
    int rows = scanner.nextInt();
    int cols = scanner.nextInt();
    scanner.nextLine(); // Move to the next line

    ArrayList<ArrayList<Cell>> grid = new ArrayList<ArrayList<Cell>>();
    for (int i = 0; i < rows; i++) {
      ArrayList<Cell> currRow = new ArrayList<>();

      String line = scanner.nextLine();
      if (line.length() != cols) {
        throw new IllegalArgumentException("Grid row length does not match the specified number of columns.");
      }

      for (char value : line.toCharArray()) {
        if (value == 'C' || value == 'c') {
          currRow.add(new Cell(Cell.CellType.CARDCELL));
        } else {
          if (value == 'X' || value == 'x') {
            currRow.add(new Cell(Cell.CellType.HOLE));
          } else {
            throw new IllegalArgumentException("Illegal character in grid configuration.");
          }
        }
      }
      grid.add(currRow);
    }
    return grid;
  }

}
