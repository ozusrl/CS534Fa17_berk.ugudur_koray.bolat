package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Segment {
    private int numOfCells;
    private ArrayList<Cell> cells;

    public Segment(int numOfCells) {
        this.numOfCells = numOfCells;
        this.cells = new ArrayList<>(numOfCells);
        createCells();
        shuffleCells();
    }

    private void createCells() {
        Symbol[] symbols = Symbol.values();
        for(int i = 0; i < numOfCells; i++)
            cells.add(new Cell(symbols[i]));
    }

    private void shuffleCells() {
        Collections.shuffle(cells);
    }

    public int getNumOfCells() {
        return numOfCells;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    @Override
    public String toString() {
        return "Segment{" +
                "numOfCells=" + numOfCells +
                ", cells=" + cells +
                "}\n";
    }
}
