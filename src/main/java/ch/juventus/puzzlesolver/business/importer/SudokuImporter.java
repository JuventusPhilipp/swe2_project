package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

import java.io.File;

public abstract class SudokuImporter implements PuzzleImporter<Sudoku> {

    @Override
    public Sudoku getPuzzleFromFile(String path) {
        return new SudokuImporterFile().getPuzzleFromFile(path);
    }

    @Override
    public Sudoku getPuzzleFromWeb(String difficulty) {
        return new SudokuImporterWeb().getPuzzleFromWeb(difficulty);
    }
}