package ThiefTown;

import java.util.Scanner;

public class ThiefTownTester 
{
	public static void main(String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		ThiefTown t = new ThiefTown(7, 4, null);
		t.displayBoard();
		while (t.playersAlive() == 2)
		{
			System.out.print("Player " + t.getCurrentPlayer() + ", please enter a direction: ");
			String input = scnr.next();
			if (input.equalsIgnoreCase("w"))
			{
				t.move(-1, 0);
			}
			else if (input.equalsIgnoreCase("a"))
			{
				t.move(0, -1);
			}
			else if (input.equalsIgnoreCase("s"))
			{
				t.move(1, 0);
			}
			else if (input.equalsIgnoreCase("d"))
			{
				t.move(0, 1);
			}
			else if (input.equalsIgnoreCase("x"))
			{
				t.move(0, 0);
			}
			else if (input.equalsIgnoreCase("i"))
			{
				t.attack(-1, 0);
			}
			else if (input.equalsIgnoreCase("j"))
			{
				t.attack(0, -1);
			}
			else if (input.equalsIgnoreCase("k"))
			{
				t.attack(1, 0);
			}
			else if (input.equalsIgnoreCase("l"))
			{
				t.attack(0, 1);
			}
			else if (input.equalsIgnoreCase("x"))
			{
				t.move(0, 0);
			}
			else
			{
				System.out.println("Please press w, a, s, d, or x to move, or press r + w, a, s, or d to attack");
			}
		}
		System.out.println();
		t.displayBoard();
		System.out.println("PLAYER " + (t.getCurrentPlayer() % 2 + 1) + " WINS!");
	}
}
