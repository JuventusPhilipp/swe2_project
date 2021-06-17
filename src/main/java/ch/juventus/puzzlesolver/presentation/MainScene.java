package ch.juventus.puzzlesolver.presentation;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MainScene extends Scene {

    private final static Logger logger = LoggerFactory.getLogger(MainScene.class);
    private final static BorderPane root = new BorderPane();
    private final HBox bot = new HBox();
    private final SplitMenuButton buttonLoad = new SplitMenuButton();
    private final Button buttonSave = new Button("save");
    private final Button buttonSolve = new Button("solve");
    private final Button buttonReset = new Button("reset");
    private final ToggleButton buttonDarkMode = new ToggleButton("DarkMode");
    private final SudokuPane sudokuPane = new SudokuPane();
    private final String lightModeCssPath = Objects.requireNonNull(getClass().getResource("/presentation/lightMode.css")).toExternalForm();
    private final String darkModeCssPath = Objects.requireNonNull(getClass().getResource("/presentation/darkMode.css")).toExternalForm();

    public MainScene() {
        super(root, 725, 800);
        this.getStylesheets().add(lightModeCssPath);
        root.setBottom(initializeBotPane());
        root.setCenter(initializeCenterPane());
    }

    private void setButtonLoad(ActionEvent event) {
        logger.info("loadButton was clicked with {}", buttonLoad.getText());
        if (root.getCenter().getClass().isInstance(sudokuPane)) {
            sudokuPane.loadSudoku(buttonLoad.getText());
            buttonLoad.setText("load");
        }
    }

    private void setButtonSave(ActionEvent event) {
        logger.info("saveButton was clicked");
        if (root.getCenter().getClass().isInstance(sudokuPane)) {
            sudokuPane.saveSudoku();
        }

    }

    private void setButtonSolve(ActionEvent event) {
        logger.info("solveButton was clicked");
        if (root.getCenter().getClass().isInstance(sudokuPane)) {
            sudokuPane.solveSudoku();
        }
    }

    private void setButtonReset(ActionEvent event) {
        logger.info("resetButton was clicked");
        if (root.getCenter().getClass().isInstance(sudokuPane)) {
            sudokuPane.resetSudoku();
        }
    }

    private void setButtonDarkMode(ActionEvent event) {
        logger.info("darkModeButton was clicked");
        if (buttonDarkMode.isSelected()) {
            logger.info("switched to darkMode");
            this.getStylesheets().remove(lightModeCssPath);
            this.getStylesheets().add(darkModeCssPath);
            buttonDarkMode.setText("LightMode");
        } else {
            logger.info("switched to lightMode");
            this.getStylesheets().remove(darkModeCssPath);
            this.getStylesheets().add(lightModeCssPath);
            buttonDarkMode.setText("DarkMode");
        }
    }

    public Scene getScene() {
        return this;
    }

    private Pane initializeCenterPane() {
        sudokuPane.setAlignment(Pos.CENTER);
        return sudokuPane;
    }

    private Pane initializeBotPane() {
        bot.setPrefHeight(75);
        bot.setAlignment(Pos.CENTER);
        bot.setSpacing(25);
        bot.getChildren().addAll(buttonLoad, buttonSave, buttonSolve, buttonReset, buttonDarkMode);
        buttonLoad.setText("load");
        buttonLoad.getItems().addAll(new MenuItem("easy"), new MenuItem("medium"), new MenuItem("hard"));
        buttonLoad.getItems().forEach(choice -> choice.setOnAction(e -> {buttonLoad.setText(choice.getText()); setButtonLoad(e);}));
        buttonLoad.setOnAction(this::setButtonLoad);
        buttonSave.setOnAction(this::setButtonSave);
        buttonSolve.setOnAction(this::setButtonSolve);
        buttonReset.setOnAction(this::setButtonReset);
        buttonDarkMode.setOnAction(this::setButtonDarkMode);
        return bot;
    }
}
