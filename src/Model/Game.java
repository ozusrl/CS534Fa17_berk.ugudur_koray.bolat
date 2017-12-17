package Model;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private Board board;
    private int numOfPlayers;
    private int numOfCards;
    private int numOfPirates;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;

    public Game(Board board, int numOfPlayers, int numOfCards, int numOfPirates) {
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.numOfCards = numOfCards;
        this.players = new ArrayList<>(numOfPlayers);
        this.deck = new ArrayList<>(numOfCards);
        this.numOfPirates = numOfPirates;
        setup();
    }

    private void setup() {
        setupPlayers();
        setupDeck();
    }

    private void setupPlayers() {
        for (int i = 0; i < numOfPlayers; i++)
            players.add(new Player(numOfPirates));
    }

    private void setupDeck() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < numOfCards; i++)
            for (int j = 0; j < 30; j++)
                deck.add(new Card(symbols[i]));
        Collections.shuffle(deck);
    }

}
