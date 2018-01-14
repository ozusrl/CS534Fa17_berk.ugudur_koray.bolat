package View;

import Model.*;
import View.GamePanel.GamePanel;
import View.Manager.PositionFinder;
import View.RightPanel.RightPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    private GamePanel gamePanel;
    private RightPanel rightPanel;
    private Game game;

    public MainFrame(Game game) throws IOException {
        this.setSize(1280, 720);
        this.game = game;
        PositionFinder positionFinder = new PositionFinder(game,this);
        gamePanel = new GamePanel(game, positionFinder);
        rightPanel = new RightPanel(game, positionFinder);
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280,759));
        this.setLayout(new BorderLayout());
        this.add(gamePanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public RightPanel getRightPanel(){
        return rightPanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
