package Controller;

import Model.Card;
import Model.Game;
import Model.Pirate;
import Model.Player;
import View.CardButton;
import View.MainFrame;
import View.PlayPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by berku on 6.1.2018.
 */
public class GameController {
    private MainFrame mainFrame;
    private PlayPanel playPanel;
    private Card chosenCard;
    private Pirate chosenPirate;
    private Game game;

    public GameController(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
        this.playPanel = mainFrame.getRightPanel().getPlayPanel();
        init();
    }

    private void init() {
        setForwardDirection();
        addDirectionListeners();
        addPlayButtonListener();
        addSkipButtonListener();
        playPanel.addCardButtons();
        playPanel.addPirateButtons();
        playPanel.getPlay().setEnabled(false);
        playPanel.getSkip().setEnabled(false);
        addCardButtonsListeners();
        addPirateButtonsListeners();

    }

    private void addDirectionListeners() {
        playPanel.getBackward().addActionListener(e -> {
            setBackwardDirection();
        });
        playPanel.getForward().addActionListener(e -> {
            setForwardDirection();
        });
    }

    private void setBackwardDirection() {
        playPanel.updateCurrentDirection("backward");
        playPanel.getBackward().setEnabled(false);
        playPanel.getForward().setEnabled(true);
        playPanel.setCardButtonsEnabled(false);
        playPanel.updateCurrentPirateLabel(null);
        chosenPirate = null;
        if (chosenPirate != null)
            playPanel.getPlay().setEnabled(true);
        else
            playPanel.getPlay().setEnabled(false);

        for(JButton btn: playPanel.getPirateButtons()){
            Pirate pirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(btn.getText()));
            boolean isMostBack = game.isMostBack(pirate);
            boolean isFinished = pirate.getCurrentCellIndex()==game.getBoard().getEndCell().getIndex();
            btn.setEnabled(!isMostBack && !isFinished);
        }
    }

    private void setForwardDirection() {
        playPanel.updateCurrentDirection("forward");
        playPanel.getForward().setEnabled(false);
        playPanel.getBackward().setEnabled(true);
        playPanel.setCardButtonsEnabled(true);
        if (chosenCard != null && chosenPirate != null)
            playPanel.getPlay().setEnabled(true);
        else
            playPanel.getPlay().setEnabled(false);

        for(JButton btn: playPanel.getPirateButtons()){
            Pirate pirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(btn.getText()));
            btn.setEnabled(!(pirate.getCurrentCellIndex()==game.getBoard().getEndCell().getIndex()));
        }
    }
    private void addPlayButtonListener() {
        playPanel.getPlay().addActionListener(e -> {
            Player player = game.getCurrentPlayer();

            Pirate pirate = chosenPirate;
            if (!playPanel.getForward().isEnabled()) {
                game.moveForward(pirate, chosenCard);
            } else {
                game.moveBackward(pirate);
            }
            switchTurnRoutine();
        });
    }

    private void switchTurnRoutine() {
        game.switchTurn();
        mainFrame.repaint();
        playPanel.repaintCardButtons();
        addCardButtonsListeners();
        addPirateButtonsListeners();
        resetChosenCard();
        setSkipButton();
        playPanel.updatePlayerLabel(game.getCurrentPlayer());
        setForwardDirection();
    }

    private void addCardButtonsListeners() {
        for (CardButton c : playPanel.getCardButtons()) {
            c.addActionListener(e -> {
                chosenCard = c.getCard();
                playPanel.getCurrentCard().setText("Current card: " + c.getCard().getSymbol().toString());
                setForwardDirection();
            });
        }
    }
    private void addPirateButtonsListeners() {
        for (JButton c : playPanel.getPirateButtons()) {
            c.addActionListener(e -> {
                chosenPirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(c.getText()));
               // playPanel.getCurrentCard().setText("Current card: " + c.getCard().getSymbol().toString());
                playPanel.updateCurrentPirateLabel(chosenPirate);
                playPanel.getPlay().setEnabled(true);
            });
        }
    }
    private void addSkipButtonListener() {
        playPanel.getSkip().addActionListener(e -> {
            switchTurnRoutine();
        });
    }

    private void setSkipButton() {
        if (game.getTurnNumber() == 1)
            playPanel.getSkip().setEnabled(true);
        else
            playPanel.getSkip().setEnabled(false);
    }

    private void resetChosenCard() {
        chosenCard = null;
    }
}
