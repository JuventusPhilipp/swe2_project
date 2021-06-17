package ch.juventus.puzzlesolver.business.handler;

import ch.juventus.puzzlesolver.business.puzzle.Puzzle;

public interface PuzzleHandler<T extends Puzzle> {
    T loadPuzzle(String selection);
    void savePuzzle(T puzzle);
    T solvePuzzle(T puzzle);
}
