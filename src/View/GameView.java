package View;

import Model.Cell;
import Model.Game;
import Model.Segment;
import Model.Symbol;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends JPanel {
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int segmentSize = 6;
        g.drawImage(seg_start, 0, 0, 96, 288, this);
        for (int i = 0; i < segmentSize; i++) {
            int x = i * 96;
            int y = ((((i % 2) + 1) % 2) * 288);
            Image img = (i % 2 == 0) ? seg_0 : seg_1;
            g.drawImage(img, x, y, 192, 288, this);
        }
        paintSymbols(g);
    }

    private void paintSymbols(Graphics g) {
        int cellIndex = 0;
        for (Segment s : game.getBoard().getSegments()) {
            for (Cell c : s.getCells()) {
                Symbol symbol = c.getSymbol();
                int tempCellIndex = (cellIndex + (numOfCells / 2));
                int x = calculateXPositionOfSymbol(tempCellIndex);
                int y = calculateYPositionOfSymbol(tempCellIndex, x);
                g.drawImage(getSymbolImage(symbol), x, y, cellSize, cellSize, this);
                cellIndex++;
            }
        }
    }

    private int calculateXPositionOfSymbol(int tempCellIndex){
        int x = 24 + (tempCellIndex / numOfCells) * cellSize * 2;
        return x;
    }

    private int calculateYPositionOfSymbol(int tempCellIndex, int x) {
        int remaining = (tempCellIndex / numOfCells) % 2;
        int y;
        if (remaining == 0)
            y = 10 + (tempCellIndex % numOfCells) * cellSize * 2;
        else
            y = 10 + Math.abs((tempCellIndex % numOfCells) - (numOfCells - 1)) * cellSize * 2;
        return y;
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

}
