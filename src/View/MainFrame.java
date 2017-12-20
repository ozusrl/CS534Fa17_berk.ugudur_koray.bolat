package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private GameView gameView = new GameView();
    private RightPanel rightPanel = new RightPanel();

    public MainFrame(){
        this.setSize(1280,720);
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
