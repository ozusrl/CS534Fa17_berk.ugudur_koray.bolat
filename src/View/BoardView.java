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
    private int targetCell;
    private boolean isTargeted;
    private Graphics g;
    private GameView gameView;

    public BoardView(Game game, GameView gameView) throws IOException {
        this.game = game;
        this.imageMap = new HashMap<>();
        this.gameView = gameView;
        cellSize = Values.CELL_SIZE;
        targetCell = -1;
        setImages();
    }

    private void setImages() throws IOException {
        System.out.println("Image load completed.");
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
        System.out.println("boardView size: " + getSize());
        System.out.println("gameView2 size: " + gameView.getSize());
        this.g = g;
        paintSegments(g);
        paintSymbols(g);
        if (isTargeted) {
            paintTargetCell(targetCell);
        }
    }

    private void paintTargetCell(int cellIndex) {
        g.setColor(Values.TARGET_COLOR);
        int x = getXPositionOfCell(cellIndex);
        int y = getYPositionOfCell(cellIndex);
        g.fillOval(x + 20, y + 8, 54, 54);
    }

    protected void paintSegments(Graphics g) {
        int numOfSegments = game.getBoard().getNumOfSegments();
        int yBase = getYBasePosition();
        int xBase = getXBasePosition();

        // Draw start segment
        g.drawImage(seg_start, xBase, yBase, Values.SEG_SINGLE_WIDTH, Values.SEG_HEIGHT, this);

        // Draw default segments
        for (int i = 0; i < numOfSegments; i++) {
            int xSegment = i * Values.SEG_SINGLE_WIDTH;
            int ySegment = (((i + 1) % 2) * Values.SEG_HEIGHT);
            int x = xBase + xSegment;
            int y = yBase + ySegment;
            g.drawImage(getSegmentImage(i), x, y, Values.SEG_WIDTH, Values.SEG_HEIGHT, this);
        }

        // Draw end segment
        int xEndSegment = gameView.getWidth() - xBase - Values.SEG_SINGLE_WIDTH;
        int yEndSegment = (numOfSegments % 2 == 1) ? yBase : yBase + (Values.SEG_HEIGHT);
        g.drawImage(seg_start, xEndSegment, yEndSegment, Values.SEG_SINGLE_WIDTH, Values.SEG_HEIGHT, this);

    }


    public Image getSegmentImage(int index) {
        return (index % 2 == 0) ? seg_0 : seg_1;
    }

    protected void paintSymbols(Graphics g) {
        for (Segment s : game.getBoard().getSegments()) {
            for (Cell c : s.getCells()) {
                Image image = imageMap.get(c.getSymbol());
                int x = getXPositionOfCell(c.getIndex()) + (Values.CELL_SIZE / 2);
                int y = getYPositionOfCell(c.getIndex()) + (Values.CELL_SIZE / 4);
                g.drawImage(image, x, y, cellSize, cellSize, this);
            }
        }
    }

    public int getXPositionOfCell(int cellIndex) {
        int tempCellIndex = cellIndex + calculateIndexGap(cellIndex);
        int xBase = getXBasePosition();
        int x = xBase + (tempCellIndex / game.getBoard().getNumOfCells()) * Values.SEG_SINGLE_WIDTH;
        return x;
    }

    public int getYPositionOfCell(int cellIndex) {
        int tempCellIndex = cellIndex + calculateIndexGap(cellIndex);
        int yBase = getYBasePosition();
        int numOfCells = game.getBoard().getNumOfCells();
        int remaining = (tempCellIndex / numOfCells) % 2;
        int y;
        if (remaining == 0)
            y = yBase + (tempCellIndex % numOfCells) * Values.SEG_SINGLE_WIDTH;
        else
            y = yBase + Math.abs((tempCellIndex % numOfCells) - (numOfCells - 1)) * Values.SEG_SINGLE_WIDTH;
        return y;
    }

    public int getXBasePosition() {
        int numOfSegments = game.getBoard().getNumOfSegments();
        int numOfSingleColumns = numOfSegments + 1;
        return (gameView.getWidth() - (Values.SEG_SINGLE_WIDTH * numOfSingleColumns)) / 2;
    }

    public int getYBasePosition() {
        return (gameView.getHeight() - Values.SEG_HEIGHT * 2) / 2;
    }

    public int calculateIndexGap(int cellIndex) {
        if (cellIndex == -1)
            return Values.START_INDEX_GAP;
        if (cellIndex == game.getBoard().getCells().size())
            return isNumOfSegmentsOdd() ? Values.END_ODD_INDEX_GAP : Values.END_EVEN_INDEX_GAP;
        else
            return Values.DEFAULT_INDEX_GAP;
    }

    public boolean isNumOfSegmentsOdd() {
        return (game.getBoard().getNumOfSegments() % 2 == 1);
    }


    public int getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(int targetCell) {
        this.targetCell = targetCell;
    }

    public boolean isTargeted() {
        return isTargeted;
    }

    public void setTargeted(boolean targeted) {
        isTargeted = targeted;
    }
}
