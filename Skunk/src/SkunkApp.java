import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

public class SkunkApp 
{
	private int numGamePlayers;
	private ArrayList<Player> gamePlayers;
	private int[] nextSeriesStatus;
	private Dice gameDice;
	private int gameKitty;
	private UI ui;
	private boolean justOneGame;
	private Game game;
	private int lastGameResult;

	public SkunkApp(UI theUI)
	{
		Die d1 = new Die();
		Die d2 = new Die();
		
		gameDice = new Dice(d1, d2);
		ui = theUI;
		
		justOneGame = false;
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


	
	public static void main(String[] args)
	{
		int status;
		int gameResult;
		int howManyPlayers;
		UI ui = new UI();
		Game game;
		SkunkApp match = new SkunkApp(ui);
		
		status = match.SetupSkunkMatch();
		//
		// If they exited game, no work to do
		if(status != -1) {
			howManyPlayers = match.SetupNumPlayers();
			
			if(howManyPlayers >= 2){
				match.SetupPlayers(howManyPlayers);
				
				match.SetupGame();
				//
				// Play until all one winner, or a quit is
				// Given
				while(status == 0) {
					gameResult = match.PlayOneGame();			
										
					match.DumpPlayerScores();
					//
					// Determine if we should play more
					if(match.GetJustOneGameFlag() == false) {
						//
						// Do they want to play another game?
						if(ui.DisplayYesNoPrompt("Play Another game?") == false){
							status = -1;
						}
					}else {
						status = -1; /* Just Playing one Game */
					}
				}
			}			
		}		
		
		StdOut.println("*************************************");
		StdOut.println("**  Thank You for Playing SKUNK    **");
		StdOut.println("*************************************");
		
	}
}
