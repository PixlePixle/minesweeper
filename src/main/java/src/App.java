package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private int width;
    private int height;
    private Board board;
    private TopBar topBar;

    @Override
    public void start(Stage stage) throws IOException {
        VBox window = new VBox();
        HBox align = new HBox();
        align.setAlignment(Pos.CENTER);
        stage.setTitle("Minesweeper");

        
        board = new Board(8, 8, 10);
        topBar = new TopBar(board);
        height = board.rows * 30 + 10 + 25; //Right now there's the issue where going a larger grid starts to cut off sides/bottom
        width = board.columns * 30 + 10;
        stage.sizeToScene();
        align.getChildren().addAll(board);
        window.getChildren().addAll(topBar, align);
        scene = new Scene(window, width , height);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        
        launch();
    }

    

}