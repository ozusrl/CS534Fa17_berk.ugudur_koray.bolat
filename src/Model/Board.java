package Model;

import javax.lang.model.type.NullType;
import java.util.ArrayList;

public class Board {
    private int numOfCells;
    private int numOfSegments;

    private ArrayList<Segment> segments;
    private ArrayList<Cell> allCells = new ArrayList<>();
    private Cell startCell;
    private Cell endCell;
    public Board(int numOfCells, int numOfSegments) {
        this.numOfCells = numOfCells;
        this.numOfSegments = numOfSegments;
        this.startCell = new Cell(null);
        this.endCell = new Cell(null);
        this.startCell.setIndex(-1);
        this.endCell.setIndex(numOfCells*numOfSegments);
        this.segments = new ArrayList<>(numOfSegments);
        createSegments();
        setAllCells();
       /* for testing cells
        for(Segment s: segments){
            for(Cell c: s.getCells()){
                System.out.println(c.toString());
            }
        }*/
    }

    private void createSegments() {
        for (int i = 0; i < numOfSegments; i++)
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

    public void  setAllCells() {
        if (allCells.size() == 0) {
            for (int i = 0; i < segments.size(); i++) {
                for (int j = 0; j < segments.get(i).getCells().size(); j++) {
                    allCells.add(segments.get(i).getCells().get(j));
                }
            }
        }
    }

    public ArrayList<Cell> getAllCells(){
        return allCells;
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
