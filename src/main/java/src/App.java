package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private int width = 680;
    private int height = 680;
    private Board board;
    private TopBar topBar;

    @Override
    public void start(Stage stage) throws IOException {
        VBox window = new VBox();
        stage.setTitle("Minesweeper");

        topBar = new TopBar();
        board = new Board(5, 5);
        stage.sizeToScene();
        window.getChildren().addAll(topBar, board);
        scene = new Scene(window, height , width);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        
        launch();
    }

    

}