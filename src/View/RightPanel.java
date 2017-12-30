package View;

import Model.Game;
import Model.Symbol;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RightPanel extends JPanel {
    private JButton btn2;
    private CardPanelView cardPanelView;
    private ScoreBoardView scoreBoardView;
    private Map<Symbol, Image> cardMap;
    private Game game;

    public RightPanel(Game game) throws IOException {
        this.game = game;
        this.cardMap = new HashMap<>();
        setCardImages();
        this.btn2 = new JButton("selam");
        this.cardPanelView = new CardPanelView(game, cardMap);
        this.scoreBoardView = new ScoreBoardView(game);

        this.setPreferredSize(new Dimension(370,500));
        this.setBackground(new Color(236,204,180));
        this.add(scoreBoardView);
        createRightPanelComponents();
        this.add(cardPanelView);
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
        this.add(btn2);
        showCardsButton.addActionListener(e -> {
            if(cardPanelView.isVisible())
                cardPanelView.setVisible(false);
            else
                cardPanelView.setVisible(true);
        });
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public JButton getBtn2() {
        return btn2;
    }
}
