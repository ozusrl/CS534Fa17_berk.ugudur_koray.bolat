package View;

import Model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameView extends JPanel {
    private ArrayList<Color> colors;
    private Image seg_0;
    private Image seg_1;
    private Image seg_start;
    private Game game;
    private int numOfCells;
    private int cellSize;

    public GameView(Game game) throws IOException {
        seg_0 = ImageIO.read(new File("img/seg_02.png"));
        seg_1 = ImageIO.read(new File("img/seg_12.png"));
        seg_start = ImageIO.read(new File("img/seg_start2.png"));
        this.game = game;
        this.numOfCells = game.getBoard().getNumOfCells();
        this.cellSize = 48;
        this.setBackground(new Color(73, 204, 212));
        this.colors = new ArrayList<>();
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
        int segmentSize = game.getBoard().getNumOfSegments();
        g.drawImage(seg_start, 0, 0, 96, 288, this);
        for (int i = 0; i < segmentSize; i++) {
            int x = i * 96;
            int y = ((((i % 2) + 1) % 2) * 288);
            Image img = (i % 2 == 0) ? seg_0 : seg_1;
            g.drawImage(img, x, y, 192, 288, this);
        }
        paintSymbols(g);
        paintPirates(g);
    }

    private void paintSymbols(Graphics g) {
        for (Segment s : game.getBoard().getSegments()) {
            for (Cell c : s.getCells()) {
                Image image = getSymbolImage(c.getSymbol());
                g.drawImage(image, c.getX() + 24, c.getY() + 10, cellSize, cellSize, this);
            }
        }
    }

    private Image getSymbolImage(Symbol symbol) {
        Image image = null;
        try {
            image = ImageIO.read(new File("img/symbols/" + symbol.toString() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
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
                int y = getCellY(pirate.getCurrentCellIndex() + 3)+ 64;
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
