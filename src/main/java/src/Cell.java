package src;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public class Cell extends Button{
    private int positionRow;
    private int positionColumn;
    private int surroundingMines;
    private boolean mine;
    private boolean flag;
    private static boolean lose = false;

    // Create a way of detecting if the button is right clicked and have it add an icon of a flag
    // Flagging a mine should also make it either unclickable or make it so that clicking the button has no effect DONE
    // Right clicking again will clear the flag and regive functionality
    // Maybe a boolean logic switch? IMPLEMENTED

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
            action();
        });
    }

    public void action() {
        if(isMine()) {
            setDisable(true);
            setText("💣");
            lose = true;
        } else if (isFlagged()) {
            setText("🚩");
        } else {
            setText(Integer.toString(surroundingMines));
        }
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


