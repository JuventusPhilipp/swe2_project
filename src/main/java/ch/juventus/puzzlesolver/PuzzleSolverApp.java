package ch.juventus.puzzlesolver;

import ch.juventus.puzzlesolver.presentation.MainScene;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PuzzleSolverApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        mainStage.setTitle("PuzzleSolver");
        mainStage.setResizable(false);
        mainStage.initStyle(StageStyle.DECORATED);

        mainStage.setScene(new MainScene().getScene());
        mainStage.show();
    }
}