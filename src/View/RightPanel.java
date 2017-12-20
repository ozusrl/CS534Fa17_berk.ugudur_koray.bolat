package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightPanel extends JPanel {

    public RightPanel(){
        createRightPanelComponents();
    }

    private void createRightPanelComponents() {
        this.setLayout(new BorderLayout());

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());
        this.add(middlePanel, BorderLayout.CENTER);

        SpecialButton btn1 = new SpecialButton("End Turn.png");
        btn1.setBackColor(52,204,255);
        middlePanel.add(btn1,BorderLayout.CENTER);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Turun bitti");
            }
        });

        JButton btn2 = new JButton("Fall Back");
        btn2.setVisible(true);
        btn2.setBounds(new Rectangle(120,72));
        middlePanel.add(btn2, BorderLayout.CENTER);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        this.add(topPanel, BorderLayout.NORTH);

        JRadioButton r1 = new JRadioButton("True");
        r1.setVisible(true);
        topPanel.add(r1);

        JRadioButton r2 = new JRadioButton("False");
        r2.setVisible(true);
        topPanel.add(r2);

        this.repaint();
    }
}
