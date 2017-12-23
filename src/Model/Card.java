package Model;

public class Card {
    private Symbol symbol;

    public Card(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "This card is "+getSymbol();
    }
}
