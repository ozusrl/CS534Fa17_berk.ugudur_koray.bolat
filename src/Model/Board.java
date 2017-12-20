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
        for(Segment s: segments){
            for(Cell c: s.getCells()){
                System.out.println(c.toString());
            }
        }
    }

    private void createSegments() {
        for(int i = 0; i < numOfSegments; i++)
            segments.add(new Segment(i, numOfCells));
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

    @Override
    public String toString() {
        return "Board{" +
                "numOfCells=" + numOfCells +
                ", numOfSegments=" + numOfSegments +
                ",segments= \n " + segments +
                "}";
    }
}
