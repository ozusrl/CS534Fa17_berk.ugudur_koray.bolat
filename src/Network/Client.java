package Network;

import Controller.GameController;
import Model.*;
import View.MainFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by berku on 12.1.2018.
 */
public class Client implements Runnable {

    @Override
    public void run() {
        try {
            int playerIndex;
            String playerName = ConsoleInputHandler.getUserInput("Enter your name: ");
            String serverIP = ConsoleInputHandler.getUserInput("Enter server address: ");


            // CONNECTING | Creating socket, writer and reader
            Socket socket = new Socket(serverIP, Server.PORT);
            ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

            // CONNECTED  | Sending playerName and getting PlayerIndex
            System.out.println("Succesfully connected to server " + serverIP);
            writer.writeObject(playerName);
            playerIndex = (int) reader.readObject();
            System.out.println("My index is: " + playerIndex);

            // WAIT
            System.out.println("Waiting for the game start.");

            // PREPARE    | Get all players and game information
            String[] names = (String[]) reader.readObject();
            int[] gameInfo = (int[]) reader.readObject();
            System.out.println("All players connected, game will start soon");
            ArrayList<Segment> segments = (ArrayList<Segment>) reader.readObject();
            ArrayList<Card> deck = (ArrayList<Card>) reader.readObject();

            // SETUP and START
            Board board = new Board(6, gameInfo[3]);
            Game game = new Game(board, gameInfo[0], gameInfo[1], gameInfo[2], 30, names);
            game.getBoard().setSegments(segments);
            game.setDeck(deck);
            game.start();
            MainFrame mainFrame = new MainFrame(game);
            mainFrame.setTitle("(" + playerIndex + ") " + playerName);
            GameController gameController = new GameController(mainFrame, game);
            System.out.println("Game is starting now...");
            mainFrame.setVisible(true);
            mainFrame.repaint();

            // GAME LOOP
            int loopCount = 0; // TODO : It should be reorganized
            while (true) {
                boolean isCurrentPlayer = game.getCurrentPlayer().getIndex() == playerIndex;
                if (isCurrentPlayer) {
                    if (loopCount == 0)
                        mainFrame.getRightPanel().setRightPanel(true);
                    loopCount++;
                    Command command = gameController.getLastCommand();
                    if (command != null) {
                        loopCount = 0;
                        writer.writeObject(command);
                        gameController.setLastCommand(null);
                    }
                } else {
                    mainFrame.getRightPanel().setRightPanel(false);
                    Command command = (Command) reader.readObject();
                    command.executeOn(game);
                    gameController.switchTurnRoutine();
                }
            }
        } catch (Exception e) {

        }
    }
}
