package Model;

import java.util.ArrayList;

/**
 * Created by berku on 17.12.2017.
 */
public class Player {
    private int numOfPirates;
    private ArrayList<Pirate> pirates;
    public Player(int numOfPirates) {
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
    }
}
