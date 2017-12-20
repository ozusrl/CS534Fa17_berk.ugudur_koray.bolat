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

    public MainFrame(Game game) throws IOException {
        this.setSize(1280,720);
        this.game = game;
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
