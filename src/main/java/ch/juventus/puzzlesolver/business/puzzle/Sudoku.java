package ch.juventus.puzzlesolver.business.puzzle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sudoku implements Puzzle{

    private final static Logger logger = LoggerFactory.getLogger(Sudoku.class);

    private final static int SIZE = 9;
    private int[][] grid;

    public Sudoku() {
        this.grid = new int[SIZE][SIZE];
    }

    public int getValue(int row, int column) {
        return grid[row][column];
    }

    public void setValue(int row, int column, int value) {
        grid[row][column] = value;
    }

    public void reset() {
        for (int column = 0; column < SIZE; column++) {
            for (int row = 0; row < SIZE; row++) {
                grid[row][column] = 0;
            }
        }
    }

    public int getSIZE() {
        return SIZE;
    }

}
