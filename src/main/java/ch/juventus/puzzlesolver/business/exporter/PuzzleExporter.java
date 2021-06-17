package ch.juventus.puzzlesolver.business.exporter;

import ch.juventus.puzzlesolver.business.puzzle.Puzzle;

import java.io.File;

public interface PuzzleExporter<T extends Puzzle> {
    void exportPuzzle(T puzzle, File file);
}
