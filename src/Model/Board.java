package Model;

import java.util.ArrayList;

/* Refactored 12.01.2018 14:43 */

public class Board {
    private int numOfCells;
    private int numOfSegments;
    private ArrayList<Segment> segments;
    private Cell startCell;
    private Cell endCell;

    public Board(int numOfCells, int numOfSegments) {
        this.numOfCells = numOfCells;
        this.numOfSegments = numOfSegments;
        this.segments = new ArrayList<>(numOfSegments);
        createSegments();
        setStartAndEndCells();
    }

    private void createSegments() {
        for (int i = 0; i < numOfSegments; i++)
            segments.add(new Segment(i, numOfCells));
    }

    private void setStartAndEndCells() {
        this.startCell = new Cell(-1, null);
        this.endCell = new Cell(numOfCells * numOfSegments, null);
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

    public Cell getStartCell() {
        return startCell;
    }

    public Cell getEndCell() {
        return endCell;
    }

    public void setSegments(ArrayList<Segment> segments) {
        this.segments = segments;
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
