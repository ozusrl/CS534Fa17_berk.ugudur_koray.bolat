package Controller;

import View.StartFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleActionListener implements ActionListener {

    private JPanel startPanel;
    private JFrame startFrame;

    public SingleActionListener(JPanel startPanel, StartFrame startFrame) {
        this.startPanel = startPanel;
        this.startFrame = startFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startPanel.removeAll();

        Integer[] playerNumbers = {2, 3, 4, 5};
        JLabel label1 = new JLabel("Player Number: ");
        JComboBox playerList = new JComboBox(playerNumbers);

        startPanel.add(label1);
        startPanel.add(playerList);

        Integer[] pirateNumber = {3, 4, 5, 6};
        JLabel label2 = new JLabel("Pirate Number: ");
        JComboBox pirateList = new JComboBox(pirateNumber);

        startPanel.add(label2);
        startPanel.add(pirateList);

        Integer[] segmentNumber = {2, 3, 4, 5, 6,7,8};
        JLabel label3 = new JLabel("Segment Number: ");
        JComboBox segmentList = new JComboBox(segmentNumber);

        startPanel.add(label3);
        startPanel.add(segmentList);

        Integer[] startingHandNumber = {6,7,8,9,10,11,12,25};
        JLabel label4 = new JLabel("Starting Card Number");
        JComboBox startHandList = new JComboBox(startingHandNumber);

        startPanel.add(label4);
        startPanel.add(startHandList);

        JButton SelectName = new JButton("Confirm");
        SelectName.addActionListener(new SelectNameListenerThenStart(playerList, pirateList, segmentList,startHandList, startPanel,startFrame));
        startPanel.add(SelectName);

        startPanel.repaint();
        startPanel.updateUI();
    }
}
