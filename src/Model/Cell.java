package Model;

public class Cell {
    private Symbol symbol;
    private int index;

    public Cell(Symbol symbol) {
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
