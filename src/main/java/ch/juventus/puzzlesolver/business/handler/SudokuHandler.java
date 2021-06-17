package ch.juventus.puzzlesolver.business.handler;

import ch.juventus.puzzlesolver.business.importer.SudokuImporterFile;
import ch.juventus.puzzlesolver.business.importer.SudokuImporterWeb;
import ch.juventus.puzzlesolver.business.puzzle.Sudoku;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SudokuHandler implements PuzzleHandler<Sudoku> {

    @Override
    public Sudoku loadPuzzle(String selection) {
        if (selection.equals("load")) {
            File file = getSudokuFile();
            if (file == null)
                // logger
                return null;
            return new SudokuImporterFile().getPuzzleFromFile(file);
        }
        return new SudokuImporterWeb().getPuzzleFromWeb(selection);
    }

    @Override
    public void savePuzzle(Sudoku sudoku) {

    }

    @Override
    public Sudoku solvePuzzle(Sudoku sudoku) {
        return null;
    }

    private File getSudokuFile() {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select sudoku file to load");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("$sudoku.txt", "*.txt"));
        return fileChooser.showOpenDialog(fileChooserStage);
    }
}
