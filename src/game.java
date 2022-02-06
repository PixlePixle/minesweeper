
import java.util.Scanner;

public class game{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input x, y, number of mines");
        int x = input.nextInt();
        int y = input.nextInt();
        int mines = input.nextInt();
        board test = new board(x, y, mines);
        test.plantMines();
        test.display();
        test.displayGame();
        input.close();
    }
}