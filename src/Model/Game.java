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
        setupPlayers();
        setupDeck();
        addPirates();
    }

    private void addPirates() {
        for(int i=0; i<players.size();i++){
            for(int j=0; j<numOfPirates; j++){
                //players.get(i).addPirate(new Pirate());
            }
        }
    }

    private void setupPlayers() {
        for (int i = 0; i < numOfPlayers; i++)
            players.add(new Player(numOfPirates));
    }

    private void setupDeck() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < symbols.length; i++)
            for (int j = 0; j < numOfEachSymbolOnDeck; j++)
                deck.add(new Card(symbols[i]));
    }

}
