package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Puzzle;

import java.io.File;

public interface PuzzleImporter<T extends Puzzle> {
    T getPuzzleFromFile(File file);
    T getPuzzleFromWeb(String difficulty);
}