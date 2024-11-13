package cs3500.tripletrios.configReaders;

import cs3500.tripletrios.model.Cell;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class which reads the grid files to create a grid for the game.
 */

public final class GridConfigReader {

  /**
   * Method which reads a grid configuration file and returns a new grid
   * based on that for the game.
   * @param filePath grid configuration file
   * @return a new grid for the game
   * @throws IllegalArgumentException if the configurations has an illegal character.\
   */
  public static ArrayList<ArrayList<Cell>> readGridConfiguration(String filePath) {
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
        throw new IllegalArgumentException("Grid row length does not match the number of columns.");
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