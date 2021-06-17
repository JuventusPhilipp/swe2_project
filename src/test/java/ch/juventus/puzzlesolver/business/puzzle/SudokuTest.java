package ch.juventus.puzzlesolver.business.puzzle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuTest {

    private Sudoku emptySudoku = new Sudoku();

    @Test
    public void testGetSize() {
        assertEquals(9, emptySudoku.getSIZE());
    }
}