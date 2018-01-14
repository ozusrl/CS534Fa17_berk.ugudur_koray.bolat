package View;

import Model.Cell;
import Model.Game;
import Model.Segment;
import Model.Symbol;
import com.sun.corba.se.impl.orbutil.graph.Graph;

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
    private ImageManager imageManager;
    private PositionFinder positionFinder;
    private int targetCell;
    private boolean isTargeted;
    private GameView gameView;

    public BoardView(Game game, GameView gameView) throws IOException {
        this.game = game;
        this.imageManager = ImageManager.getInstance();
        this.gameView = gameView;
        this.positionFinder = gameView.getPositionFinder();
        targetCell = -1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintSegments(g);
        paintSymbols(g);
        paintTargetCell(g, targetCell);
    }

    private void paintSegments(Graphics g) {
        int numOfSegments = game.getBoard().getNumOfSegments();
        Point basePos = positionFinder.getBasePoint();

        // Draw start segment
        Image startSegImg = imageManager.getStartSegmentImage();
        g.drawImage(startSegImg, basePos.x, basePos.y, Values.SEG_SINGLE_WIDTH, Values.SEG_HEIGHT, this);

        // Draw default segments
        for (int i = 0; i < numOfSegments; i++) {
            Point segPos = positionFinder.getPositionOfSegment(i);
            Image segImg = imageManager.getSegmentImage(i);
            g.drawImage(segImg, segPos.x, segPos.y, Values.SEG_WIDTH, Values.SEG_HEIGHT, this);
        }

        // Draw end segment
        Point endSegPos = positionFinder.getPositionOfSegment(numOfSegments);
        Image endSegmentImage = imageManager.getEndSegmentImage();
        g.drawImage(endSegmentImage, endSegPos.x, endSegPos.y, Values.SEG_SINGLE_WIDTH, Values.SEG_HEIGHT, this);
    }

    private void paintSymbols(Graphics g) {
        for (Segment s : game.getBoard().getSegments()) {
            for (Cell c : s.getCells()) {
                Image image = imageManager.getSymbolImage(c.getSymbol());
                Point position = positionFinder.getPositionOfSymbolOnCell(c.getIndex());
                g.drawImage(image, position.x, position.y, Values.CELL_SIZE, Values.CELL_SIZE, this);
            }
        }
    }

    private void paintTargetCell(Graphics g, int cellIndex) {
        if (isTargeted) {
            g.setColor(Values.TARGET_COLOR);
            Point position = positionFinder.getPositionOfCell(cellIndex);
            g.fillOval(position.x + 20, position.y + 8, 54, 54);
        }
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
