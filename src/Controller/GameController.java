package Controller;

import Model.Card;
import Model.Game;
import Model.Pirate;
import Model.Player;
import Network.Command;
import Network.MoveBackward;
import Network.MoveForward;
import Network.SkipTurn;
import View.GamePanel.BoardPanel;
import View.Button.CardButton;
import View.MainFrame;
import View.RightPanel.PlayPanel;

import javax.swing.*;

/**
 * Created by berku on 6.1.2018.
 */
public class GameController {
    private MainFrame mainFrame;
    private PlayPanel playPanel;
    private BoardPanel boardPanel;
    private Card chosenCard;
    private Pirate chosenPirate;
    private Game game;
    private Command lastCommand;

    public GameController(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.boardPanel = mainFrame.getGamePanel().getBoardPanel();
        this.game = game;
        this.playPanel = mainFrame.getRightPanel().getPlayPanel();
        this.lastCommand = null;
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
        playPanel.getBackward().setEnabled(false);
        playPanel.getForward().setEnabled(true);
        playPanel.setCardButtonsEnabled(false);
        setNullCardAndPirate();
        playPanel.getPlay().setEnabled(false);

        for (JButton btn : playPanel.getPirateButtons()) {
            Pirate pirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(btn.getText()));
            boolean canMove = game.getAvailableCellIndexOnBackward(pirate) != pirate.getCurrentCellIndex();
            boolean isFinished = pirate.getCurrentCellIndex() == game.getBoard().getEndCell().getIndex();
            btn.setEnabled(canMove && !isFinished);
        }
    }

    private void setForwardDirection() {
        playPanel.getForward().setEnabled(false);
        playPanel.getBackward().setEnabled(true);
        playPanel.setCardButtonsEnabled(true);
        setNullCardAndPirate();
        playPanel.getPlay().setEnabled(false);

        for (JButton btn : playPanel.getPirateButtons()) {
            Pirate pirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(btn.getText()));
            btn.setEnabled(!(pirate.getCurrentCellIndex() == game.getBoard().getEndCell().getIndex()));
        }
    }

    private void addPlayButtonListener() {
        playPanel.getPlay().addActionListener(e -> {
            Player player = game.getCurrentPlayer();

            Pirate pirate = chosenPirate;
            if (!playPanel.getForward().isEnabled()) {
                lastCommand = new MoveForward(pirate, chosenCard);
                game.moveForward(pirate, chosenCard);
            } else {
                lastCommand = new MoveBackward(pirate);
                game.moveBackward(pirate);
            }
            switchTurnRoutine();
        });
    }

    public void switchTurnRoutine() {
        game.switchTurn();
        boardPanel.setTargeted(false);
        playPanel.repaintCardButtons();
        addCardButtonsListeners();
        addPirateButtonsListeners();
        resetChosenCard();
        setSkipButton();
        setNullCardAndPirate();
        playPanel.updatePlayerLabel(game.getCurrentPlayer());
        setForwardDirection();
        if (game.isFinished()) {
            JOptionPane.showMessageDialog(mainFrame, "GAMEOVER, WINNER: " + game.getWinner().getName());
            mainFrame.dispose();
            System.exit(0);
        }
        mainFrame.repaint();
    }


    private void setNullCardAndPirate() {
        chosenCard = null;
        chosenPirate = null;
        playPanel.getCurrentCard().setText("Current Card: Not Selected");
        playPanel.updateCurrentPirateLabel(null);
        chosenPirate = null;
    }

    private void addCardButtonsListeners() {
        for (CardButton c : playPanel.getCardButtons()) {
            c.addActionListener(e -> {
                chosenCard = c.getCard();
                playPanel.getCurrentCard().setText("Current card: " + c.getCard().getSymbol().toString());
                if (chosenPirate != null) {
                    playPanel.getPlay().setEnabled(true);
                    int cellIndex = game.getAvailableCellIndexOnForward(chosenPirate, chosenCard);
                    setTargetCell(cellIndex);
                    mainFrame.repaint();
                }
            });
        }
    }

    private void addPirateButtonsListeners() {
        for (JButton c : playPanel.getPirateButtons()) {
            c.addActionListener(e -> {
                chosenPirate = game.getCurrentPlayer().getPirates().get(Integer.parseInt(c.getText()));
                // playPanel.getCurrentCard().setText("Current card: " + c.getCard().getSymbol().toString());
                playPanel.updateCurrentPirateLabel(chosenPirate);
                if (chosenCard != null || !playPanel.getBackward().isEnabled()) {
                    playPanel.getPlay().setEnabled(true);
                    int cellIndex;
                    if (!playPanel.getBackward().isEnabled())
                        cellIndex = game.getAvailableCellIndexOnBackward(chosenPirate);
                    else
                        cellIndex = game.getAvailableCellIndexOnForward(chosenPirate, chosenCard);
                    setTargetCell(cellIndex);
                    mainFrame.repaint();
                }
            });
        }
    }

    private void setTargetCell(int cellIndex) {
        boardPanel.setTargeted(true);
        boardPanel.setTargetCell(cellIndex);
    }

    private void addSkipButtonListener() {
        playPanel.getSkip().addActionListener(e -> {
            lastCommand = new SkipTurn();
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

    public Command getLastCommand() {
        return lastCommand;
    }

    public void setLastCommand(Command lastCommand) {
        this.lastCommand = lastCommand;
    }

}
