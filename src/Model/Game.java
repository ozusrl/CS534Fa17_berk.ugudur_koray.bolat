package Model;

import java.util.ArrayList;
import java.util.Collections;

/* Refactored 12.01.2018 16:25 */

public class Game {
    private Board board;
    private int numOfPlayers;
    private int numOfPirates;
    private int numOfEachSymbolOnDeck;
    private ArrayList<Player> players;
    private ArrayList<Card> deck;
    private int numOfStartingCards;
    private String[] names;
    private int currentPlayerIndex;
    private int turnNumber;

    public Game(Board board, int numOfPlayers, int numOfPirates,
                int numOfStartingCards, int numOfEachSymbolOnDeck, String[] names) {
        this.board = board;
        this.numOfPlayers = numOfPlayers;
        this.numOfPirates = numOfPirates;
        this.numOfStartingCards = numOfStartingCards;
        this.numOfEachSymbolOnDeck = numOfEachSymbolOnDeck;
        this.names = names;
        this.players = new ArrayList<>(numOfPlayers);
        this.deck = new ArrayList<>();
        setup();
    }

    private void setup() {
        this.currentPlayerIndex = 0;
        this.turnNumber = 0;
        setupPlayers();
        setupPirates();
        setupDeck();
    }

    public void start() {
        drawStartingCards();
    }

    private void setupPlayers() {
        for (int i = 0; i < numOfPlayers; i++)
            players.add(new Player(i, names[i], numOfPirates));
    }

    private void setupPirates() {
        for (Player player : players) {
            for (int j = 0; j < numOfPirates; j++) {
                int STARTING_CELL = -1;
                player.addPirate(new Pirate(j, player.getIndex(), STARTING_CELL));
            }
        }
    }

    private void setupDeck() {
        Symbol[] symbols = Symbol.values();
        for (int i = 0; i < symbols.length; i++)
            for (int j = 0; j < numOfEachSymbolOnDeck; j++)
                deck.add(new Card(j + i, symbols[i]));
        Collections.shuffle(deck);
    }

    private void drawStartingCards() {
        for (Player player : players) {
            drawCard(player, numOfStartingCards);
        }
    }

    private void drawCard(Player player, int drawCount) {
        for (int i = 0; i < drawCount; i++) {
            player.drawCard(deck.get(0));
            deck.remove(0);
        }
    }

    public void moveForward(Pirate pirate, Card card) {
        int availableCellIndexOnForward = getAvailableCellIndexOnForward(pirate, card);
        players.get(pirate.getPlayerIndex()).discard(card);
        pirate.move(availableCellIndexOnForward);
        System.out.println("Moving Forward: " + availableCellIndexOnForward);
    }

    public void moveBackward(Pirate pirate) {
        int availableCellIndexOnBackward = getAvailableCellIndexOnBackward(pirate);
        if (pirate.getCurrentCellIndex() != availableCellIndexOnBackward) {
            int drawableCardCount = getNumOfPiratesOnCell(availableCellIndexOnBackward);
            drawCard(getCurrentPlayer(), drawableCardCount);
            pirate.move(availableCellIndexOnBackward);
        }
        System.out.println("Moving Backward: " + availableCellIndexOnBackward);
    }

    public int getAvailableCellIndexOnForward(Pirate pirate, Card card) {
        for (Segment segment : board.getSegments()) {
            for (Cell cell : segment.getCells()) {
                if (canPirateMoveForwardTo(pirate, card, cell))
                    return cell.getIndex();
            }
        }
        return board.getEndCell().getIndex();
    }

    private boolean canPirateMoveForwardTo(Pirate pirate, Card card, Cell cell) {
        if (pirate.getCurrentCellIndex() < cell.getIndex() && card.getSymbol() == cell.getSymbol()) {
            if (getNumOfPiratesOnCell(cell.getIndex()) == 0)
                return true;
        }
        return false;
    }

    public int getAvailableCellIndexOnBackward(Pirate pirate) {
        int availableCellIndex = pirate.getCurrentCellIndex();
        for (Segment segment : board.getSegments()) {
            for (Cell cell : segment.getCells()) {
                if (canPirateMoveBackwardTo(pirate, cell))
                    availableCellIndex = cell.getIndex();
            }
        }
        return availableCellIndex;
    }

    private boolean canPirateMoveBackwardTo(Pirate pirate, Cell cell) {
        if (pirate.getCurrentCellIndex() > cell.getIndex()) {
            if (getNumOfPiratesOnCell(cell.getIndex()) > 0 && getNumOfPiratesOnCell(cell.getIndex()) < 3)
                return true;
        }
        return false;
    }

    private int getNumOfPiratesOnCell(int cellIndex) {
        return getPiratesOnCell(cellIndex).size();
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

    public Player getWinner() {
        for (Player player : players) {
            boolean finished = true;
            for (Pirate pirate : player.getPirates()) {
                if (pirate.getCurrentCellIndex() != getBoard().getEndCell().getIndex())
                    finished = false;
            }
            if (finished)
                return player;
        }
        return null;
    }

    public void switchTurn() {
        turnNumber++;
        if (turnNumber == 2) {
            turnNumber = 0;
            switchToNextPlayer();
        }
    }

    private void switchToNextPlayer() {
        currentPlayerIndex++;
        currentPlayerIndex = currentPlayerIndex % players.size();
        System.out.println("Now turn for Player " + currentPlayerIndex);
    }

    public boolean isFinished() {
        return getWinner() != null;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public Board getBoard() {
        return board;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public int getNumOfPirates() {
        return numOfPirates;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }
}
