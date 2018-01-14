package View.RightPanel;

import Model.*;
import View.Button.CardButton;
import View.Button.SpecialButton;
import View.Constant.Colors;
import View.Constant.Values;
import View.Manager.ImageManager;
import View.Manager.PositionFinder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by berku on 6.1.2018.
 */
public class PlayPanel extends JPanel {
    private Game game;
    private boolean justStart;
    private ArrayList<Color> colors;
    private SpecialButton play;
    private SpecialButton forward;
    private SpecialButton backward;
    private SpecialButton skip;
    private JLabel currentCard;
    private JLabel currentPlayer;
    private JLabel currentPirate;
    private ImageManager imageManager;
    private PositionFinder positionFinder;
    private ArrayList<CardButton> cardButtons;
    private ArrayList<SpecialButton> pirateButtons;

    public PlayPanel(Game game, PositionFinder positionFinder) {
        this.setPreferredSize(Values.RIGHT_PANEL_DIMENSION);
        imageManager = ImageManager.getInstance();
        this.setBackground(new Color(244, 182, 141));
        this.setLayout(null);
        this.game = game;
        this.positionFinder = positionFinder;
        this.cardButtons = new ArrayList<>();
        this.pirateButtons = new ArrayList<>();
        this.justStart = true;
        this.colors = Colors.getColors();
        createComponents();
        setComponentsBounds();
        addComponents();
    }


    private void createComponents() {
        forward = new SpecialButton("img/button/forward.png");
        backward = new SpecialButton("img/button/backward.png");
        play = new SpecialButton("img/button/play.png");
        skip = new SpecialButton("img/button/skip.png");
        currentCard = new JLabel("Current Card:");
        currentPlayer = new JLabel(game.getCurrentPlayer().getName(), SwingConstants.CENTER);
        currentPirate = new JLabel("Current Pirate: ");
    }

    private void setComponentsBounds() {
        Dimension size = this.getPreferredSize();
        currentCard.setBounds(16, size.height - 110, 350, 20);
        currentCard.setForeground(new Color(0, 0, 0));
        currentPlayer.setBounds(0, Values.PADDING / 2, 350, 16);
        currentPirate.setBounds(16, size.height - 90, 200, 20);
        backward.setBounds(Values.PADDING, Values.PADDING * 2, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
        int forwardX = (Values.PADDING * 2 + Values.BUTTON_WIDTH);
        forward.setBounds(forwardX, Values.PADDING * 2, Values.BUTTON_WIDTH, Values.BUTTON_HEIGHT);
        play.setBounds(Values.PADDING, size.height - Values.PADDING - 48, 242, Values.BUTTON_HEIGHT * 3);
        skip.setBounds(240 + Values.PADDING * 2, size.height - Values.PADDING - 48, 60, Values.BUTTON_HEIGHT * 3);
    }

    private void addComponents() {
        this.add(currentCard);
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

    public void updateCurrentPirateLabel(Pirate pirate) {
        if (pirate == null) {
            currentPirate.setForeground(colors.get(0));
            currentPirate.setText("Current Pirate: Not selected");
        } else {

            currentPirate.setForeground(colors.get(pirate.getIndex()));
            currentPirate.setText("Current Pirate: " + pirate.getIndex());
        }
    }

    public void repaintCardButtons() {
        removeCards(cardButtons);
        removeCards(pirateButtons);
        addCardButtons();
        addPirateButtons();
    }

    public void addCardButtons() {
        int i = 0;
        Dimension size = this.getPreferredSize();
        System.out.println("cur:" + game.getCurrentPlayer().getIndex());
        for (Card c : game.getCurrentPlayer().getHand()) {
            Point position = positionFinder.getPositionOfCardOnPlayPanel(i);
            Image image = imageManager.getCardImage(c.getSymbol());
            int imgSize = Values.CARD_BUTTON_IMAGE_SIZE;
            image = image.getScaledInstance(imgSize, imgSize, Image.SCALE_DEFAULT);
            CardButton btn = new CardButton(c, image);
            btn.setBounds(position.x, position.y, imgSize + 2, imgSize + 2);
            cardButtons.add(btn);
            this.add(btn);
            i++;
        }
        justStart = false;
    }

    public void addPirateButtons() {
        for (Pirate p : game.getCurrentPlayer().getPirates()) {
            int index = p.getIndex();
            SpecialButton btn = new SpecialButton(colors.get(index));
            btn.setText("" + index);
            Point position = positionFinder.getPositionOfPirateOnPlayPanel(index);
            Dimension size = positionFinder.getSizeOfPirateOnPlayPanel();
            btn.setBounds(position.x, position.y, size.width, size.height);
            pirateButtons.add(btn);
            this.add(btn);
        }
    }

    public void setCardButtonsEnabled(boolean enable) {
        for (CardButton c : cardButtons)
            c.setEnabled(enable);
    }

    public void removeCards(ArrayList<?> buttons) {
        if (!justStart) {
            for (int j = 0; j < buttons.size(); j++) {
                this.remove((Component) buttons.get(j));
                buttons.remove(j);
                j--;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = imageManager.getRightBackgroundImage();
        g.drawImage(img, 0, 0, 350, 600, this);
    }

    public void updatePlayerLabel(Player player) {
        currentPlayer.setForeground(new Colors().getColors().get(player.getIndex()));
        currentPlayer.setText(player.getName());
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

    public JButton getSkip() {
        return skip;
    }

    public ArrayList<CardButton> getCardButtons() {
        return cardButtons;
    }

    public ArrayList<SpecialButton> getPirateButtons() {
        return pirateButtons;
    }
}
