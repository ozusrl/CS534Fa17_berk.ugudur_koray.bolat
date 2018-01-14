package View;

import Model.*;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameView extends JPanel {
    private ArrayList<Color> colors;
    private BoardView boardView;
    private PositionFinder positionFinder;
    private Game game;
    private Random rnd;

    public GameView(Game game) throws IOException {
        this.game = game;
        this.setBackground(new Color(73, 204, 212));
        this.positionFinder = new PositionFinder(game, this);
        this.boardView = new BoardView(game, this);
        colors = new Colors().getColors();
        this.rnd = new Random();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSea(g);
        boardView.paintComponent(g);
        paintPirates(g);
    }

    private void paintSea(Graphics g) {
        for (int i = 0; i <= getWidth() / 10; i++) {
            for (int j = 0; j < getHeight() / 10; j++) {
                g.setColor(new Color(79, 213, 222));
                g.fillRect((i * 10) + rnd.nextInt(8), (j * 10) + rnd.nextInt(8) * j, 4, 4);
                g.setColor(new Color(73, 197, 205));
                g.fillRect((i * 10) + rnd.nextInt(8), (j * 10) + rnd.nextInt(8) * j, 4, 4);
                g.setColor(new Color(81, 219, 228));
                g.fillRect((i * 10) + rnd.nextInt(8), (j * 10) + rnd.nextInt(8) * j, 2, 2);
            }
        }
    }

    private void paintPirates(Graphics g) {
        int startCellIndex = game.getBoard().getStartCell().getIndex();
        int endCellIndex = game.getBoard().getEndCell().getIndex();
        for (int i = startCellIndex; i <= endCellIndex; i++) {
            paintPiratesOnCell(g, i);
        }
    }

    private void paintPiratesOnCell(Graphics g, int cellIndex) {
        ArrayList<Pirate> pirates = game.getPiratesOnCell(cellIndex);
        for (Pirate pirate : pirates) {
            int location = pirates.indexOf(pirate);
            Player player = game.getPlayers().get(pirate.getPlayerIndex());
            Point position = positionFinder.getPositionOfPirate(pirate.getCurrentCellIndex(), location);
            paintPirate(g, player, pirate, position.x, position.y);
        }
    }

    private void paintPirate(Graphics g, Player player, Pirate pirate, int x, int y) {
        g.setColor(colors.get(pirate.getIndex()));
        int sizeOuter = Values.CELL_SIZE / 3;
        g.fillOval(x, y, sizeOuter, sizeOuter);
        g.setColor(colors.get(player.getIndex()));
        g.fillOval(x + 2, y + 2, sizeOuter - 4, sizeOuter - 4);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public PositionFinder getPositionFinder() {
        return positionFinder;
    }
}
