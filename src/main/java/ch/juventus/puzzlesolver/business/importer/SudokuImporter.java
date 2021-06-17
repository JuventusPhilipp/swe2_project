package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

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