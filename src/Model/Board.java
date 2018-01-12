package Model;

import java.util.ArrayList;

/* Refactored 12.01.2018 14:43 */

public class Board {
    private int numOfCells;
    private int numOfSegments;
    private ArrayList<Segment> segments;
    private Cell startCell;
    private Cell endCell;
    private ArrayList<Cell> cells = new ArrayList<>();

    public Board(int numOfCells, int numOfSegments) {
        this.numOfCells = numOfCells;
        this.numOfSegments = numOfSegments;
        this.segments = new ArrayList<>(numOfSegments);
        createSegments();
        setStartAndEndCells();
        setCells();
    }

    private void createSegments() {
        for (int i = 0; i < numOfSegments; i++)
            segments.add(new Segment(i, numOfCells));
    }

    private void setStartAndEndCells() {
        this.startCell = new Cell(-1,null);
        this.endCell = new Cell(numOfCells * numOfSegments,null);
    }

    private void setCells() {
        if (cells.isEmpty()) {
            for (Segment segment : segments) {
                cells.addAll(segment.getCells());
            }
        }
    }

    public int getNumOfSegments() {
        return numOfSegments;
    }

    public int getNumOfCells() {
        return numOfCells;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    @Override
    public String toString() {
        return "Board{" +
                "numOfCells=" + numOfCells +
                ", numOfSegments=" + numOfSegments +
                ",segments= \n " + segments +
                "}";
    }
}
