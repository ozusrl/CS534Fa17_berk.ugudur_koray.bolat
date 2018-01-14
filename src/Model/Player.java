package Model;

import java.util.ArrayList;

/* Refactored 12.01.2018 14:58 */

public class Player {

    private String name;
    private int index;
    private ArrayList<Pirate> pirates;
    private ArrayList<Card> hand;
    private int numOfPirates;

    public Player(int index, String name, int numOfPirates) {
        this.index = index;
        this.name = name;
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
        this.hand = new ArrayList<>();
        setupPirates();
    }

    private void setupPirates() {
        for (int j = 0; j < numOfPirates; j++) {
            int STARTING_CELL = -1;
            this.addPirate(new Pirate(j, this.getIndex(), STARTING_CELL));
        }
    }

    public void addPirate(Pirate p) {
        pirates.add(p);
    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public int getIndex() {
        return index;
    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    public void discard(Card card) {
        hand.remove(card);
    }

    @Override
    public String toString() {
        String cardSymbols = "";
        for (int i = 0; i < hand.size(); i++) {
            cardSymbols += hand.get(i).toString() + " ";
        }
        return "Player" + index +
                " has " + hand.size() + " cards"
                + " and they are (" + cardSymbols + ")";
    }

}
