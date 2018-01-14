package View.RightPanel;

import Model.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by berku on 30.12.2017.
 */
public class ScoreBoardView extends JPanel{
    public ScoreBoardView(Game game) {
        this.game = game;
        this.setBackground(new Color(24,50,60,0));
        this.setPreferredSize(new Dimension(350,50));
    }

    private Game game;
}
