package minesweeper;
import java.util.Scanner;

public class oldCode {

	public void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//00 = Blank Space(No mine around)
		//#1 = Flag(No Mine)
		//#2 = Mine
		//#3 = Flag(Mine)
		//10 = 1 mine around
		//20 = 2 mines around
		//etc, etc.
		
		System.out.println("Board Width (Min 3, Max 9): ");
		int width = 8;
		System.out.println("Board Height (Min 3,Max 9): ");
		int height = 9;
		System.out.println("Number of Bombs: ");
		int bombs = 1;
		int i = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		boolean win = false;
		boolean gameLoop = true;
		String action = "";
		
		String[][] gameBoard = new String[width][height];
		int[][] board = new int[width][height];
		
		//Plants bombs
		while (i < bombs)
		{
			x = (int) (Math.random() * width);
			y = (int) (Math.random() * height);
			if (board[x][y] != 2)
			{
				board[x][y] = 2; 
				i++;
			}
		}
		
		//Updates each cell if mine is around
		x = 0;
		y = 0;
		while (y < height)
		{
			if (x < width)
			{
				if(board[x][y]%10 == 2)
				{
					if((x-1) >= 0)//Left column
					{
						board[x-1][y] += 10;
						if((y-1)>=0)
							board[x-1][y-1] += 10;
						if((y+1)<height)
							board[x-1][y+1] += 10;
					}
					
					if((y-1)>=0)//middle
						board[x][y-1] += 10;
					
					if((y+1)<height)
						board[x][y+1] += 10;
					
					if((x+1) < width)//right
					{
						board[x+1][y] += 10;
						if((y-1)>=0)
							board[x+1][y-1] += 10;
						if((y+1)<height)
							board[x+1][y+1] += 10;
					}
				}
				x++;
			}
			else
			{
				y++;
				x = 0;
			}
		}
		
		x = 0;
		y = 0;
		//Creates mineboard display board
		while (y < height)
		{
			if (x < width)
			{
				gameBoard[x][y] = "[%]";
				x++;
			}
			else
			{
				y++;
				x = 0;
			}
		}
		
		while(gameLoop)
		{
			//Displays board
			x = 0;
			y = 0;
			i = 0;
			while (y < height)
			{
				if (x < width)
				{
					if (x == 0) //Prints side guiding numbers
						System.out.print(y + 1 + " ");
					System.out.print(gameBoard[x][y]);// Prints gameBoard cell
					//System.out.print(" " + (int) (Math.floor((board[x][y]/10))) + " ");// Displays number of mines
					//System.out.print(" " + board[x][y] + " ");//Shows cell value
					x++;
				}
				else //Once row is complete, go to next row
				{
					y++;
					System.out.print("\n");
					x = 0;
				}
				if (y == height) //Prints out bottom guiding numbers
				{
					System.out.print("  ");
					while (i < width)
					{
						System.out.print(" " + (i + 1) + " ");
						i++;
					}
				}
			}
			
			//Asks user for move
			System.out.println("\nMoves (f = flag(toggle), r = reveal)   Ex: r 3 5");
			action = input.next().trim();
			x = input.nextInt() - 1;
			y = input.nextInt() - 1;
			input.nextLine();
			
			//If not within range
			if (x <= -1 || y <= -1 || x >= width || y >= height)
				System.out.println("Invalid move");
			
			//if user chooses to flag a cell
			else if (action.equalsIgnoreCase("f"))
			{
				if(gameBoard[x][y].equals("[%]"))
				{
					gameBoard[x][y] = "[@]";
					board[x][y]++;
				}
				else if(gameBoard[x][y].equals("[@]"))
				{
					gameBoard[x][y] = "[%]";
					board[x][y]--;
				}
				else
					System.out.println("This cell is already revealed!");
			}
			
			//If user chooses to reveal a square
			else if(action.equalsIgnoreCase("r"))
			{
				if(board[x][y] % 10 == 2)
				{
					System.out.println("You lose!");
					break;
				}
				else
				{
					gameBoard[x][y] = "[" + (int) (Math.floor((board[x][y]/10))) + "]";
					if ((int) (Math.floor((board[x][y]/10))) == 0)
					{
						i = 0;
						while (i < 10 + Math.floor(width*height/10))//Idk what to do, just making sure just in case
						{
							x = 0;
							y = 0;
							while (y < height)
							{
								if (x < width)
								{
									if(gameBoard[x][y].equals("[0]"))
									{
										if((x-1) >= 0)//Left column
										{
											if(board[x-1][y] % 10 != 1) 
											gameBoard[x-1][y] = "[" + (int) (Math.floor((board[x-1][y]/10))) + "]";
											if((y-1)>=0) {
												if(board[x-1][y-1] % 10 != 1) 
													gameBoard[x-1][y-1] = "[" + (int) (Math.floor((board[x-1][y-1]/10))) + "]";}
											if((y+1)<height) {
												if(board[x-1][y+1] % 10 != 1) 
													gameBoard[x-1][y+1] = "[" + (int) (Math.floor((board[x-1][y+1]/10))) + "]";}
										}
										//middle
										if((y-1)>=0) {
											if(board[x][y-1] %10 != 1) 
												gameBoard[x][y-1] = "[" + (int) (Math.floor((board[x][y-1]/10))) + "]";}
										
										if((y+1)<height) {
											if(board[x][y+1] % 10 != 1) 
												gameBoard[x][y+1] = "[" + (int) (Math.floor((board[x][y+1]/10))) + "]";}
										
										if((x+1) < width)//right
										{
											if(board[x+1][y] % 10!= 1) {
												gameBoard[x+1][y] = "[" + (int) (Math.floor((board[x+1][y]/10))) + "]";}
											if((y-1)>=0) {
												if(board[x+1][y-1] %10 != 1)
													gameBoard[x+1][y-1] = "[" + (int) (Math.floor((board[x+1][y-1]/10))) + "]";}
											if((y+1)<height) {
												if(board[x+1][y+1] %10 != 1)
													gameBoard[x+1][y+1] = "[" + (int) (Math.floor((board[x+1][y+1]/10))) + "]";}
										}
									}
									x++;
								}
								else
								{
									y++;
									x = 0;
								}
							}
							i++;
						}
					}
					
					
					
				}
			}
			

			else
				System.out.println("Invalid move");

			//Checks to see if all mines flagged
			x = 0;
			y = 0;
			z = 0;
			gameLoop = false;
			while (y < height)
			{
				if (x < width)
				{
					if(board[x][y] % 10 == 3)
					z++;
					if(!(gameBoard[x][y].equals("[%]") || board[x][y] % 10 == 1) && !gameLoop)
					{
						;
					}
					else
						gameLoop = true;
					x++;
				}
				else
				{
					y++;
					x = 0;
				}
			}
			
			
			

			//End of loop
		}
		if (z == bombs)
			System.out.println("Congrats!");
		System.out.println("game ended");
		//End of code
	}

}
