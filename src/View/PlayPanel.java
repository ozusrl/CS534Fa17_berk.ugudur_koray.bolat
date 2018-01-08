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
    private SpecialButton forward;
    private SpecialButton backward;
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
        forward = new SpecialButton("img/button/forward.png");
        backward = new SpecialButton("img/button/backward.png");
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
        backward.setBounds(Values.PADDING, Values.PADDING, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
        int forwardX= (Values.PADDING)+(Values.PADDING/2)+Values.BUTTON_WIDTH;
        forward.setBounds(forwardX, Values.PADDING, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
        play.setBounds(0, size.height - 50, size.width - 75, 50);
        skip.setBounds(size.width - 75, size.height - 50, 75, 50);


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

    public void updateCurrentPirateLabel(Pirate pirate) {
        if (pirate == null) {
            currentPirate.setForeground(new Colors().getColors().get(0));
            currentPirate.setText("Current Pirate: Not selected");
        } else {

            currentPirate.setForeground(new Colors().getColors().get(pirate.getIndex()));
            currentPirate.setText("Current Pirate: " + pirate.getIndex());
        }
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
            int x = Values.PADDING +(i % 7) * 45;
            int y = 40 + Values.BUTTON_HEIGHT + Values.PADDING * 3 + (i / 7 * 45);
            CardButton btn = new CardButton(c, cardMap.get(c.getSymbol()).getScaledInstance(40,40,Image.SCALE_DEFAULT));
            btn.setBounds(x, y, 42, 42);
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

    public void addPirateButtons() {
        for (Pirate p : game.getCurrentPlayer().getPirates()) {
            int index = p.getIndex();
            JButton btn = new JButton("" + index);
            btn.setBackground(colors.get(index));
            int width = (this.getPreferredSize().width - Values.PADDING*2)/game.getNumOfPirates();
            int x = Values.PADDING + (index * (width));
            int y = Values.PADDING*2 + Values.BUTTON_HEIGHT;
            btn.setBounds(x, y, width, 40);
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
