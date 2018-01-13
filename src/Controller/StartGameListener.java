package Controller;

import Model.Board;
import Model.Game;
import View.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartGameListener implements ActionListener {
    JTextField[] playerNamesFields;
    int pirateCount;
    int segmentCount;
    int startHandCount;
    JFrame startFrame;

    public StartGameListener(JTextField[] playerNamesFields, int pirateCount, int segmentCount,int startHandCount, JFrame startFrame) {
        this.playerNamesFields = playerNamesFields;
        this.pirateCount = pirateCount;
        this.segmentCount = segmentCount;
        this.startHandCount = startHandCount;
        this.startFrame = startFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startFrame.setVisible(false);
        String[] playerNames = new String[playerNamesFields.length];
        for(int i=0;i<playerNamesFields.length;i++){
            playerNames[i] = playerNamesFields[i].getText();
        }
        Board board = new Board(6,segmentCount);
        Game game = new Game(board,playerNamesFields.length, pirateCount, startHandCount, 30, playerNames);
        MainFrame mainFrame;
        game.start();
        try {
            mainFrame = new MainFrame(game);
            mainFrame.setVisible(true);
            GameController gameController = new GameController(mainFrame,game);
        } catch (IOException e1) {
            e1.printStackTrace();
        }




    }
}
