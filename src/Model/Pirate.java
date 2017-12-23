package Model;

/**
 * Created by berku on 17.12.2017.
 */
public class Pirate {

private int currentCellPosition;

public Pirate(int currentCellPosition){
        this.currentCellPosition = currentCellPosition;
    }


    public int getCurrentCellPosition() {
        return currentCellPosition;
    }

    public void setCurrentCellPosition(int currentCellPosition) {
        this.currentCellPosition = currentCellPosition;
    }
}
