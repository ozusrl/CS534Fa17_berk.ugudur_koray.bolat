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
        this.colors = new ArrayList<>();
        this.boardView = new BoardView(game);
        setColors();
    }

    private void setColors() {
        colors.add(new Color(0, 0, 0));
        colors.add(new Color(255, 255, 255));
        colors.add(new Color(255, 0, 0));
        colors.add(new Color(0, 255, 0));
        colors.add(new Color(0, 0, 255));
        colors.add(new Color(122, 100, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        boardView.paintComponent(g);
        paintPirates(g);
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

    private void paintPirate(Graphics g, Player player, Pirate pirate, int x, int y) {
        g.setColor(colors.get(pirate.getIndex()));
        g.fillOval(x, y - 3, 16, 16);
        g.setColor(colors.get(player.getIndex()));
        g.fillOval(x + 3, y, 10, 10);
    }

    private int getCellX(int tempCellIndex) {
        //TODO: Magic number
        int x = (tempCellIndex / numOfCells) * 48 * 2;
        return x;
    }

    private int getCellY(int tempCellIndex) {
        //TODO: Magic number
        int remaining = (tempCellIndex / numOfCells) % 2;
        int y;
        if (remaining == 0)
            y = (tempCellIndex % numOfCells) * 48 * 2;
        else
            y = Math.abs((tempCellIndex % numOfCells) - (numOfCells - 1)) * 48 * 2;
        return y;
    }

}
