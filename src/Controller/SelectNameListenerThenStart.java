package Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectNameListenerThenStart implements ActionListener {
    JComboBox playerNumber;
    JComboBox pirateNumber;
    JComboBox segmentNumber;
    JComboBox startHandNumber;
    JPanel panel;
    JFrame startFrame;

    public SelectNameListenerThenStart(JComboBox playerNumber, JComboBox pirateNumber, JComboBox segmentNumber,JComboBox startHandNumber, JPanel startPanel, JFrame startFrame) {
        this.playerNumber = playerNumber;
        this.pirateNumber = pirateNumber;
        this.segmentNumber = segmentNumber;
        this.startHandNumber=startHandNumber;
        panel = startPanel;
        this.startFrame = startFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.removeAll();

        JPanel gridPanel = new JPanel();

        int playerCount = (int) playerNumber.getSelectedItem();
        int pirateCount = (int) pirateNumber.getSelectedItem();
        int segmentCount = (int) segmentNumber.getSelectedItem();
        int startHandCount = (int) startHandNumber.getSelectedItem();

        JTextField[] playerNamesFields = new JTextField[playerCount];

        gridPanel.setLayout(new GridLayout(playerCount,2));
        panel.setLayout(new BorderLayout());

        for (int i = 0; i < playerCount; i++) {
            JLabel lbl = new JLabel("PlayerName " + i);
            JTextField txt = new JTextField("", 20);
            gridPanel.add(lbl);
            gridPanel.add(txt);
            playerNamesFields[i] = txt;
        }

        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(new StartGameListener(playerNamesFields,pirateCount,segmentCount,startHandCount,startFrame));
        panel.add(gridPanel,BorderLayout.NORTH);
        panel.add(startGame,BorderLayout.SOUTH);
        panel.updateUI();
    }
}
