package View;

import Model.Game;
import Model.Symbol;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RightPanel extends JPanel {
    private CardPanelView cardPanelView;
    private ScoreBoardView scoreBoardView;
    private PlayPanel playPanel;
    private Map<Symbol, Image> cardMap;
    private Game game;

    public RightPanel(Game game) throws IOException {
        this.game = game;
        this.cardMap = new HashMap<>();
        setCardImages();
        this.cardPanelView = new CardPanelView(game, cardMap);
        this.scoreBoardView = new ScoreBoardView(game);
        this.playPanel = new PlayPanel(game, cardMap);
        this.setPreferredSize(new Dimension(370,500));
        this.setBackground(new Color(73, 204, 212));
        this.add(scoreBoardView);
        createRightPanelComponents();
        this.add(cardPanelView);
        this.add(playPanel);
        playPanel.setVisible(false);
    }
    private void setCardImages() throws IOException {
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            cardMap.put(symbol, ImageIO.read(new File("img/cards/" + symbol.toString() + ".png")));
        }
    }

    private void createRightPanelComponents() {
        SpecialButton showCardsButton = new SpecialButton("img/button/showcards.png");

        this.add(showCardsButton);
        showCardsButton.addActionListener(e -> {
            if(cardPanelView.isVisible()){
                cardPanelView.setVisible(false);
                playPanel.setVisible(true);
            }
            else{
                cardPanelView.setVisible(true);
                playPanel.setVisible(false);
            }

        });
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }


    public PlayPanel getPlayPanel(){
        return playPanel;
    }
}
