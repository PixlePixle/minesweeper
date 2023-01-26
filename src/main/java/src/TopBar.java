package src;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.VBox;

public class TopBar extends MenuBar{
    ClickableMenu options;
    ClickableMenu about;
    ClickableMenu restart;
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
            ButtonType type = new ButtonType("Cancel", ButtonData.OK_DONE);
            ButtonType replay = new ButtonType("Done", ButtonData.YES);

            // Adding Difficulties
            RadioButton easy = new RadioButton("Beginner");
            easy.setTooltip(new Tooltip("8x8 with 10 mines"));
            RadioButton medium = new RadioButton("Medium");
            medium.setTooltip(new Tooltip("Medum"));
            RadioButton hard = new RadioButton("Expert");
            hard.setTooltip(new Tooltip("had"));
            ToggleGroup difficulty = new ToggleGroup();
            difficulty.getToggles().addAll(easy, medium, hard);
            VBox radioButtons = new VBox();
            radioButtons.getChildren().addAll(easy, medium, hard);
            dialog.getDialogPane().getChildren().addAll(radioButtons);


            dialog.getDialogPane().getButtonTypes().addAll(type, replay);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getText().equals("Done")) {
                if(easy.isSelected()) {
                    board.updateBoard(8, 8, 10);
                    App.resize(8, 8);
                }
                if (medium.isSelected()) {
                    board.updateBoard(10, 10, 20);
                    App.resize(10, 10);
                }
                if (hard.isSelected()) {
                    board.updateBoard(15, 15, 99);
                    App.resize(15, 15);
                }
                board.restart();
            }
                
        });


        restart = new ClickableMenu("Restart");
        restart.setOnAction(e -> {
            board.restart();
        });


        about = new ClickableMenu("About");
        String aboutText = "Minesweeper clone made by Peter";

        about.setOnAction(e -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("About");
            ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
            dialog.setContentText(aboutText);
            dialog.getDialogPane().getButtonTypes().addAll(type);
            Optional<ButtonType> result = dialog.showAndWait();
        });



        this.getMenus().addAll(restart, options, about);
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
