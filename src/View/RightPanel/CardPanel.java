package View.RightPanel;

import Model.Card;
import Model.Game;
import Model.Player;
import View.Constant.Colors;
import View.Manager.ImageManager;
import View.Manager.PositionFinder;
import View.Constant.Values;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    private Game game;
    private ImageManager imageManager;
    private PositionFinder positionFinder;
    private ArrayList<JLabel> playerLabels;

    public CardPanel(Game game, PositionFinder positionFinder) {
        this.game = game;
        this.positionFinder = positionFinder;
        imageManager = ImageManager.getInstance();
        this.playerLabels = new ArrayList<>();
        this.setPreferredSize(Values.RIGHT_PANEL_DIMENSION);
        this.setBackground(new Color(226, 170, 130));
        this.setLayout(null);
        setPlayerLabels();
    }

    protected void setPlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = new JLabel("PLAYER " + player.getIndex());
            label.setBounds(20, (player.getIndex() * 110) + 16, 150, 15);
            this.add(label);
            playerLabels.add(label);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackgroundElements(g);
        paintCards(g);
        updatePlayerLabels();
    }

    protected void paintBackgroundElements(Graphics g){
        g.drawImage(imageManager.getRightBackgroundImage(),0,0,350,600,this);
    }

    protected void paintCards(Graphics g){
        for (Player player : game.getPlayers()) {
            int cardIndex = 0;
            for (Card card : player.getHand()) {
                Image cardImg = imageManager.getCardImage(card.getSymbol());
                Point location = positionFinder.getPositionOfCardOnCardPanel(player.getIndex(), cardIndex);
                Dimension cardDimension = Values.CARD_DIMENSION;
                g.drawImage(cardImg, location.x, location.y, cardDimension.width, cardDimension.height, this);
                cardIndex++;
            }
        }
    }

    protected void updatePlayerLabels() {
        for (Player player : game.getPlayers()) {
            JLabel label = playerLabels.get(player.getIndex());
            label.setText(player.getName() + " (" + player.getHand().size() + " cards)");
            label.setForeground(Colors.getColors().get(player.getIndex()));
        }
    }

}
