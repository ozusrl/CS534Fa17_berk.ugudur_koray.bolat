package Model;

public class Cell {
    private Symbol symbol;

    public Cell(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "symbol=" + symbol +
                '}';
    }
}
