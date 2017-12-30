package View;

import Model.Card;
import Model.Game;
import Model.Player;
import Model.Symbol;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class CardPanelView extends JPanel {
    private Game game;
    private Map<Symbol, Image> cardMap;
    private ArrayList<JLabel> playerLabels;

    public CardPanelView(Game game, Map<Symbol, Image> cardMap) {
        this.game = game;
        this.cardMap = cardMap;
        this.playerLabels = new ArrayList<>();
        this.setPreferredSize(new Dimension(350, 600));
        this.setBackground(new Color(226, 170, 130));
        this.setLayout(null);
        setPlayerLabels();

    }

    protected void setPlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = new JLabel("PLAYER " + player.getIndex());
            label.setBounds(20, (player.getIndex() * 110) + 5, 150, 10);
            this.add(label);
            playerLabels.add(label);
        }
    }

    protected void updatePlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = playerLabels.get(player.getIndex());
            label.setText("PLAYER " + player.getIndex() + " (" + player.getHand().size() + " cards)");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Player player : game.getPlayers()) {
            int cardIndex = 0;
            for (Card card : player.getHand()) {
                Image img = cardMap.get(card.getSymbol());
                Point location = calculateLocationOfCard(player.getIndex(), cardIndex);
                g.drawImage(img, location.x, location.y, 40, 40, this);
                cardIndex++;
            }
        }
        updatePlayerLabels();
    }

    protected Point calculateLocationOfCard(int playerIndex, int cardIndex) {
        int x = (cardIndex % 10) * 32;
        int y = (playerIndex * 110) + ((cardIndex % 2) * 2) + (20 + (cardIndex / 10 * 45));
        return new Point(x, y);
    }
}
