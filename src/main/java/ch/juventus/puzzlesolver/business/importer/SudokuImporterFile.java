package ch.juventus.puzzlesolver.business.importer;

import ch.juventus.puzzlesolver.business.puzzle.Sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class SudokuImporterFile extends SudokuImporter {

    @Override
    public Sudoku getPuzzleFromFile(String path) {
        Sudoku sudoku = new Sudoku();
        try (Scanner scanner = new Scanner(new File(path))) {
            for (int row = 0; row < sudoku.getSIZE(); row++) {
                for (int column = 0; column < sudoku.getSIZE(); column++) {
                    sudoku.setValue(row, column, Integer.parseInt(scanner.next()));
                }
            }
        } catch (InvalidPathException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return sudoku;
    }
}