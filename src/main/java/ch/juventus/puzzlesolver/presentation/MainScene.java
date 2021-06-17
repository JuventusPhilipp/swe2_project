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
import java.util.Objects;

public class MainScene extends Scene {

    private final static BorderPane root = new BorderPane();
    private final HBox bot = new HBox();
    private final SplitMenuButton buttonLoad = new SplitMenuButton();
    private final Button buttonSave = new Button("save");
    private final Button buttonSolve = new Button("solve");
    private final Button buttonReset = new Button("reset");
    private final ToggleButton buttonDarkMode = new ToggleButton("DarkMode");
    private final String lightModeCssPath = Objects.requireNonNull(getClass().getResource("/presentation/lightMode.css")).toExternalForm();
    private final String darkModeCssPath = Objects.requireNonNull(getClass().getResource("/presentation/darkMode.css")).toExternalForm();

    public MainScene() {
        super(root, 725, 800);
        this.getStylesheets().add(lightModeCssPath);
        root.setBottom(initializeBotPane());
    }

    private void setButtonLoad(ActionEvent event) {

    }

    private void setButtonSave(ActionEvent event) {


    }

    private void setButtonSolve(ActionEvent event) {

    }

    private void setButtonReset(ActionEvent event) {

    }

    private void setButtonDarkMode(ActionEvent event) {
        if (buttonDarkMode.isSelected()) {
            this.getStylesheets().remove(lightModeCssPath);
            this.getStylesheets().add(darkModeCssPath);
            buttonDarkMode.setText("LightMode");
        } else {
            this.getStylesheets().remove(darkModeCssPath);
            this.getStylesheets().add(lightModeCssPath);
            buttonDarkMode.setText("DarkMode");
        }
    }

    public Scene getScene() {
        return this;
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
