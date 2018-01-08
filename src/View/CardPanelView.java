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
        this.setPreferredSize(Values.RIGHT_PANEL_DIMENSION);
        this.setBackground(new Color(226, 170, 130));
        this.setLayout(null);
        setPlayerLabels();

    }

    protected void setPlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = new JLabel("PLAYER " + player.getIndex());
            label.setBounds(20, (player.getIndex() * 110) + 5, 150, 15);
            this.add(label);
            playerLabels.add(label);
        }
    }

    protected void updatePlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = playerLabels.get(player.getIndex());
            label.setText(player.getName() + " (" + player.getHand().size() + " cards)");
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
                Dimension cardDimension = Values.CARD_DIMENSION;
                g.drawImage(img, location.x, location.y, cardDimension.width, cardDimension.height, this);
                cardIndex++;
            }
        }
        updatePlayerLabels();
    }

    protected Point calculateLocationOfCard(int playerIndex, int cardIndex) {
        int column = (cardIndex % Values.MAX_CARD_ON_ROW);
        int x = Values.PADDING + column * Values.CARD_X_GAP;
        int yBase = (playerIndex * Values.RIGHT_PANEL_START_Y);
        int yJut = ((cardIndex % Values.Y_JUT) * Values.Y_JUT);
        int row = (Values.CARD_Y_BASE + (cardIndex / Values.MAX_CARD_ON_ROW * Values.CARD_Y_GAP));
        int y = yBase + yJut + row;
        return new Point(x, y);
    }
}
