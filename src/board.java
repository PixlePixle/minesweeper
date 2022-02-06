public class board {
    private int width;
    private int height;
    private int mines;
    private int[][] gameBoard;
    private String[][] displayBoard;

    public board(int x, int y, int mines) {
        width = x;
        height = y;
        this.mines = mines;
        
        //Initializes gameBoard and plants mines
        plantMines();

        //Initializes displayBoard
        String[][] arrayDisplay = new String[width][height];
        for(int i = 0; i < height; i++) { //Fills every spot with 0... Maybe unnecessary
            for(int j = 0; j < width; j++) {
                arrayDisplay[j][i] = "[%]";
            }
        }
        displayBoard = arrayDisplay;

        System.out.println("Board created\nWidth: " + width + "\nHeight: " + height + "\nNumber of Mines: " + this.mines);
    }

    public void plantMines() {
        int[][] array = new int[width][height];

        //Randomly plants mines
        for(int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * width);
            int y = (int) (Math.random() * height);
            if(array[x][y] != 2) {
                array[x][y] = 2;
            }
            else
                i--;
        }
        gameBoard = array;
        numberMines();
    }

    private void numberMines() {
        int[][] array = new int[width][height];
        for(int i = 0; i < height; i++) { 
            for(int j = 0; j < width; j++) {
                array[j][i] = gameBoard[j][i];
            }
        }

        for(int y = 0; y < height; y++) { 
            for(int x = 0; x < width; x++) {
                if(array[x][y] == 2) {
                    int xMin = x-1;
                    int xMax = x+2;
                    int yMin = y-1;
                    int yMax = y+2;
                    if(x == 0)
                        xMin = 0;
                    if(xMax > width)
                        xMax = width;
                    if(y == 0)
                        yMin = 0;
                    if(yMax > height)
                        yMax = height;

                    for(int i = yMin; i < yMax; i++) {
                        for(int j = xMin; j < xMax; j++) {
                            if(array[j][i] != 2)
                                array[j][i] += 10;
                        }
                    }
                }
            }
        }
        gameBoard = array;
    }

    public void display() {
        for(int i = 0; i < height; i++) { 
            for(int j = 0; j < width; j++) {
                if(gameBoard[j][i] == 0 || gameBoard[j][i] == 2)
                    System.out.print("0");
                System.out.print(gameBoard[j][i] + " ");

            }
            System.out.println();
        }
    }

    public void displayGame() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                System.out.print(displayBoard[j][i] + " ");
                if(j == width - 1)
                    System.out.println();
            }
        }
    }

    public void checkBoard() {

    }



    
}
