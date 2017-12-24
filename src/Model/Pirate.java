package Model;

/**
 * Created by berku on 17.12.2017.
 */
public class Pirate {
    private int index;
    private int playerIndex;
    private int currentCellIndex;

    public Pirate(int index, int playerIndex, int currentCellIndex) {
        this.index = index;
        this.playerIndex = playerIndex;
        this.currentCellIndex = currentCellIndex;
    }

    public int getCurrentCellIndex() {
        return currentCellIndex;
    }

    public void move(int cellIndex) {
        currentCellIndex = cellIndex;
    }

    public int getIndex() {
        return index;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }
}
