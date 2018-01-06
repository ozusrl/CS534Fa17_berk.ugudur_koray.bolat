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
    private final int STARTING_HAND_CARD_NUMBER = 12;
    private final int STARTING_CELL = -1;
    private int currentPlayerIndex;
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
        endCell = board.getAllCells().size();
        this.currentPlayerIndex = 0;
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
        for (int i = 0; i < players.size(); i++) {
            for (int j = 0; j < numOfPirates; j++) {
                players.get(i).addPirate(new Pirate(j, i, STARTING_CELL));
            }
        }
    }

    private void setupPlayers() {
        for (int i = 0; i < numOfPlayers; i++)
            players.add(new Player(board, numOfPirates, i));
    }

    private void setupDeck() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < symbols.length; i++)
            for (int j = 0; j < numOfEachSymbolOnDeck; j++)
                deck.add(new Card(symbols[i]));
        Collections.shuffle(deck);
    }

    private void playersDrawCards() {
        for (Player player : players) {
            drawCard(player, STARTING_HAND_CARD_NUMBER);
        }
    }

    private void drawCard(Player player, int drawCount) {
        for (int i = 0; i < drawCount; i++) {
            player.drawCard(deck.get(0));
            deck.remove(0);
        }
    }

    private void switchToNextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex = currentPlayerIndex % players.size();
        System.out.println("Now turn for Player " + currentPlayerIndex);
    }

    public void moveForward(Pirate pirate, Card card) {
        int cellIndex = getFirstAvailableCellOnForward(pirate, card);
        System.out.println("cellIndex: " + cellIndex);
        pirate.move(cellIndex);
    }

    public void moveBackward(Pirate pirate) {
        Player currentPlayer = players.get(currentPlayerIndex);
        int cellIndex = getFirstAvailableCellOnBackward(pirate);
        if (pirate.getCurrentCellIndex() != cellIndex) {
            int drawableCardCount = getNumOfPiratesOnCell(cellIndex);
            drawCard(currentPlayer, drawableCardCount);
            pirate.move(cellIndex);
        }
    }


    private int getFirstAvailableCellOnForward(Pirate pirate, Card card) {
        int pirateCellIndex = pirate.getCurrentCellIndex();
        for (Cell cell : board.getAllCells()) {
            if (pirateCellIndex < cell.getIndex() && card.getSymbol() == cell.getSymbol()) {
                if (getNumOfPiratesOnCell(cell.getIndex()) == 0)
                    return cell.getIndex();
            }
        }
        return 0; //last cell'e vardıktan sonra olacak process eklenecek
    }

    private int getFirstAvailableCellOnBackward(Pirate pirate) {
        int pirateCellIndex = pirate.getCurrentCellIndex();
        int availableCellIndex = pirate.getCurrentCellIndex();
        for (Cell cell : board.getAllCells()) {
            if (pirateCellIndex > cell.getIndex()) {
                if (getNumOfPiratesOnCell(cell.getIndex()) > 0 && getNumOfPiratesOnCell(cell.getIndex()) < 3)
                    availableCellIndex = cell.getIndex();
            }
        }
        return availableCellIndex;
    }

    public int getNumOfPiratesOnCell(int cellIndex) {
        int numOfPiratesOnCell = 0;
        for (Player player : players) {
            for (Pirate pirate : player.getPirates()) {
                if (pirate.getCurrentCellIndex() == cellIndex)
                    numOfPiratesOnCell++;
            }
        }
        return numOfPiratesOnCell;
    }

    public ArrayList<Pirate> getPiratesOnCell(int cellIndex) {
        ArrayList<Pirate> pirates = new ArrayList<>();
        for (Player player : players) {
            for (Pirate pirate : player.getPirates()) {
                if (pirate.getCurrentCellIndex() == cellIndex)
                    pirates.add(pirate);
            }
        }
        return pirates;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }
}
