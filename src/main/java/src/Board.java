package src;

import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board extends VBox {
    public static int rows;
    public static int columns;
    boolean gameOver = false;
    public static Cell[][] board;

    /**
     * Initializes a board 
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        super();
        this.rows = rows;
        this.columns = columns;
        board = new Cell[rows][columns];
        populateBoard();
        System.out.println("Board created and populated!");
    }

    /**
     * Populates the board array as well seeding
     * and generating the display section.
     */
    private void populateBoard() {
        // Populates with Cells
        for (int i = 0; i < rows; i++) {
            HBox row = new HBox();
            for (int j = 0; j < columns; j++) {
                board[i][j] = new Cell(i, j);
                board[i][j].setBoard(this);
                row.getChildren().add(board[i][j]);
            }
            this.getChildren().add(row);
        }
        // Create a multithreaded thread so that when cleared == rows * cols - mines it's a win
        // Don't really care too much about flags cause you know how much there are

        //MAKE SURE TO CREATE A VARIABLE LATER SO THE NUMBER OF MINES CAN BE CHANGED
        //Seeds the bombs and numbers surrounding cells
        for (int i = 0; i < 5;) {
            int x = (int) (Math.random() * rows);
            int y = (int) (Math.random() * columns);
            if(!board[x][y].isMine()) {
                board[x][y].setMine();

                int xMin = x-1;
                int xMax = x+2;
                int yMin = y-1;
                int yMax = y+2;
                if(x == 0)
                    xMin = 0;
                if(xMax > rows)
                    xMax = rows;
                if(y == 0)
                    yMin = 0;
                if(yMax > columns)
                    yMax = columns;
                for(int z = xMin; z < xMax; z++) {
                    for(int w = yMin; w < yMax; w++) {
                        board[z][w].surroundingMinesCount();
                    }
                }

                i++;
                System.out.println("Mine " + i + " seeded at: " + x + "," + y);
            }
        }
        board[0][0].cleared = 0;
        loop();
    }

    // TODO: Shift the numbering from the populate Board method to one that works on click.
    // Maybe move to the Cell class, check surrounding cells for number of miens and then reveal
    // That way we can make it so if the first click has a bomb, it simply moves it elsewhere

    // As well as create a (possibly recursive) implementation for clearing the large patches of empty squares
    // As well as a reset method
    
    protected void endGame() {
        System.out.println("Boom!");
        gameOver = true;
    }

    public Board getBoard() {
        return this;
    }


    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public void  loop() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("You win!");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        ButtonType replay = new ButtonType("Replay", ButtonData.YES);
        dialog.getDialogPane().getButtonTypes().addAll(type, replay);
        Thread thread = new Thread(() -> {
        

            while(true) {
                if(board[0][0].cleared == (rows * columns) - 5 || gameOver) {
                    System.out.println("Game over");
                    if(board[0][0].cleared != (rows * columns))
                        dialog.setContentText("You hit a mine. Game over.");
                    else
                        dialog.setContentText("Congratulations! You've cleared the minefield.");
                    gameOver = true;
                    Platform.runLater(() -> {
                        Optional<ButtonType> result = dialog.showAndWait();
                        if (result.isPresent() && result.get().getText().equals("Replay")) {
                            this.getChildren().clear();
                            gameOver = false;
                            populateBoard();
                            
                        }
                        
                    });
                    break;
                } else {
                    System.out.print("");
                }
                
            }
        });
        thread.setDaemon(false);
        thread.start();
    }

}
