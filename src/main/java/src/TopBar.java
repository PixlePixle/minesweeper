package src;

import java.util.Optional;

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
    int selection = 1;
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
            easy.setTooltip(new Tooltip("9x9 with 10 mines"));
            RadioButton medium = new RadioButton("Medium");
            medium.setTooltip(new Tooltip("16x16 with 40 mines"));
            RadioButton hard = new RadioButton("Expert");
            hard.setTooltip(new Tooltip("30x16 with 99 mines"));
            ToggleGroup difficulty = new ToggleGroup();
            difficulty.getToggles().addAll(easy, medium, hard);
            switch (selection) {
                case 1:
                    easy.setSelected(true);
                    easy.requestFocus();
                    break;
                case 2:
                    medium.setSelected(true);
                    medium.requestFocus();
                    break;
                case 3:
                    hard.setSelected(true);
                    hard.requestFocus();
                    break;
        
                default:
                    System.out.println("How'd we get here?");
                    break;
            }
            VBox radioButtons = new VBox();
            radioButtons.getChildren().addAll(easy, medium, hard);
            dialog.getDialogPane().getChildren().addAll(radioButtons);
            easy.setMinSize(RadioButton.USE_PREF_SIZE, RadioButton.USE_PREF_SIZE);
            medium.setMinSize(RadioButton.USE_PREF_SIZE, RadioButton.USE_PREF_SIZE);
            hard.setMinSize(RadioButton.USE_PREF_SIZE, RadioButton.USE_PREF_SIZE);
            
            dialog.getDialogPane().setMinSize(200, 120);
            dialog.getDialogPane().setMaxSize(200, 120);

            dialog.getDialogPane().getButtonTypes().addAll(type, replay);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().getText().equals("Done")) {
                if(easy.isSelected()) {
                    board.updateBoard(9, 9, 10);
                    App.resize(9, 9);
                    selection = 1;
                }
                if (medium.isSelected()) {
                    board.updateBoard(16, 16, 40);
                    App.resize(16, 16);
                    selection = 2;
                }
                if (hard.isSelected()) {
                    board.updateBoard(16, 30, 99);
                    App.resize(16, 30);
                    selection = 3;
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
            dialog.showAndWait();
        });



        this.getMenus().addAll(restart, options, about);
    }
}
