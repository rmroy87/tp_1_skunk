
//
// A Skunk Player
public class Player {

	private int seriesScore;
	private int gameScore;
	private int blueChips;
	private int redChips;
	private int whiteChips;
	private int lastRoll;
	private int lastRollDie1;
	private int lastRollDie2;
	private String playerName;
	private String playerStatus;
	private int gamesWon;
	private boolean isBot;

	
	//
	// Normal Player constructor, uses default chip counts
	public Player(String thePlayerName, boolean playerIsBot)
	{
		//
		// Default is 50 chips, with this breakdown
		// Blue = 10, Red = 5, White = 1
		this.redChips    = 4;
		this.blueChips   = 2;	
		this.whiteChips  = 10;
		this.gameScore   = 0;
		this.seriesScore = 0;
		this.lastRoll    = 0;
		this.playerName  = thePlayerName;
		this.lastRollDie1 = 0;
		this.lastRollDie2 = 0;
		this.gamesWon     = 0;
		this.isBot        = playerIsBot;
	}
	
	//
	// Player is a bot
	public boolean PlayerIsBot()
	{
		return this.isBot;
	}
	
	//
	// Does Bot Want to Roll
	public boolean BotPlayerWantToRoll()
	{
		boolean rc = false;
		
		if(this.isBot == true) {
			if(this.seriesScore > 0) {
				if((this.seriesScore > 10) || 
				   ((this.gameScore + this.seriesScore) > 100)) {
					rc = false;
				}else {
					if(this.gameScore > 90) {
						rc = false;
					}else {
						rc = true;
					}
				}
			}else {
				//
				// Always roll on first try of seried
				rc = true;
			}
		}
		
		return rc;
	}
	//
	// Player won the game
	public void PlayerWon()
	{
		this.gamesWon += 1;
	}
	//
	// How many games has this player won
	public int GamesWon()
	{
		return this.gamesWon;
	}
	//
	// Grab the players name
	public String GetStatus()
	{
		int totalChips = this.GetTotalChips();
		
		this.playerStatus = this.playerName + ": Score= " + this.gameScore + " Chips: " + totalChips + " (Blue= " + this.blueChips + ",Red= " + this.redChips + ",White= " + this.whiteChips + ")";
		return this.playerStatus;
	}
	
	//
	// Use to clear scores, normally during a new game of
	// a match
	public void ClearScore()
	{
		this.gameScore   = 0;
		this.seriesScore = 0;
	}
	
	//
	// Grab the players name
	public String GetName()
	{
		return this.playerName;
	}
	//
	// Grab the players score
	public int GetScore()
	{
		return this.gameScore;
	}
	//
	// Grab the players chip count
	public int GetTotalChips()
	{
		int chips = (this.blueChips * 10) + (this.redChips * 5) +
													this.whiteChips;
		return chips;
	}
	
	//
	// Start the next Series
	public void StartSeries()
	{
		this.seriesScore = 0;
	}
	//
	// Get the Series Score
	public int GetSeriesScore()
	{
		return this.seriesScore;
	}
	//
	//  Get the Total score of last roll
	public int GetLastRoll()
	{
		return this.lastRoll;
	}
	//
	//  Get the score of last roll, die 1
	public int GetLastRollDie1()
	{
		return this.lastRollDie1;
	}
	//
	//  Get the score of last roll, die 2
	public int GetLastRollDie2()
	{
		return this.lastRollDie2;
	}
	
	//
	// End the current Series
	public void EndSeries()
	{
		this.gameScore += this.seriesScore;
	}
	//
	// Take chips from a players chip stash, to kitty or to winner
	public int TakeChips(int theNumberOfChips) 
	{
		int chipsToReturn;
		int chipsAvailable = this.GetTotalChips();
		//
		// If we don't have enough to cover, return what we have
		// and set chip cache to zero
		if(chipsAvailable > theNumberOfChips) {
			//
			// Iterate through and convert chips until we have enough to
			// cover.
			while(this.whiteChips < theNumberOfChips){
				// Gota convert some chips
				if(this.redChips > 0) {
					this.redChips   -= 1;
					this.whiteChips += 5;
				}else {
					if(this.blueChips > 0) {
						this.blueChips  -= 1;
						this.whiteChips += 10;
					}
				}
			}
			
			this.whiteChips -= theNumberOfChips;
			chipsToReturn    = theNumberOfChips;
		}else {
			chipsToReturn    = chipsAvailable;
			this.blueChips   = this.redChips = this.whiteChips = 0; // Busted
		}
		
		return chipsToReturn;
	}
	
	//
	// Put chips into a players chip stash, from kitty or from losers
	// returns the total chips for this player
	public int PutChips(int theNumberOfChips) 
	{		
		//
		// Grab the Blue Chips first
		while(theNumberOfChips >= 10){
			this.blueChips   += 1;
			theNumberOfChips -= 10;
		}
		//
		// The Red Chips next
		if(theNumberOfChips >= 5){
			this.redChips   += 1;
			theNumberOfChips -= 5;
		}
		//
		// The White Chips last
		this.whiteChips += theNumberOfChips;
		
		return this.GetTotalChips();
	}
	
	//
	// Players Turn, one dice roll, for this series, the return value is the 
	// result of theroll as described below.
	//    RC = 4 (Double Skunk), 2 (Skunk/Deuce), 1 (Single Skunk), 0 (Still Active)
	public int RollDice(Dice theDice)
	{
		int rollResult = 0;
		int aRoll[] = new int[2];
		
		aRoll = theDice.Roll();
		//
		// Save for display purposes, not total
		this.lastRollDie1 = aRoll[0];
		this.lastRollDie2 = aRoll[1];
		
		//
		// If one is a SKUNK...
		if((aRoll[0] == 1) && (aRoll[1] == 1)) {
			rollResult   = 4;
			this.gameScore = 0; // Lost them all
		}else {
			//
			// Not both SKUNKS, but possibly 1 Skunk or
			// 1 skunk and 1 DEUCE
			if((aRoll[0] == 1) || (aRoll[1] == 1)) {
				//
				// At least one is a SKUNK, maybe a DEUCE as well
				if((aRoll[0] == 2) || (aRoll[1] == 2)) {
					//
					// A Skunk and a DEUCE
					rollResult     = 2;
					this.seriesScore = 0;
				}else {
					//
					// Just a Single Skunk here...
					rollResult     = 1;
					this.seriesScore = 0;
				}
				this.lastRoll = 0;
			}else {
				//
				// Just some valid dice here...
				this.lastRoll = aRoll[0] + aRoll[1];
				this.seriesScore += this.lastRoll;
				
			}
		}
				
		return rollResult;
	}	
}