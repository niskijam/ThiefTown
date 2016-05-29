package ThiefTown;

import java.util.Random;

public class ThiefTown
{
	PositionMarker[][] board;
	private int numNPC;
	int[][] PlayerLocations;
	int currentPlayer;
	int playersAlive;
	GuiInterface gui;
	int p1NpcsKilled;
	int p2NpcsKilled;

	/**
	 * Creates the Board
	 * @param r the number of rows and columns
	 * @param numNPC the number of NPCs
	 * @param gui the gui interface
	 */
	public ThiefTown(int r, int numNPC, GuiInterface gui)
	{
		this.gui = gui;
		p1NpcsKilled = 0;
		p2NpcsKilled = 0;
		currentPlayer = 1;
		board = new PositionMarker[r][r];
		for (int i = 0; i < r; i++)
		{
			for (int j = 0; j < r; j++)
			{
				board[i][j] = new PositionMarker(true, false, false, false, 0);				
			}
		}		
		this.numNPC = numNPC;
		placeNPC();
		this.PlayerLocations = new int[2][3];
		placePlayer();
	}

	/**
	 * Places NPCs randomly
	 */
	public void placeNPC()
	{
		Random rng = new Random();
		for (int i = 1; i <= numNPC; i++)
		{
			int xpos;
			int ypos;
			do 
			{
				xpos = new Random().nextInt(board.length);
				ypos = new Random().nextInt(board[0].length);
			} while (!board[xpos][ypos].isBlank);
			board[xpos][ypos] = new PositionMarker(false, false, true, true, i + 2);
		}
	}

	/**
	 * Places Players Randomly
	 */
	public void placePlayer()
	{
		{
			for (int i = 0; i < 2; i++)
			{
				int xpos;
				int ypos;
				do 
				{
					xpos = new Random().nextInt(board.length);
					ypos = new Random().nextInt(board[0].length);
				} while (!board[xpos][ypos].isBlank);

				board[xpos][ypos] = new PositionMarker(false, true, false, true, i + 1);
				PlayerLocations[i][1] = xpos;
				PlayerLocations[i][2] = ypos;
			}
		}
	}

	/**
	 * Moves a player in the specified direction
	 * @param xOffSet the change in the x position
	 * @param yOffSet the change in the y position
	 */
	public void move(int xOffSet, int yOffSet)
	{
		int currentX = PlayerLocations[currentPlayer - 1][1];
		int currentY = PlayerLocations[currentPlayer - 1][2];

		if ((xOffSet == -1 && currentX == 0)
				|| (yOffSet == -1 && currentY == 0)
				|| (xOffSet == 1 && currentX == board[0].length - 1)
				|| (yOffSet == 1 && currentY == board.length - 1))
		{
			System.out.print("");
		}
		else if (board[currentX + xOffSet][currentY + yOffSet].isBlank)
		{
			PlayerLocations[currentPlayer - 1][1] = currentX + xOffSet;
			PlayerLocations[currentPlayer - 1][2] = currentY + yOffSet;

			board[currentX + xOffSet][currentY + yOffSet] = new PositionMarker(false, true, false, true, currentPlayer);
			board[currentX][currentY] = new PositionMarker(true, false, false, false, 0);			
		}

		if (currentPlayer == 2)
		{
			moveNPC();
			gui.initUI();
		}
		currentPlayer = currentPlayer % 2 + 1;
	}

	/**
	 * Moves the NPCs randomly
	 */
	public void moveNPC()
	{
		Random rng = new Random();
		int direction = 0;
		int[] alreadyMoved = new int[numNPC];
		int alreadyMovedSize = 0;

		for (int r = 0; r < board[0].length; r++)
		{
			for (int c = 0; c < board.length; c++)
			{
				if (board[r][c].isNPC)
				{
					int curPlayerNum = 0;
					if (arrayContains(alreadyMoved, board[r][c].playerNum)) {
						//System.out.println("pakyu alreayd moved num "+board[r][c].playerNum);
						continue;
					} else {
						alreadyMoved[alreadyMovedSize++] = board[r][c].playerNum;
						curPlayerNum = board[r][c].playerNum;
					}
					direction = rng.nextInt(6);

					// System.err.println("Direction: " + direction + " r: " + r + " c: " + c);

					if (direction == 2 && r != 0)
					{
						// System.err.println("Case 1");
						//moves up
						if (board[r-1][c].isBlank)
						{
							board[r - 1][c] = new PositionMarker(false, false, true, true, curPlayerNum);
							board[r][c] = new PositionMarker(true, false, false, false, 0);
						}
					}
					else if (direction == 3 && c != 0)
					{
						// System.err.println("Case 2");
						//moves left
						if (board[r][c-1].isBlank)
						{
							board[r][c-1] = new PositionMarker(false, false, true, true, curPlayerNum);
							board[r][c] = new PositionMarker(true, false, false, false, 0);
						}
					}
					else if (direction == 4 && r != board[0].length - 1)
					{
						// System.err.println("Case 3");
						//moves down
						if (board[r + 1][c].isBlank)
						{
							board[r + 1][c] = new PositionMarker(false, false, true, true, curPlayerNum);
							board[r][c] = new PositionMarker(true, false, false, false, 0);
						}
					}
					else if (direction == 5 && c != board.length - 1)
					{
						// System.err.println("Case 4");
						//moves right
						if (board[r][c + 1].isBlank)
						{
							board[r][c + 1] = new PositionMarker(false, false, true, true, curPlayerNum);
							board[r][c] = new PositionMarker(true, false, false, false, 0);
						}
					}
					else
					{
						// System.err.println("Case 5");
					}
				}
			}
		}		
	}
	
	/**
	 * Checks if an array contains a value
	 * @param array the array that will be searched through
	 * @param value the value being searched for
	 * @return if the value is found
	 */
	public static boolean arrayContains(int[] array, int value) 
	{
		for (int e : array)
		{
			if (e == value)
			{
				return true;
			}
		}
		return false;
	}


	/**
	 * Allows the players to attack in a specified direction
	 * @param xOffSet the change in x position
	 * @param yOffSet the change in y position
	 */
	public void attack(int xOffSet, int yOffSet)
	{
		int currentX = PlayerLocations[currentPlayer - 1][1];
		int currentY = PlayerLocations[currentPlayer - 1][2];

		int toAttackX = currentX + xOffSet;
		int toAttackY = currentY + yOffSet;

		if (toAttackX > board.length - 1 || toAttackY > board[0].length - 1 || toAttackX < 0 || toAttackY < 0) 
		{
			//System.out.println("paku bad attack");
			if (currentPlayer == 2)
			{
				moveNPC();
				gui.initUI();
			}
			currentPlayer = currentPlayer % 2 + 1;
			return;
		}

		if (!board[toAttackX][toAttackY].isBlank)
		{
			if (board[toAttackX][toAttackY].isNPC)
			{
				if (currentPlayer == 1)
				{
					p1NpcsKilled++;
				}
				else if (currentPlayer == 2)
				{
					p2NpcsKilled++;
				}
			}
			board[toAttackX][toAttackY] = new PositionMarker(false, false, false, false, 0);
			playersAlive();
		}
		if (currentPlayer == 2)
		{
			moveNPC();
			gui.initUI();
		}
		currentPlayer = currentPlayer % 2 + 1;
	}

	/**
	 * Displays the board to the console
	 */
	public void displayBoard()
	{
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[0].length; c++)
			{
				if (board[r][c].isNPC)
				{
					System.out.print("| O |");
				}
				else if(board[r][c].isPlayer)
				{
					System.out.print("| O |");
				}
				else if (board[r][c].isBlank)
				{
					System.out.print("|   |");
				}
				else if (!board[r][c].isAlive)
				{
					System.out.print("| X |");
				}
			}
			System.out.println();
		}
	}
	
	/**
	 * Gets the board
	 * @return the board
	 */
	public PositionMarker[][] getBoard()
	{
		return board;
	}
	
	/**
	 * Checks how many players are alive
	 * @return the number of players alive
	 */
	public int playersAlive()
	{
		playersAlive = 0;
		for (int r = 0; r < board.length; r++)
		{
			for (int c = 0; c < board[0].length; c++)
			{
				if (board[r][c].isPlayer)
				{
					playersAlive++;
				}
			}
		}
		return playersAlive;
	}
	
	/**
	 * Checks whose turn it is
	 * @return the player whose turn it is
	 */
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	/**
	 * Gets the number of NPCs that player 1 has killed
	 * @return the number of killed NPCs
	 */
	public int getP1NpcsKilled()
	{
		return p1NpcsKilled;
	}
	
	/**
	 * Gets the number of NPCs that player 2 has killed
	 * @return the number of killed NPCs
	 */
	public int getP2NpcsKilled()
	{
		return p2NpcsKilled;
	}
}
