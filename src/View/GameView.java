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
    private Game game;
    private int numOfCells;

    public GameView(Game game) throws IOException {
        this.game = game;
        this.numOfCells = game.getBoard().getNumOfCells();
        this.setBackground(new Color(73, 204, 212));
        this.boardView = new BoardView(game, this);
        colors = new Colors().getColors();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSea(g);
        System.out.println("gameView size: " + getSize());
        boardView.paintComponent(g);
        paintPirates(g);
        paintPiratesOnStartCell(g);
        paintPiratesOnEndCell(g);
    }

    private void paintSea(Graphics g) {
        Random rnd = new Random();
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
        ArrayList<Cell> cells = new ArrayList<>();
        for(Segment segment: game.getBoard().getSegments())
            for(Cell cell: segment.getCells())
                cells.add(cell);

        for (int i = 0; i < cells.size(); i++) {
            ArrayList<Pirate> pirates = game.getPiratesOnCell(cells.get(i).getIndex());
            for (int j = 0; j < pirates.size(); j++) {
                Pirate pirate = pirates.get(j);
                Player player = game.getPlayers().get(pirate.getPlayerIndex());
                int x = boardView.getXPositionOfCell(pirate.getCurrentCellIndex()) + 24;
                x = x + j * 16;
                int y = boardView.getYPositionOfCell(pirate.getCurrentCellIndex()) + 64;
                paintPirate(g, player, pirate, x, y);
            }
        }
    }

    private void paintPiratesOnStartCell(Graphics g) {
        ArrayList<Pirate> pirates = game.getPiratesOnCell(-1);
        for (int j = 0; j < pirates.size(); j++) {
            Pirate pirate = pirates.get(j);
            Player player = game.getPlayers().get(pirate.getPlayerIndex());
            int x = boardView.getXPositionOfCell(pirate.getCurrentCellIndex()) + 24;
            x = x + (j % 3) * 16;
            int y = boardView.getYPositionOfCell(pirate.getCurrentCellIndex()) + 24 + (16 * (j / 3));
            paintPirate(g, player, pirate, x, y);
        }
    }

    private void paintPiratesOnEndCell(Graphics g) {
        ArrayList<Pirate> pirates = game.getPiratesOnCell(game.getBoard().getEndCell().getIndex());
        for (int j = 0; j < pirates.size(); j++) {
            Pirate pirate = pirates.get(j);
            Player player = game.getPlayers().get(pirate.getPlayerIndex());
            int x = boardView.getXPositionOfCell(pirate.getCurrentCellIndex()) + 25;
            x = x + (j % 3) * 16;
            int y = boardView.getYPositionOfCell(pirate.getCurrentCellIndex()) + 24 + (18 * (j / 3));
            paintPirate(g, player, pirate, x, y);
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
}
