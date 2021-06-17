package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Puzzle;

public interface PuzzleImporter<T extends Puzzle> {
    T getPuzzleFromFile(String path);
    T getPuzzleFromWeb(String difficulty);
}
