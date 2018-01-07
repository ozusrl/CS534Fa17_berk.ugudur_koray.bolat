package View;

import Model.*;

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
    private ArrayList<Color> colors;
    private JButton play;
    private JButton forward;
    private JButton backward;
    private JButton skip;
    private JLabel currentCard;
    private JLabel currentDirection;
    private JLabel currentPlayer;
    private JLabel currentPirate;
    private ArrayList<CardButton> cardButtons;
    private ArrayList<JButton> pirateButtons;

    public PlayPanel(Game game, Map<Symbol, Image> cardMap) {
        this.setPreferredSize(Values.RIGHT_PANEL_DIMENSION);
        this.setBackground(new Color(226, 170, 130));
        this.setLayout(null);
        this.game = game;
        this.cardMap = cardMap;
        this.cardButtons = new ArrayList<>();
        this.pirateButtons = new ArrayList<>();
        this.justStart = 1;
        this.colors = new Colors().getColors();
        forward = new JButton("Forward");
        backward = new JButton("Backward");
        play = new JButton("Play");
        skip = new JButton("Skip");
        currentCard = new JLabel("Current Card:");
        currentDirection = new JLabel("Current Direction: ");
        currentPlayer = new JLabel("Current Player: ");
        currentPirate = new JLabel("Current Pirate: ");
        Dimension size = this.getPreferredSize();
        currentCard.setBounds(0, size.height - 300, 200, 50);
        currentDirection.setBounds(0, size.height - 400, 200, 50);
        currentPlayer.setBounds(0, size.height - 110, 200, 50);
        currentPirate.setBounds(0, size.height - 170, 200, 50);
        backward.setBounds(0, size.height - 75, size.width / 2, 25);
        forward.setBounds(size.width / 2, size.height - 75, size.width / 2, 25);
        play.setBounds(0, size.height - 50, size.width - 75, 50);
        skip.setBounds(size.width-75, size.height - 50, 75, 50);


        this.add(currentCard);
        this.add(currentDirection);
        this.add(currentPlayer);
        this.add(currentPirate);
        this.add(forward);
        this.add(backward);
        this.add(play);
        this.add(skip);
    }

    public void updateCurrentCard(Symbol symbol, int x) {
        currentCard.setText("Current Card: " + x);
    }

    public void updateCurrentDirection(String direction) {
        currentDirection.setText("Current Direction: " + direction);
    }

    public void updateCurrentPirateLabel(Pirate pirate){
        currentPirate.setForeground(new Colors().getColors().get(pirate.getIndex()));
        currentPirate.setText("Current Pirate: " + pirate.getIndex());
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

    public JButton getSkip() {
        return skip;
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public ArrayList<JButton> getPirateButtons() {
        return pirateButtons;
    }

    public void repaintCardButtons() {
        removeCardButtons();
        removePirateButtons();
        addCardButtons();
        addPirateButtons();
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
            btn.setBounds((i%16) * 23, size.height - 200 + (i/16 * 23), 22, 22);
            cardButtons.add(btn);
            this.add(btn);
            i++;
        }
        justStart = 0;
    }

    public void removePirateButtons() {
        if (justStart == 0) {
            for (int j = 0; j < pirateButtons.size(); j++) {
                this.remove(pirateButtons.get(j));
                pirateButtons.remove(j);
                j--;
            }
        }
    }

    public void addPirateButtons(){
        for(Pirate p: game.getCurrentPlayer().getPirates()){
            int index = p.getIndex();
            JButton btn = new JButton(""+index);
            btn.setBackground(colors.get(index));
            btn.setBounds(index*46,this.getPreferredSize().height-136,45,45);
            pirateButtons.add(btn);
            this.add(btn);
        }

    }

    public void setCardButtonsEnabled(boolean enable) {
        for (CardButton c : cardButtons)
            c.setEnabled(enable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void updatePlayerLabel(Player player) {
        currentPlayer.setForeground(new Colors().getColors().get(player.getIndex()));
        currentPlayer.setText(player.getName());
    }
}
