package ch.juventus.puzzlesolver.business.exporter;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SudokuExporter implements PuzzleExporter<Sudoku> {

    @Override
    public void exportPuzzle(Sudoku sudoku, File file) {

        try {
            Files.write(Path.of(file.getPath()), convertSudokuToString(sudoku).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertSudokuToString(Sudoku sudoku) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < sudoku.getSIZE(); row++) {
            for (int column = 0; column < sudoku.getSIZE(); column++) {
                stringBuilder.append(sudoku.getValue(row, column)).append(" ");
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}