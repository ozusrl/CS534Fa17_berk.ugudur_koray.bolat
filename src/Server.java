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
public class Server implements Serializable {
    public final static int PORT = 3458;

    public static void main(String args[]) throws Exception {
        ServerSocket socket = new ServerSocket(PORT);
        ArrayList<ObjectInputStream> readers = new ArrayList<>();
        ArrayList<ObjectOutputStream> writers = new ArrayList<>();
        String[] names = new String[2];
        System.out.println("Waiting for player connect");
        int numOfPlayers = 0;
        Socket clientSocket = null;
        while (numOfPlayers < 2) {
            clientSocket = socket.accept();
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

        Board board = new Board(6, 3);
        Game game = new Game(board, 2, 1, 6, 30, names);
        GameController gameController = new GameController(new MainFrame(game), game);
        System.out.println(game.getDeck());

        for (ObjectOutputStream writer : writers) {
            writer.writeObject(names);
            writer.writeObject(game.getBoard().getSegments());
            writer.writeObject(game.getDeck());
        }
        game.start();

        System.out.println(game.getDeck());

        while (true) {
            int currentPlayerIndex = game.getCurrentPlayer().getIndex();
            Command command = (Command) readers.get(currentPlayerIndex).readObject();
            command.executeOn(game);
            gameController.switchTurnRoutine();
            for(int i = 0; i < writers.size(); i++){
                if(i != currentPlayerIndex)
                    writers.get(i).writeObject(command);
            }
            command = null;
        }
    }
}
