package View;

import Model.Board;
import Model.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private GameView gameView;
    private RightPanel rightPanel = new RightPanel();
    private Game game;

    public MainFrame() throws IOException {
        this.setSize(1280,720);
        game = new Game(new Board(6,6),4,30,6);
        gameView = new GameView(game);
        init();
    }

    private void init() {
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        this.add(gameView,BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
        this.repaint();
    }
}
