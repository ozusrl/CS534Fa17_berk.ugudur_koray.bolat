package Model;

import java.util.ArrayList;

public class Player {

    private Board board;
    private String name;
    private int index;
    private int numOfPirates;
    private ArrayList<Pirate> pirates;
    private ArrayList<Card> hand;

    public Player(Board board, int numOfPirates, int index, String name) {
        this.board = board;
        this.index = index;
        this.name = name;
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
        this.hand = new ArrayList<>();
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

    public void discard(Card card){
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
