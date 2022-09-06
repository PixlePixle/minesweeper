package src;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Board extends VBox {
    public int rows;
    public int columns;
    public Cell[][] board;

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
        //Move setting the click logic to here so that it's possible to check lose/Set recursive formula BUT HOW

        //MAKE SURE TO CREATE A VARIABLE LATER SO THE NUMBER OF MINES CAN BE CHANGED
        //Seeds the bombs and numbers surrounding cells
        for (int i = 0; i < 2;) {
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
    }

    // TODO: Shift the numbering from the populate Board method to one that works on click.
    // Maybe move to the Cell class, check surrounding cells for number of miens and then reveal
    // That way we can make it so if the first click has a bomb, it simply moves it elsewhere

    // As well as create a (possibly recursive) implementation for clearing the large patches of empty squares
    // As well as a reset method
    
    public void revealEmpty() {
        
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



}
