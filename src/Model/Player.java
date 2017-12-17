package Model;

import java.util.ArrayList;

public class Player {
    private int numOfPirates;
    private ArrayList<Pirate> pirates;

    public Player(int numOfPirates) {
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
    }
}
