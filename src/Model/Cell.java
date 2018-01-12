package Model;

/* Refactored 12.01.2018 14:54 */

public class Cell {
    private Symbol symbol;
    private int index;

    public Cell(int index, Symbol symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "symbol=" + symbol +
                ", index=" + index +
                '}';
    }
}
