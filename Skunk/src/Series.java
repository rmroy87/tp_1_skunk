import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

//
// This class is to manage one Round or Series (as described in
// the game instructions) of SKUNK.  Each player with Chips,
// will get an opportunity to ROLL the dice.
public class Series {

	private int[] nextSeriesStatus;
	private int numPlayers;
	private ArrayList<Player> players;
	private int kitty;
	private Dice dice;
	private UI ui;
	
	//
	// Normal Player constructor, uses default chip counts
	public Series(UI theUI, Dice theDice, ArrayList<Player> thePlayers)
	{
		players = thePlayers;
		dice    = theDice;
		ui      = theUI;
		
		kitty      = 0;
		numPlayers = players.size();
		nextSeriesStatus = new int[numPlayers];
	}
	//
	// Used after a series completes
	public int GetKitty()
	{
		return this.kitty;
	}
	//
	// Just in-case we want to force kitty values for test
	public void SetKitty(int theNewKitty)
	{
		this.kitty = theNewKitty;
	}
	
	public boolean AskPlayerToRoll(Player thePlayer)
	{
		boolean  prompt;
		
		prompt = this.ui.DisplayYesNoPrompt(thePlayer.GetName() + 
						" Turn: Series Score = " + 
						thePlayer.GetSeriesScore() + 
						", Game Score = " + 
						(thePlayer.GetSeriesScore() + 
								thePlayer.GetScore() ) + 
						", do you want to Roll Dice?");		
		
		return prompt;
	}
	
	//
	// One Skunk turn, on a per player basis
	public int PlayerTurn(Player thePlayer)
	{
		int roll = 0;
		int done = 0;
		int chipsToKitty = 0;
		int nextRollStatus = 1;
		
		thePlayer.StartSeries();
		//
		// A PLayer may not want to roll the dice at all
		while(  (done == 0) && 
				(AskPlayerToRoll(thePlayer) == true)) {
			
			roll = thePlayer.RollDice(this.dice);
			ui.DisplayMsg(	"  Roll = " + 
							thePlayer.GetLastRoll() + 
							" (" + thePlayer.GetLastRollDie1() + "," + 
							thePlayer.GetLastRollDie2() + ") " +
							", Score = " + 
							thePlayer.GetSeriesScore(), true);
			
			//StdOut.println("  Roll = " + thePlayer.GetLastRoll() + ", Score = " + thePlayer.GetSeriesScore());
			//
			// If their Roll is not zero, they cannot 
			if(roll != 0) {
				chipsToKitty = thePlayer.TakeChips(roll);
				this.kitty   += chipsToKitty;				
				
				done = 1;	
				if(roll == 4) {
					ui.DisplayMsg("  *** You rolled a DOUBLE SKUNK...Busted ***", true);
				}else if(roll == 2) {
					ui.DisplayMsg("  *** You rolled a SKUNK/DEUCE...Busted ***", true);
				}else if(roll == 1) {
					ui.DisplayMsg("  *** You rolled a SKUNK...Busted ***", true);
				}
				
				if(roll != chipsToKitty) {
					nextRollStatus = -1;
					ui.DisplayMsg("  *** " + thePlayer.GetName() + " GAME OVER, Out of Chips!! ***", true);
				}
			}
		}
		
		thePlayer.EndSeries();
		
		if(nextRollStatus == 1) {
			if(thePlayer.GetScore() >= 100) {
				nextRollStatus = thePlayer.GetScore();
			}
		}
		
		return nextRollStatus;
	}
	
	//
	// Play the Game
	public int PlaySeries(int seriesNum)
	{
		int lastSeries = 0;
		int i;
		int j;
		int turn;
		Player thePlayer;
		
		ui.DisplayMsg("---------------------------------------------------------------", true);
		ui.DisplayMsg("Skunk Round [ " + seriesNum + "]:", true);
				
		for(i=0;i<this.numPlayers; i++) {
			
			if((this.nextSeriesStatus[i] == 1) || (this.nextSeriesStatus[i] == 0)) {
				thePlayer = this.players.get(i);
				//
				// Make sure player is not bankrupt
				if(thePlayer.GetTotalChips() > 0) {						
					
					ui.DisplayMsg(thePlayer.GetName() + " [Score = " + thePlayer.GetScore() + "] is up:", true);
					
					turn = PlayerTurn(thePlayer);
					if(turn == -1) {
						//
						// This player is out of chips, no more rolls for them
						this.nextSeriesStatus[i] = turn;
					}else if(turn >= 100) {
						//
						// Somebody went out, so all other players with chips get one
						// more roll, setup the next series array
						this.nextSeriesStatus[i] = turn;
						
						for(j=0;j<this.numPlayers;j++) {
							if(j != i) {
								if(this.nextSeriesStatus[j] == 1) {
									this.nextSeriesStatus[j] = 0;
								}
							}
						}
						lastSeries = 1;
						break;
					}else {
						//
						// Still in the game, able to roll the next series.
						this.nextSeriesStatus[i] = turn;
					}
				
					ui.DisplayMsg(thePlayer.GetName() + " [Score = " + thePlayer.GetScore() + "] is done:", true);
					ui.DisplayMsg("---------------------------------------------------------------", true);
				}else {
					ui.DisplayMsg(thePlayer.GetName() + " is BANKRUPT and cannot ROLL", true);
				}
			}
		}
				
		return lastSeries;		
	}
}
