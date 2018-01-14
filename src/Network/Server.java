package Network;

import Controller.GameController;
import Model.Board;
import Model.Game;
import Model.Segment;
import Network.Command;
import View.MainFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by berku on 12.1.2018.
 */
public class Server implements Serializable, Runnable {
    public final static int PORT = 3458;

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(PORT);

            ArrayList<ObjectInputStream> readers = new ArrayList<>();
            ArrayList<ObjectOutputStream> writers = new ArrayList<>();

            int[] gameInfo = new int[4];
            gameInfo[0] = Integer.parseInt(ConsoleInputHandler.getUserInput("How many player?"));
            gameInfo[1] = Integer.parseInt(ConsoleInputHandler.getUserInput("How many pirates?"));
            gameInfo[2] = Integer.parseInt(ConsoleInputHandler.getUserInput("How many starting cards?"));
            gameInfo[3] = Integer.parseInt(ConsoleInputHandler.getUserInput("How many segments?"));
            String[] names = new String[gameInfo[0]];

            System.out.println("Waiting for player connect");

            int numOfPlayers = 0;

            while (numOfPlayers < gameInfo[0]) {
                Socket clientSocket = socket.accept();
                ObjectInputStream reader = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream writer = new ObjectOutputStream(clientSocket.getOutputStream());

                System.out.println("Waiting for name player " + numOfPlayers);
                String name = (String) reader.readObject();
                names[numOfPlayers] = name;
                writer.writeObject(numOfPlayers);
                numOfPlayers++;
                System.out.println("Someone connected, current numOfPlayers: " + numOfPlayers);
                writers.add(writer);
                readers.add(reader);
            }

            Board board = new Board(6, gameInfo[3]);
            Game game = new Game(board, gameInfo[0], gameInfo[1], gameInfo[2], 30, names);
            GameController gameController = new GameController(new MainFrame(game), game);

            for (ObjectOutputStream writer : writers) {
                writer.writeObject(names);
                writer.writeObject(gameInfo);
                writer.writeObject(game.getBoard().getSegments());
                writer.writeObject(game.getDeck());
            }
            game.start();

            while (true) {
                int currentPlayerIndex = game.getCurrentPlayer().getIndex();
                Command command = (Command) readers.get(currentPlayerIndex).readObject();
                command.executeOn(game);
                gameController.switchTurnRoutine();
                for(int i = 0; i < writers.size(); i++){
                    if(i != currentPlayerIndex)
                        writers.get(i).writeObject(command);
                }
            }
        }
        catch (Exception e){
        }

    }
}
