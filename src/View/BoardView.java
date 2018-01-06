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
import java.util.HashMap;
import java.util.Map;

/**
 * Created by berku on 30.12.2017.
 */
public class BoardView extends JPanel {
    private Game game;
    private Map<Symbol, Image> imageMap;
    private Image seg_0;
    private Image seg_1;
    private Image seg_start;
    private int cellSize;
    public BoardView(Game game) throws IOException {
        this.game = game;
        this.imageMap = new HashMap<>();
        cellSize = Values.CELL_SIZE;
        setImages();
    }

    private void setImages() throws IOException {
        seg_0 = ImageIO.read(new File("img/seg_02.png"));
        seg_1 = ImageIO.read(new File("img/seg_12.png"));
        seg_start = ImageIO.read(new File("img/seg_start2.png"));
        Symbol[] symbols = Symbol.values();
        for (Symbol symbol : symbols) {
            imageMap.put(symbol, ImageIO.read(new File("img/symbols/" + symbol.toString() + ".png")));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSegments(g);
        paintSymbols(g);
    }

    protected void paintSegments(Graphics g) {
        int segmentSize = game.getBoard().getNumOfSegments();
        g.drawImage(seg_start, 0, 0, cellSize * 2, cellSize*6, this);
        for (int i = 0; i < segmentSize; i++) {
            int x = i * cellSize * 2;
            int y = ((((i % 2) + 1) % 2) * cellSize*6);
            Image img = (i % 2 == 0) ? seg_0 : seg_1;
            g.drawImage(img, x, y, cellSize * 4, cellSize*6, this);
        }
    }

    protected void paintSymbols(Graphics g) {

        for (Segment s : game.getBoard().getSegments()) {
            for (Cell c : s.getCells()) {
                Image image = imageMap.get(c.getSymbol());
                int x = c.getX() + (Values.CELL_SIZE / 2);
                int y = c.getY() + (Values.CELL_SIZE / 4);
                g.drawImage(image, x, y, cellSize, cellSize, this);
            }
        }
    }
}