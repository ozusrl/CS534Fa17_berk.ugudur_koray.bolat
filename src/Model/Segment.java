package Model;

import View.Values;

import java.util.ArrayList;
import java.util.Collections;

public class Segment {
    private int numOfCells;
    private int index;
    private ArrayList<Cell> cells;

    public Segment(int index, int numOfCells) {
        this.numOfCells = numOfCells;
        this.cells = new ArrayList<>(numOfCells);
        this.index = index;
        createCells();
        shuffleCells();
        setupCells();
    }

    private void createCells() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < numOfCells; i++)
            cells.add(new Cell(symbols[i]));
    }

    private void shuffleCells() {
        Collections.shuffle(cells);
    }

    private void setupCells() {
        for (int i = 0; i < numOfCells; i++) {
            Cell cell = cells.get(i);
            cell.setIndex((index * numOfCells) + i);
        }
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
