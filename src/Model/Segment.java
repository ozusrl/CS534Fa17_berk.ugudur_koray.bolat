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
            int tempCellIndex = cell.getIndex() + (numOfCells / 2);
            cell.setX(calculateXPositionOfCell(tempCellIndex));
            cell.setY(calculateYPositionOfCell(tempCellIndex));
        }
    }

    private int calculateXPositionOfCell(int tempCellIndex){
        //TODO: Magic number
        int x = (tempCellIndex / numOfCells) * Values.CELL_SIZE * 2;
        return x;
    }

    private int calculateYPositionOfCell(int tempCellIndex) {
        //TODO: Magic number
        int remaining = (tempCellIndex / numOfCells) % 2;
        int y;
        if (remaining == 0)
            y = (tempCellIndex % numOfCells) * Values.CELL_SIZE * 2;
        else
            y = Math.abs((tempCellIndex % numOfCells) - (numOfCells - 1)) * Values.CELL_SIZE * 2;
        return y;
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
