package ThiefTown;

public class PositionMarker 
{
	public boolean isBlank;
	public boolean isPlayer;
	public boolean isNPC;
	public boolean isAlive;
	public int playerNum;
	
	/**
	 * Gets the status of each position on the board
	 * @param isBlank checks if the spot is blank
	 * @param isPlayer checks if the spot is a player
	 * @param isNPC checks if the spot is an NPC
	 * @param isAlive checks if the spot contains an alive character
	 * @param playerNum the player number
	 */
	public PositionMarker(boolean isBlank, boolean isPlayer, boolean isNPC, boolean isAlive, int playerNum)
	{
		this.isBlank = isBlank;
		this.isPlayer = isPlayer;
		this.isAlive = isAlive;
		this.isNPC = isNPC;
		this.playerNum = playerNum;
	}
	
	/**
	 * Converts the status of each spot into a String
	 */
	public String toString()
	{
		if (isNPC || isPlayer)
		{
			return "O";
		}
		else if (isBlank)
		{
			return "";
		}
		else if (!isAlive)
		{
			return "X-|-<";
		}
		return "";
	}
}
