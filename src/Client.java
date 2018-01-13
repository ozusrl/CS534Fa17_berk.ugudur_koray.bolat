import Controller.GameController;
import Model.*;
import Network.Command;
import View.MainFrame;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by berku on 12.1.2018.
 */
public class Client {
    public static void main(String args[]) throws Exception {
        int index = 0;
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter server IP:");
        String serverIP = "10.200.34.13";

        Socket socket = new Socket(serverIP, Server.PORT);

        // create reader and writer
        ObjectOutputStream writer = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream reader = new ObjectInputStream(socket.getInputStream());

        System.out.println("Connected to Server");
        System.out.print("Enter your name: ");
        String name = "berk";
        writer.writeObject(name);
        index = (int) reader.readObject();
        System.out.println("My index is: " + index);
        System.out.println("Waiting for game start");

        String[] names = (String[]) reader.readObject();
        ArrayList<Segment> segments = (ArrayList<Segment>) reader.readObject();
        ArrayList<Card> deck = (ArrayList<Card>) reader.readObject();

        Board board = new Board(6, 3);
        Game game = new Game(board, 2, 1, 6, 30, names);
        game.getBoard().setSegments(segments);
        game.getBoard().setCells();
        System.out.println(game.getDeck());

        game.setDeck(deck);

        game.start();

        MainFrame mainFrame = new MainFrame(game);
        mainFrame.setTitle("" + index);
        GameController gameController = new GameController(mainFrame, game);
        mainFrame.setVisible(true);
        mainFrame.repaint();
        System.out.println("Game is starting now...");
        System.out.println(game.getDeck());

        int i = 0;
        while (true) {
            boolean isCurrentPlayer = game.getCurrentPlayer().getIndex() == index;
            if (isCurrentPlayer) {
                if (i == 0) {
                    mainFrame.getRightPanel().setRightPanel(true);
                }
                i++;
                Command command = gameController.getLastCommand();
                if (command != null) {
                    i = 0;
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
    }
}
