package ch.juventus.puzzlesolver.business.handler;

import ch.juventus.puzzlesolver.business.exporter.SudokuExporter;
import ch.juventus.puzzlesolver.business.importer.SudokuImporterFile;
import ch.juventus.puzzlesolver.business.importer.SudokuImporterWeb;
import ch.juventus.puzzlesolver.business.puzzle.Sudoku;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SudokuHandler implements PuzzleHandler<Sudoku> {

    private final static Logger logger = LoggerFactory.getLogger(SudokuHandler.class);

    @Override
    public Sudoku loadPuzzle(String selection) {
        if (selection.equals("load")) {
            File file = getSudokuFile();
            if (file == null) {
                logger.error("no file selected");
            }
            assert file != null;
            return new SudokuImporterFile().getPuzzleFromFile(file.getPath());
        }
        return new SudokuImporterWeb().getPuzzleFromWeb(selection);
    }

    @Override
    public void savePuzzle(Sudoku sudoku) {
        File file = createSudokuFile();
        new SudokuExporter().exportPuzzle(sudoku, file);
    }

    @Override
    public Sudoku solvePuzzle(Sudoku sudoku) {
        return null;
    }

    private File createSudokuFile() {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select file to save the sudoku");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("$sudoku.txt", "*.txt"));
        return fileChooser.showSaveDialog(fileChooserStage);
    }

    private File getSudokuFile() {
        Stage fileChooserStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("select sudoku file to load");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("$sudoku.txt", "*.txt"));
        return fileChooser.showOpenDialog(fileChooserStage);
    }
}
