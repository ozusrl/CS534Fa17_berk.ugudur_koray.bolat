

import Controller.GameController;
import Model.Board;
import Model.Game;
import View.MainFrame;

import javax.swing.*;
import java.io.IOException;

public class Main {
    /*public static void main(String[] args){
        System.out.println("Hello pirates!");
        MainFrame mainFrame = new MainFrame();
        Game game = new Game(new Board(6,6),4,30,6);
*/

    public static void main(String[] args) throws IOException {
        System.out.println("Hello pirates!");
        Board board = new Board(6, 6);
        Game game = new Game(board, 5, 30, 6);
        MainFrame mainFrame = new MainFrame(game);
        mainFrame.setVisible(true);
        GameController gameController = new GameController(mainFrame, game);
    }
}


