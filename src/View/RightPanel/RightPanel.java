package View.RightPanel;

import Model.Game;
import Model.Symbol;
import View.Button.SpecialButton;
import View.Manager.PositionFinder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RightPanel extends JPanel {
    private CardPanel cardPanel;
    private ScoreBoardView scoreBoardView;
    private PlayPanel playPanel;
    private Game game;
    private SpecialButton showCardsButton;
    private PositionFinder positionFinder;

    public RightPanel(Game game, PositionFinder positionFinder) throws IOException {
        this.game = game;
        this.cardPanel = new CardPanel(game, positionFinder);
        this.scoreBoardView = new ScoreBoardView(game);
        this.playPanel = new PlayPanel(game, positionFinder);
        this.setPreferredSize(new Dimension(370, 500));
        this.setBackground(new Color(73, 204, 212));
        this.showCardsButton = new SpecialButton("img/button/showcards.png");
        this.add(scoreBoardView);
        createRightPanelComponents();
        this.add(cardPanel);
        this.add(playPanel);

        playPanel.setVisible(false);
    }

    private void createRightPanelComponents() {
        this.add(showCardsButton);
        showCardsButton.addActionListener(e -> {
            if (cardPanel.isVisible()) {
                cardPanel.setVisible(false);
                playPanel.setVisible(true);
            } else {
                cardPanel.setVisible(true);
                playPanel.setVisible(false);
            }
        });
    }

    public void setRightPanel(boolean isMyTurn) {
        if (isMyTurn) {
            cardPanel.setVisible(false);
            playPanel.setVisible(true);
            showCardsButton.setEnabled(true);

        } else {
            showCardsButton.setEnabled(false);
            cardPanel.setVisible(true);
            playPanel.setVisible(false);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public PlayPanel getPlayPanel() {
        return playPanel;
    }
}
