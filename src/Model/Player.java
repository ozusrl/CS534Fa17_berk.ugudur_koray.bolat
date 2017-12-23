package Model;

import java.util.ArrayList;

public class Player {

    private Board board;
    private int playerNumber;
    private int numOfPirates;
    private ArrayList<Pirate> pirates;
    private ArrayList<Card> hand;

    public Player(Board board, int numOfPirates, int playerNumber) {
        this.board = board;
        this.playerNumber = playerNumber;
        this.numOfPirates = numOfPirates;
        this.pirates = new ArrayList<>(numOfPirates);
        this.hand = new ArrayList<>();
    }

    public void addPirate(Pirate p) {
        pirates.add(p);
    }

    public void moveForward(Card card, Pirate pirate) {
        Symbol tmpSymbol = card.getSymbol();
        int piratePosition = pirate.getCurrentCellPosition();
        if (piratePosition == -1)
            initialStart(pirate, tmpSymbol);
        else
            secondialStart(pirate, tmpSymbol);

    }


    private void initialStart(Pirate p, Symbol sym) {
        //These two four loop look from the beginning to all cells
        for (int i = 0; i < board.getSegments().size(); i++) {
            for (int j = 0; j < board.getSegments().get(i).getCells().size(); j++) {
                //That means we use our card and we want the go to that symbol-cell
                if (board.getSegments().get(i).getCells().get(j).getSymbol().equals(sym)) {
                    //That means this cell is empty, no jump , move the pirate to that cell
                    if (board.getSegments().get(i).getCells().get(j).getPirateArraySize() == 0) {
                        p.setCurrentCellPosition(board.getSegments().get(i).getCells().get(j).getIndex());
                        board.getSegments().get(i).getCells().get(j).addPirate(p);
                    //That means this cell is not empty, jump
                    } else {
                        p.setCurrentCellPosition(board.getSegments().get(i).getCells().get(j).getIndex());
                        secondialStart(p, sym);
                    }
                }
            }
        }
    }

    private void secondialStart(Pirate p, Symbol sym) {

        int tmpPiratePosition = p.getCurrentCellPosition();
       //Son segmentte mi diye teyit ediyorum
        if(((tmpPiratePosition/6)+1)==board.getSegments().size()){
            //Gücüm burda tukendi :(
        }
        //Again these 2 four loop
        for (int i = (tmpPiratePosition/6)+1; i < board.getSegments().size(); i++) {
            for (int j = 0; j < board.getSegments().get(i).getCells().size(); j++) {

            }
        }
    }

    public void moveBackward(Pirate pirate) {

    }

    public void drawCard(Card card) {
        hand.add(card);
    }

    public int setPlayerNumber(int x) {
        return playerNumber = x;
    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    @Override
    public String toString() {
        String cardSymbols = "";
        for (int i = 0; i < hand.size(); i++) {
            cardSymbols += hand.get(i).toString() + " ";
        }
        return "Player" + playerNumber +
                " has " + hand.size() + " cards"
                + " and they are (" + cardSymbols + ")";
    }
}
