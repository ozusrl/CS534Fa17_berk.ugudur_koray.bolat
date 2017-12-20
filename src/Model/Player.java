package Model;

import java.util.ArrayList;

public class Player {
    private int numOfPirates;
    private ArrayList<Pirate> pirates;
    private ArrayList<Card> hand;

    public Player(int numOfPirates) {
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
        this.hand = new ArrayList<>();
    }

    public void addPirate(Pirate p){
        pirates.add(p);
    }

    public void moveForward(Card card, Pirate pirate){

    }

    public void moveBackward(Pirate pirate){

    }


}
