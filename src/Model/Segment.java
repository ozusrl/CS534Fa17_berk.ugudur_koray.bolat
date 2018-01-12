package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* Refactored 12.01.2018 14:52 */

public class Segment {
    private int index;
    private int numOfCells;
    private ArrayList<Cell> cells;

    public Segment(int index, int numOfCells) {
        this.index = index;
        this.numOfCells = numOfCells;
        this.cells = new ArrayList<>(numOfCells);
        createCells();
    }

    private void createCells() {
        ArrayList<Symbol> symbols = getRandomSymbols();
        for (int i = 0; i < numOfCells; i++) {
            int index = (this.index * numOfCells) + i;
            cells.add(new Cell(index, symbols.get(i)));
        }
    }

    private ArrayList<Symbol> getRandomSymbols() {
        ArrayList<Symbol> symbols = new ArrayList<>(Arrays.asList(Symbol.values()));
        Collections.shuffle(symbols);
        return symbols;
    }

    public int getNumOfCells() {
        return numOfCells;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "numOfCells=" + numOfCells +
                ", cells=" + cells +
                "}\n";
    }

}
