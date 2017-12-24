package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        game.getPlayers().get(1).getPirates().get(0).move(12);
        game.getPlayers().get(1).getPirates().get(1).move(1);
        game.getPlayers().get(1).getPirates().get(2).move(4);
        game.getPlayers().get(1).getPirates().get(3).move(13);
        game.getPlayers().get(1).getPirates().get(4).move(11);
        game.getPlayers().get(1).getPirates().get(5).move(5);
        rightPanel.getBtn2().addActionListener(e -> {

            Pirate pirate =game.getPlayers().get(2).getPirates().get(0);
            System.out.println(pirate.getCurrentCellIndex());
            game.moveForward(pirate,new Card(Symbol.HAT));
            //game.moveBackward(pirate);
            System.out.println(pirate.getCurrentCellIndex());
            this.repaint();
        });
        this.repaint();
    }
}
