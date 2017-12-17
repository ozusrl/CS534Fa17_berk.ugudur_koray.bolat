package Model;

import java.util.ArrayList;

public class Board {
    private int numOfCells;
    private int numOfSegments;
    private ArrayList<Segment> segments;

    public Board(int numOfCells, int numOfSegments) {
        this.numOfCells = numOfCells;
        this.numOfSegments = numOfSegments;
        this.segments = new ArrayList<>(numOfSegments);
        createSegments();
    }

    private void createSegments() {
        for(int i = 0; i < numOfSegments; i++)
            segments.add(new Segment(numOfCells));
    }

    public int getNumOfSegments() {
        return numOfSegments;
    }

    public int getNumOfCells() {
        return numOfCells;
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
