package ch.juventus.puzzlesolver.presentation;

import ch.juventus.puzzlesolver.business.handler.SudokuHandler;
import ch.juventus.puzzlesolver.business.puzzle.Sudoku;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SudokuPane extends StackPane {

    private final static Logger logger = LoggerFactory.getLogger(SudokuPane.class);
    private final GridPane inputFields = new GridPane();
    private final Pane backgroundPane = new Pane();
    private final SudokuHandler handler;
    private Sudoku sudoku;

    public SudokuPane() {
        sudoku = new Sudoku();
        handler = new SudokuHandler();
        initializeGrid(sudoku);
        initializeBackground();
        this.getChildren().add(backgroundPane);
        this.getChildren().add(inputFields);
    }

    public void loadSudoku(String selection) {
        try {
            sudoku = handler.loadPuzzle(selection);
        } catch (NullPointerException e) {
            logger.error("no file selected");
        }
        updateGrid();
    }

    public void saveSudoku() {
        handler.savePuzzle(sudoku);
    }

    public void solveSudoku() {
        sudoku = handler.solvePuzzle(sudoku);
        updateGrid();
    }

    public void resetSudoku() {
        sudoku.reset();
        updateGrid();
    }

    private void updateGrid() {
        for (int row = 0; row < sudoku.getSIZE(); row++) {
            for (int column = 0; column < sudoku.getSIZE(); column++) {
                setFieldByRowColumnIndex(row, column, sudoku.getValue(row, column));
            }
        }
        logger.info("grid got updated");
    }

    public void setFieldByRowColumnIndex(int row, int column, int value) {
        for (Node field: inputFields.getChildren()) {
            if (GridPane.getRowIndex(field) == row && GridPane.getColumnIndex(field) == column) {
                if (value == 0)
                    ((TextField)field).setText("");
                else
                    ((TextField)field).setText(String.valueOf(value));
            }
        }
    }

    private void initializeBackground() {
        drawLines(false);
        drawLines(true);
    }

    private void drawLines(boolean rotate) {
        int startPoint = 100;
        int length = 675;
        double padding = 75;
        for (int i = 0; i < 8; i++) {
            Line line;
            if (rotate) {
                line = new Line(startPoint + padding * i, startPoint - padding, startPoint + padding * i, startPoint - padding + length);
            } else {
                line = new Line(startPoint - padding, startPoint + padding * i, startPoint - padding + length, startPoint + padding * i);
            }
            if ((i+1) % 3 == 0) {
                line.setStrokeWidth(5);
            }
            line.setId("sudokuBackgroundLine");
            backgroundPane.getChildren().add(line);
        }
    }

    private void initializeGrid(Sudoku sudoku) {
        inputFields.setVgap(15);
        inputFields.setHgap(15);
        inputFields.setAlignment(Pos.CENTER);

        for (int row = 0; row < sudoku.getSIZE(); row++) {
            for (int column = 0; column < sudoku.getSIZE(); column++) {
                int finalRow = row;
                int finalColumn = column;
                TextField textField = new TextField();
                textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (!newValue.matches("\\d{1,9}")) {
                        if (!newValue.equals(""))
                            logger.info("value {} at [{}][{}] is not an integer", newValue, finalRow, finalColumn);
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    } else {
                        sudoku.setValue(finalRow, finalColumn, Integer.parseInt(newValue));
                    }
                });
                textField.setId("sudokuInputField");
                textField.setPrefSize(60,60);
                inputFields.add(textField, column, row);
            }
        }
    }
}