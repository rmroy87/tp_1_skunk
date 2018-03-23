import java.util.ArrayList;

import edu.princeton.cs.introcs.StdOut;

//
// This is a Class used to contrl/run one game of a SKUNK Match
// it will need the list of the players, and the dice to be used
public class Game {
	private int numPlayers;
	private ArrayList<Player> players;
	private int kitty;
	private Dice dice;
	private UI ui;
	private Series series;
	private int lastGameWinner;
	
	//
	// Normal Game constructor
	public Game(UI theUI, Dice theDice, ArrayList<Player> thePlayers)
	{
		this.ui = theUI;
		this.dice = theDice;
		this.players = thePlayers;
		this.numPlayers = thePlayers.size();
		this.lastGameWinner = -1;
			
		this.kitty = 0;
	}
	
	//
	// Get the Index for the last game winner
	public int LastGameWinner()
	{
		return this.lastGameWinner;
	}
	
	//
	// Used after a Game completes
	public int GetKitty()
	{
		return this.kitty;
	}
	//
	// Just in-case we start a new Game with chips in kitty
	public void SetKitty(int theNewKitty)
	{
		this.kitty = theNewKitty;
	}
	//
	// After a game is done, handle the kitty payout and 
	// collect the penalties	
	public void HandleGameWinner()
	{
		int i;
		int highScore = 0;
		int highIndex = -1;
		Player thePlayer;
			
		//
		// Find out who has the high score
		for(i=0;i<this.numPlayers; i++) {
			thePlayer = this.players.get(i);
			
			if(thePlayer.GetScore() > highScore) {
				highScore = thePlayer.GetScore();
				highIndex = i;	
			}
		}
		ui.DisplayMsg("***************************************************************", true);
		
		//
		// If all scores are zero, there is no high score
		if( highIndex > -1) {
			//
			// Pay the penalties
			for(i=0;i<this.numPlayers; i++) {
				thePlayer = this.players.get(i);
				
				if(i != highIndex) {
					if(thePlayer.GetScore() == 0) {
						this.kitty += thePlayer.TakeChips(10);
					}else {
						this.kitty += thePlayer.TakeChips(5);
					}
				}
			}
			//
			// Payout the winner
			thePlayer = this.players.get(highIndex);
			thePlayer.PutChips(this.kitty);
			thePlayer.PlayerWon();
			
			ui.DisplayMsg("*** Winner of Game: " + thePlayer.GetName() + 
						  " Game Kitty = " + this.kitty + " Chips ***", true);
			
			this.lastGameWinner = highIndex;
			
			this.kitty = 0;
		}else {
			ui.DisplayMsg("*** NO Winner - All Scores are ZERO ***", true);
		}
		ui.DisplayMsg("***************************************************************", true);
		
	}
	
	//
	// Play the Game
	public int PlayGame()
	{
		int hitLastSeries = 0;
		int seriesNum = 1;
		int i;
		Player thePlayer;
		
		this.kitty = 0;
		
		ui.DisplayMsg("**********************************************************", true);	
		ui.DisplayMsg("***              Playing SKUNK Game                    ***", true);	
		ui.DisplayMsg("**********************************************************", true);	
		
		this.series = new Series(this.ui, this.dice, this.players);
		
		//
		// Clear out all players scores, in case we are the
		// later games in a match
		for(i=0;i<this.numPlayers;i++) {
			thePlayer = this.players.get(i);
			thePlayer.ClearScore();
		}
		
		while(hitLastSeries == 0) {
			this.series.SetKitty(0); 
			hitLastSeries = this.series.PlaySeries(seriesNum);
			this.kitty    += this.series.GetKitty();
			
			ui.DisplayMsg("End of Round[" + seriesNum + 
						  "]: Kitty = " + this.kitty + " Chips", true);
			
			DumpPlayerScores();
			seriesNum += 1;
		}
		ui.DisplayMsg(" ", true);
		ui.DisplayMsg("*** Last Roll for All Players! ***", true);	
		ui.DisplayMsg(" ", true);
		
		this.series.SetKitty(0);
		this.series.PlaySeries(seriesNum);
		this.kitty    += this.series.GetKitty();
		
		ui.DisplayMsg(" ", true);
		ui.DisplayMsg("Final Game Kity (Before Penalties) = " + 
						this.kitty + " Chips ", true);
		//
		// Game over, handle the winner, dole out the kitty, and penalize the
		// Losers...
		this.HandleGameWinner();	
		
		DumpPlayerScores();
		
		
		return seriesNum;
	}
	
	//
	// Dump all players scores, use between rounds
	public void DumpPlayerScores()
	{
		int i;
		Player thePlayer;
		ui.DisplayMsg("---------------------------------------------------------",true);
		ui.DisplayMsg("Player Scores:", true);
		
		for(i=0;i<this.numPlayers; i++) {
			thePlayer = this.players.get(i);
			StdOut.println("  " + thePlayer.GetStatus());
		}
		
		ui.DisplayMsg("---------------------------------------------------------",true);	
	}
	
}
