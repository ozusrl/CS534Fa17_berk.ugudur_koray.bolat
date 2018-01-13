package Model;

import java.io.Serializable;

public class Card implements Serializable{
    private Symbol symbol;
    private int index;
    public Card(int index, Symbol symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "This card is "+getSymbol();
    }
}
