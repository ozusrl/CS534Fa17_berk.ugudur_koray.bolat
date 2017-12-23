package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private Board board;
    private int numOfPlayers;
    private int numOfEachSymbolOnDeck;
    private int numOfPirates;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private final int STARTING_HAND_CARD_NUMBER = 5;
    private final int STARTING_CELL = -1;
    private int endCell;

    public Game(Board board, int numOfPlayers, int numOfEachSymbolOnDeck, int numOfPirates) {
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.numOfEachSymbolOnDeck = numOfEachSymbolOnDeck;
        this.players = new ArrayList<>(numOfPlayers);
        this.deck = new ArrayList<>();
        this.numOfPirates = numOfPirates;
        setup();
    }

    private void setup() {
        endCell= board.getAllCells().size();
        setupPlayers();
        setupDeck();
        addPirates();
        playersDrawCards();
       /* For testing cards
       for(int i=0;i<players.size();i++){
            System.out.println(players.get(i).toString());
        }
        */

    }



    private void addPirates() {
        for(int i=0; i<players.size();i++){
            for(int j=0; j<numOfPirates; j++){
                players.get(i).addPirate(new Pirate(STARTING_CELL));
            }
        }
    }

    private void setupPlayers() {
        for (int i = 0; i < numOfPlayers; i++)
            players.add(new Player(board,numOfPirates,i));
    }

    private void setupDeck() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < symbols.length; i++)
            for (int j = 0; j < numOfEachSymbolOnDeck; j++)
                deck.add(new Card(symbols[i]));
        Collections.shuffle(deck);
    }

    private void playersDrawCards() {

        for(int i=0;i<players.size();i++){
            for(int j=0;j<STARTING_HAND_CARD_NUMBER;j++){
                players.get(i).drawCard(deck.get(0));
                deck.remove(0);
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
