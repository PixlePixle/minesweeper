package src;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ButtonBar.ButtonData;

public class TopBar extends MenuBar{
    ClickableMenu options;
    ClickableMenu help;
    Board board;
    public TopBar(Board board) {
        super();
        this.setMinHeight(25);
        this.setMaxHeight(25);
        this.board = board;
        populate();
    }

    private void populate() {
        options = new ClickableMenu("Options");
        options.setOnAction(e -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Options");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            ButtonType replay = new ButtonType("Replay", ButtonData.YES);
            dialog.getDialogPane().getButtonTypes().addAll(type, replay);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getText().equals("Replay")) {
                board.gameOver = true;
                board.getChildren().clear();
                board.gameOver = false;
                board.populateBoard();
            }
                
        });




        help = new ClickableMenu("Help");
        help.setOnAction(e -> {
            runNow(() -> {
                System.out.println("Help Pressed");
            });
        });



        this.getMenus().addAll(options, help);
    }

    // Do i need this?
    /**
     * Multithreading
     * @param target
     */
    public static void runNow(Runnable target) {
        Thread thread = new Thread(target);
        thread.setDaemon(true);
        thread.start();
    }
}
