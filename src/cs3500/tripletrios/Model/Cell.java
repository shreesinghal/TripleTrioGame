package cs3500.tripletrios.Model;

public final class Cell {

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

    public CellType getCellType() {
        return cellType;
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
    public void placeCard(Card card) {
        this.card = card;
        hasCard = true;
    }

    public Card getCard() {
        return this.card;
    }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Cell
            && this.cellType == ((Cell) obj).cellType
            && this.hasCard == ((Cell) obj).hasCard
            && this.card == ((Cell) obj).card;
    }
}