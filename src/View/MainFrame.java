package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {

    private GameView gameView;
    private RightPanel rightPanel;
    private Game game;

    public MainFrame(Game game) throws IOException {
        this.setSize(1280, 720);
        this.game = game;
        rightPanel = new RightPanel(game);
        gameView = new GameView(game);
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280,759));
        this.setLayout(new BorderLayout());
        this.add(gameView, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public RightPanel getRightPanel(){
        return rightPanel;
    }

    public GameView getGameView() {
        return gameView;
    }
}
