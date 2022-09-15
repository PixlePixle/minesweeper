package src;

import src.Board;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Cell extends Button{
    static int cleared = 0;
    private int positionRow;
    private int positionColumn;
    private int surroundingMines;
    private boolean mine;
    private boolean flag;
    public boolean recursed = false;
    private static Board board;

    /**
     * Prevent creation of cell without information
     */
    private Cell() {}

    /**
     * Create a Cell object which creates a *temp* button object 
     * and set positionRow and positionColumn to i and j.
     * @param i
     * @param j
     */
    public Cell(int i, int j) {
        super(" ");
        this.positionRow = i;
        this.positionColumn = j;
        surroundingMines = 0;
        mine = false;
        flag = false;
        setPadding(Insets.EMPTY);
        setMinSize(30, 30);
        setMaxSize(30, 30);
        this.setFocusTraversable(false);
        this.setOnAction(e -> {
            if(!board.gameOver)
                action();
        });
        this.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY && !board.gameOver) {
                    flag = !flag;
                    if (flag) {
                        setText("🚩");
                    } else {
                        setText(" ");
                    }
                }
            }            
        });
    }

    public void action() {
        if(!recursed && !isFlagged() && !isMine() && !isDisabled()) {
            cleared++;
        }
        if(isFlagged()) {
            System.out.println("Flag clicked");
            System.out.println(cleared);
        } else if (isMine()) {
            setDisable(true);
            setText("💣");
            board.endGame();
        } else {
            setDisable(true);
            if(surroundingMines > 0) {
                setText(Integer.toString(surroundingMines));
            } else {
                setText(" ");
                recurse();
            }
        }
    }

    /**
     * Recursive function for clearing squares
     */
    private void recurse() {
        recursed = true;
        if(positionRow-1 >= 0 && !board.board[positionRow-1][positionColumn].recursed) {
            board.board[positionRow-1][positionColumn].action();
            if(positionColumn-1 >= 0 && !board.board[positionRow-1][positionColumn-1].recursed)
                board.board[positionRow-1][positionColumn-1].action();
        }
        if(positionRow+1 < board.rows && !board.board[positionRow+1][positionColumn].recursed) {
            board.board[positionRow+1][positionColumn].action();
            if(positionColumn+1 < board.columns && !board.board[positionRow+1][positionColumn+1].recursed)
                board.board[positionRow+1][positionColumn+1].action();
        }
        if(positionColumn-1 >= 0 && !board.board[positionRow][positionColumn-1].recursed) {
            board.board[positionRow][positionColumn-1].action();
            if(positionRow+1 < board.rows && !board.board[positionRow+1][positionColumn-1].recursed)
                board.board[positionRow+1][positionColumn-1].action();
        }
        if(positionColumn+1 < board.columns && !board.board[positionRow][positionColumn+1].recursed) {
            board.board[positionRow][positionColumn+1].action();
            if(positionRow-1 >= 0 && !board.board[positionRow-1][positionColumn+1].recursed)
                board.board[positionRow-1][positionColumn+1].action();
        }
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * Returns whether this cell is flagged or not.
     * @return
     */
    public boolean isFlagged() {
        return this.flag;
    }

    /**
     * Returns whether this cell is a mine or not.
     * @return
     */
    public boolean isMine() {
        return this.mine;
    }

    public void setMine() {
        mine = true;
    }
    
    public void surroundingMinesCount() {
        this.surroundingMines += 1;
    }

    /**
     * Returns number of surrounding mines.
     * @return
     */
    public int surroundingMines() {
        return this.surroundingMines;
    }
    
}


