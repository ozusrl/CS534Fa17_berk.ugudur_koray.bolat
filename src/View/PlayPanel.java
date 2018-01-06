package View;

import Model.Card;
import Model.Game;
import Model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by berku on 6.1.2018.
 */
public class PlayPanel extends JPanel {
    private Game game;
    private Map<Symbol, Image> cardMap;
    private int justStart;
    private JButton play;
    private JButton forward;
    private JButton backward;
    private JLabel currentCard;
    private JLabel currentDirection;
    private ArrayList<CardButton> cardButtons;

    public PlayPanel(Game game, Map<Symbol, Image> cardMap) {
        this.setPreferredSize(Values.RIGHT_PANEL_DIMENSION);
        this.setBackground(new Color(226, 170, 130));
        this.setLayout(null);
        this.game = game;
        this.cardMap = cardMap;
        this.cardButtons = new ArrayList<>();
        this.justStart = 1;
        forward = new JButton("Forward");
        backward = new JButton("Backward");
        play = new JButton("Play");
        currentCard = new JLabel("Current Card:");
        currentDirection = new JLabel("Current Direction: ");
        Dimension size = this.getPreferredSize();
        currentCard.setBounds(0, size.height - 300, 200, 50);
        currentDirection.setBounds(0, size.height - 400, 200, 50);
        backward.setBounds(0, size.height - 75, size.width / 2, 25);
        forward.setBounds(size.width / 2, size.height - 75, size.width / 2, 25);
        play.setBounds(0, size.height - 50, size.width, 50);

        backward.addActionListener(e -> {
            updateCurrentDirection("backward");
            forward.setEnabled(true);
            backward.setEnabled(false);
        });

        forward.addActionListener(e -> {
            updateCurrentDirection("forward");
            forward.setEnabled(false);
            backward.setEnabled(true);
        });

        this.add(currentCard);
        this.add(currentDirection);
        this.add(forward);
        this.add(backward);
        this.add(play);
    }

    public void updateCurrentCard(Symbol symbol, int x) {
        currentCard.setText("Current Card: " + x);
    }

    public void updateCurrentDirection(String direction) {
        currentDirection.setText("Current Direction: " + direction);
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getForward() {
        return forward;
    }

    public JButton getBackward() {
        return backward;
    }

    public JLabel getCurrentCard() {
        return currentCard;
    }

    public JLabel getCurrentDirection() {
        return currentDirection;
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public void repaintCardButtons() {
        removeCardButtons();
        addCardButtons();
    }

    public void removeCardButtons() {
        if (justStart == 0) {
            for (int j = 0; j < cardButtons.size(); j++) {
                this.remove(cardButtons.get(j));
                cardButtons.remove(j);
                j--;
            }
        }
    }

    public void addCardButtons() {
        int i = 0;
        Dimension size = this.getPreferredSize();
        System.out.println("cur:" + game.getCurrentPlayer().getIndex());
        for (Card c : game.getCurrentPlayer().getHand()) {
            CardButton btn = new CardButton(c, cardMap.get(c.getSymbol()));
            btn.setBounds(i * 26, size.height - 200, 24, 24);
            cardButtons.add(btn);
            this.add(btn);
            i++;
        }
        justStart = 0;
    }

    public void setCardButtonsEnabled(boolean enable) {
        for (CardButton c : cardButtons)
            c.setEnabled(enable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
