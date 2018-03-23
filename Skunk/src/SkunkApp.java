import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp 
{
	private int numGamePlayers;
	private ArrayList<Player> gamePlayers;
	private int[] nextSeriesStatus;
	private Dice gameDice;
	private UI ui;
	private boolean justOneGame;
	private Game game;
	private int lastGameResult;

	public SkunkApp(UI theUI)
	{
		Die d1 = new Die();
		Die d2 = new Die();
		
		this.gameDice = new Dice(d1, d2);
		this.ui = theUI;		
		this.justOneGame = false;
	}
	//
	// An overloaded contructor to run a default setup
	public SkunkApp(UI theUI, String theP1Name, String theP2Name)
	{
		Player thePlayer;
		Die d1 = new Die();
		Die d2 = new Die();
				
		this.gameDice = new Dice(d1, d2);
		this.ui = theUI;		
		this.justOneGame = true;	
		this.numGamePlayers = 2;
		this.gamePlayers = new ArrayList<Player> (2);
		this.nextSeriesStatus = new int[2];
		
		thePlayer = new Player(theP1Name);
		gamePlayers.add(thePlayer);	
		nextSeriesStatus[0] = 1;
		
		thePlayer = new Player(theP2Name);
		gamePlayers.add(thePlayer);	
		nextSeriesStatus[1] = 1;
		
	}
	
	public int GetNumberOfPlayers()
	{
		return this.numGamePlayers;
	}
	
	public String GetPlayerName(int playerIndex) 
	{
		String retString;
		Player aPlayer;
		
		if(playerIndex < this.numGamePlayers) {
			aPlayer = this.gamePlayers.get(playerIndex);
			retString = aPlayer.GetName();
		}else {
			retString = new String();
			retString = "Invalid Index";
		}
		
		return retString;
	}
	
	public void SetupGame()
	{
		this.game = new Game(this.ui,this.gameDice, this.gamePlayers);
	}
	
	public int PlayOneGame()
	{
		this.lastGameResult = this.game.PlayGame();
		
		return this.lastGameResult;
	}
	
	public void DumpPlayerScores()
	{
		this.game.DumpPlayerScores();
	}
	
	public boolean GetJustOneGameFlag()
	{
		return this.justOneGame;
	}
	//
	// Setup the Match
	public int SetupSkunkMatch()
	{
		int rc = 0;
		int prompt;
		String[] menuMsgs = new String[3];
		
		menuMsgs[0] = "Play one Game of Skunk";
		menuMsgs[1] = "Play games until winner";
		menuMsgs[2] = "Quit";
				
		this.ui.DisplayMsg("********************************************",true);
		this.ui.DisplayMsg("*** Welcome to the game of SKUNK!!  ***", true);
		this.ui.DisplayMsg("********************************************",true);
		
		prompt = this.ui.DisplayMenuPrompt("Skunk Match Options", menuMsgs, 3);		
		
		if(prompt == 2) {
			this.justOneGame = false;
		}else if(prompt == 1) {
			this.justOneGame = true;
		}else {
			rc = -1;
		}		
		
		return rc;
	}
	
	//
	// Setup the Players for the game
	public int SetupPlayers(int theNumGamePlayers)
	{
		int i;
		String newPlayer = new String();
		Player thePlayer;
		int newPlayerStatus = 1;
	
		this.numGamePlayers = theNumGamePlayers;
		gamePlayers = new ArrayList<Player> (theNumGamePlayers);
		nextSeriesStatus = new int[theNumGamePlayers];
		
		for(i=0;i<theNumGamePlayers;i++) {
			newPlayer = this.SetupPlayerName(i+1);
			thePlayer = new Player(newPlayer);
			gamePlayers.add(thePlayer);	
			nextSeriesStatus[i] = newPlayerStatus;
		}		
		
		return theNumGamePlayers;
	}
	
	public int SetupNumPlayers()
	{
		int numPlayers;
		String playerString;
		
		playerString = ui.GetInputString("Please Enter the number of players (2-8): ");
	
		
		if((playerString.charAt(0) > '8') || (playerString.charAt(0) < '2')) {
			numPlayers = 0;
		}else {
			numPlayers = playerString.charAt(0) - '0';
		}
	
		return numPlayers;
	}
	
	public String SetupPlayerName(int index)
	{
		String aPlayerName = new String();
		
		aPlayerName = ui.GetInputString("Please Enter Player [" + 
										index + "] Name: ");
		
		return aPlayerName;
	}


	public void DumpMatchResults(int numPlayers)
	{
		int i;		
		Player thePlayer;
	
		this.ui.DisplayMsg("--------------------------------------", true);
		this.ui.DisplayMsg("--          Match Results           --", true);
		this.ui.DisplayMsg("--------------------------------------", true);
		
		
		for(i=0;i<numPlayers;i++) {
			thePlayer = this.gamePlayers.get(i);
			this.ui.DisplayMsg("  " + thePlayer.GetName() + 
					      " - Chips = " + thePlayer.GetTotalChips(), true);			
		}		
		
		this.ui.DisplayMsg("--------------------------------------", true);
		
	}
	
	public static void main(String[] args)
	{
		int status;
		int numPlayers=0;
		UI ui = new UI();
		SkunkApp match = new SkunkApp(ui);
		
		status = match.SetupSkunkMatch();
		//
		// If they exited game, no work to do
		if(status != -1) {			
				
			numPlayers = match.SetupNumPlayers();
			
			if(match.SetupPlayers(numPlayers) == numPlayers) {
			
				match.SetupGame();
				//
				// Play until all one winner, or a quit is
				// Given
				while(status == 0) {
					match.PlayOneGame();			
										
					match.DumpPlayerScores();
					//
					// Determine if we should play more
					if(match.GetJustOneGameFlag() == false) {
						//
						// Do they want to play another game?
						if(ui.DisplayYesNoPrompt("Play Another game?") == false){
							status = -1;
						}else {
							
						}
					}else {
						status = -1; /* Just Playing one Game */
					}
				}	
			}else {
				ui.DisplayMsg("**** CRITICAL ERROR - Failed to Create Players ****", true);
			}
		}		
		
		match.DumpMatchResults(numPlayers);
		
		ui.DisplayMsg("*************************************", true);
		ui.DisplayMsg("**  Thank You for Playing SKUNK    **", true);
		ui.DisplayMsg("*************************************", true);
		
	}
}
