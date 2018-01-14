package View;

import Model.Game;

import java.awt.*;

/**
 * Created by berku on 14.1.2018.
 */
public class PositionFinder {
    private Game game;
    private GameView gameView;

    public PositionFinder(Game game, GameView gameView) {
        this.gameView = gameView;
        this.game = game;
    }

    public Point getPositionOfPirate(int cellIndex, int location) {
        int x = getXPositionOfCell(cellIndex) + 24 + (location % 3) * 16;
        int y = getYPositionOfCell(cellIndex) + 64 + (16 * (location / 3));
        return new Point(x, y);
    }

    public Point getPositionOfSegment(int segmentIndex) {
        int x = getXBasePosition() + segmentIndex * Values.SEG_SINGLE_WIDTH;
        int y = getYBasePosition() + (((segmentIndex + 1) % 2) * Values.SEG_HEIGHT);
        return new Point(x, y);
    }

    public Point getPositionOfSymbolOnCell(int cellIndex) {
        int x = getXPositionOfCell(cellIndex) + (Values.CELL_SIZE / 2);
        int y = getYPositionOfCell(cellIndex) + (Values.CELL_SIZE / 4);
        return new Point(x, y);
    }

    public Point getPositionOfCell(int cellIndex) {
        return new Point(getXPositionOfCell(cellIndex), getYPositionOfCell(cellIndex));
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

    public int calculateIndexGap(int cellIndex) {
        if (cellIndex == -1)
            return Values.START_INDEX_GAP;
        if (cellIndex == game.getBoard().getEndCell().getIndex())
            return isNumOfSegmentsOdd() ? Values.END_ODD_INDEX_GAP : Values.END_EVEN_INDEX_GAP;
        else
            return Values.DEFAULT_INDEX_GAP;
    }

    public boolean isNumOfSegmentsOdd() {
        return (game.getBoard().getNumOfSegments() % 2 == 1);
    }

    public Point getBasePoint() {
        return new Point(getXBasePosition(), getYBasePosition());
    }

    public int getXBasePosition() {
        int numOfSegments = game.getBoard().getNumOfSegments();
        int numOfSingleColumns = numOfSegments + 1;
        return (gameView.getWidth() - (Values.SEG_SINGLE_WIDTH * numOfSingleColumns)) / 2;
    }

    public int getYBasePosition() {
        return (gameView.getHeight() - Values.SEG_HEIGHT * 2) / 2;
    }
}
