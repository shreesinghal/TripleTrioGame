package cs3500.tripletrios.Model;

public class Cell {

    boolean hasCard;
    Card card;

    public static enum CellType {
        HOLE,
        CARDCELL;
    }

    CellType cellType;

    /**
     * Creates a new empty Cell on the grid.
     * @param type specified cell type
     */
    public Cell(CellType type) {
        hasCard = false;
        card = null;
        this.cellType = type;
    }

    /**
     * Returns if the cell has a card or not.
     * @return true if has card and vice versa
     */
    public boolean isEmpty() {
        return hasCard;
    }

    /**
     * Puts a card into the cell.
     * @param card card to put in cell
     */
    void placeCard(Card card) {
        this.card = card;
        hasCard = true;
    }


}