package View;

import Controller.SingleActionListener;
import Network.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {

    public StartFrame(){
        this.setSize(480,480);
        initStartFrame();
    }

    private void initStartFrame() {
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout());

        JButton single = new JButton("Single Player");
        single.addActionListener(new SingleActionListener(startPanel,this));
        JButton multiplayer = new JButton("Multiplayer");

        startPanel.add(single);
        startPanel.add(multiplayer);

        this.add(startPanel);
        this.setVisible(true);
    }
}
