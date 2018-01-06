package Controller;

import Model.Card;
import Model.Game;
import Model.Pirate;
import Model.Symbol;
import View.CardButton;
import View.MainFrame;
import View.PlayPanel;

/**
 * Created by berku on 6.1.2018.
 */
public class GameController {
    private MainFrame mainFrame;
    private PlayPanel playPanel;
    private Card chosenCard;
    private Game game;

    public GameController(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
        this.playPanel = mainFrame.getRightPanel().getPlayPanel();
        setButtonsForward();
        addDirectionListeners();
        addPlayListener();
        addCardListeners();
    }

    private void addDirectionListeners() {
        playPanel.getBackward().addActionListener(e -> {
            setButtonsBackward();
        });
        playPanel.getForward().addActionListener(e -> {
            setButtonsForward();
        });
    }

    private void setButtonsBackward() {
        playPanel.getBackward().setEnabled(true);
        playPanel.getForward().setEnabled(false);
        playPanel.setCardButtonsEnabled(false);
    }

    private void setButtonsForward() {
        playPanel.getForward().setEnabled(false);
        playPanel.getBackward().setEnabled(true);
        playPanel.setCardButtonsEnabled(true);
    }

    private void addPlayListener() {
        playPanel.getPlay().addActionListener(e -> {
            Pirate pirate =game.getPlayers().get(2).getPirates().get(0);
            if (!playPanel.getForward().isEnabled()) {
                game.moveForward(pirate, chosenCard);
            } else {
                game.moveBackward(game.getPlayers().get(2).getPirates().get(0));
            }
            game.switchToNextPlayer();
            mainFrame.repaint();
            playPanel.repaintCardButtons();
            setButtonsForward();
            addCardListeners();
        });
    }

    private void addCardListeners() {
        for (CardButton c : playPanel.getCardButtons()) {
            System.out.println(c.getCard().toString());
            c.addActionListener(e -> {
                chosenCard = c.getCard();
                playPanel.getCurrentCard().setText("Current card: " + c.getCard().getSymbol().toString());
            });
        }
    }
}
