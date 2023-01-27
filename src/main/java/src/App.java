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
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        VBox window = new VBox();
        HBox align = new HBox();
        App.stage = stage;
        align.setAlignment(Pos.CENTER);
        stage.setTitle("Minesweeper");

        
        board = new Board(8, 8, 10);
        topBar = new TopBar(board);
        align.getChildren().addAll(board);
        window.getChildren().addAll(topBar, align);
        scene = new Scene(window, width , height);
        resize(8, 8);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void resize(int rows, int cols) {
        int newHeight = rows * 30 + 16 + 55; //Right now there's the issue where going a larger grid starts to cut off sides/bottom
        int newWidth = cols * 30 + 16 + 10;
        stage.setWidth(newWidth);
        stage.setHeight(newHeight);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        
        launch();
    }

    

}