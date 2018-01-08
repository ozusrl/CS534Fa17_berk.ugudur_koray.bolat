package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GameView extends JPanel {
    private ArrayList<Color> colors;
    private BoardView boardView;
    private Game game;
    private int numOfCells;

    public GameView(Game game) throws IOException {
        this.game = game;
        this.numOfCells = game.getBoard().getNumOfCells();
        this.setBackground(new Color(73, 204, 212));
        this.boardView = new BoardView(game);
        colors = new Colors().getColors();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardView.paintComponent(g);
        paintPirates(g);
        paintPiratesOnStartCell(g);
        paintPiratesOnEndCell(g);
    }

    private void paintPirates(Graphics g) {
        ArrayList<Cell> cells = game.getBoard().getAllCells();
        for (int i = 0; i < cells.size(); i++) {
            ArrayList<Pirate> pirates = game.getPiratesOnCell(cells.get(i).getIndex());
            for (int j = 0; j < pirates.size(); j++) {
                Pirate pirate = pirates.get(j);
                Player player = game.getPlayers().get(pirate.getPlayerIndex());
                int x = getCellX(pirate.getCurrentCellIndex() + 3) + 24;
                x = x + j * 16;
                int y = getCellY(pirate.getCurrentCellIndex() + 3) + 64;
                paintPirate(g, player, pirate, x, y);
            }
        }
    }

    private void paintPiratesOnStartCell(Graphics g) {
        ArrayList<Pirate> pirates = game.getPiratesOnCell(-1);
        for (int j = 0; j < pirates.size(); j++) {
            Pirate pirate = pirates.get(j);
            Player player = game.getPlayers().get(pirate.getPlayerIndex());
            int x = getCellX(pirate.getCurrentCellIndex() + 1) + 24;
            x = x + (j % 3) * 16;
            int y = getCellY(pirate.getCurrentCellIndex() + 1) + 24 + (18 * (j / 3));
            paintPirate(g, player, pirate, x, y);
        }
    }

    private void paintPiratesOnEndCell(Graphics g) {
        int plusIndex = (game.getBoard().getNumOfSegments() % 2 == 1) ? 5 : 3;
        ArrayList<Pirate> pirates = game.getPiratesOnCell(game.getBoard().getEndCell().getIndex());
        for (int j = 0; j < pirates.size(); j++) {
            Pirate pirate = pirates.get(j);
            Player player = game.getPlayers().get(pirate.getPlayerIndex());
            int x = getCellX(pirate.getCurrentCellIndex() + plusIndex) + 25;
            x = x + (j % 3) * 16;
            int y = getCellY(pirate.getCurrentCellIndex() + plusIndex) + 24 + (18 * (j / 3));
            paintPirate(g, player, pirate, x, y);
        }
    }

    private void paintPirate(Graphics g, Player player, Pirate pirate, int x, int y) {
        g.setColor(colors.get(pirate.getIndex()));
        int sizeOuter = Values.CELL_SIZE / 3;
        g.fillOval(x, y - 3, sizeOuter, sizeOuter);
        g.setColor(colors.get(player.getIndex()));
        //TODO: Magic number
        g.fillOval(x + 2, y - 1, 12, 12);
    }

    private int getCellX(int tempCellIndex) {
        int x = (tempCellIndex / numOfCells) * Values.CELL_SIZE * 2;
        return x;
    }

    private int getCellY(int tempCellIndex) {
        //TODO: Magic number
        int remaining = (tempCellIndex / numOfCells) % 2;
        int y;
        if (remaining == 0)
            y = (tempCellIndex % numOfCells) * Values.CELL_SIZE * 2;
        else
            y = Math.abs((tempCellIndex % numOfCells) - (numOfCells - 1)) * Values.CELL_SIZE * 2;
        return y;
    }

}
