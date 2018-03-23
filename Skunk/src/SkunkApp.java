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
	
	public void DumpSkunkRules()
	{
		this.ui.DisplayMsg("********************************************",true);
		this.ui.DisplayMsg("***     The Rules of the SKUNK game!!    ***", true);
		this.ui.DisplayMsg("********************************************",true);
		this.ui.DisplayMsg(" ", true);
		this.ui.DisplayMsg("DIRECTIONS FOR PLAYING:", true);

		this.ui.DisplayMsg("The object of the game is to accumulate a score of ", true);
		this.ui.DisplayMsg("100 points or more. A score is made by rolling the ", true);
		this.ui.DisplayMsg("dice and combining the points on the two dice. ", true);
		this.ui.DisplayMsg("For example: A 4 and 5 would be 9 points - if  ", true);
		this.ui.DisplayMsg("the player decides to take another roll of the  ", true);
		this.ui.DisplayMsg("dice and turns up a 3 and 5 (8 points), he would then  ", true);
		this.ui.DisplayMsg("have an accumulated total of 17 points for the two rolls. ", true);
		this.ui.DisplayMsg("The player has the privilege of continuing to shake to  ", true);
		this.ui.DisplayMsg("increase his score or of passing the dice to wait for the ", true);
		this.ui.DisplayMsg("next series, thus preventing thepossibility of rolling a", true);
		this.ui.DisplayMsg("Skunk and losing his score.", true);
		this.ui.DisplayMsg(" ", true);
		this.ui.DisplayMsg("PENALTIES:", true);

		this.ui.DisplayMsg("A skunk in any series voids the score for that series ", true);
		this.ui.DisplayMsg("only and draws a penalty of 1 chip placed in the kitty," , true);
		this.ui.DisplayMsg("and loss of dice.", true);
		this.ui.DisplayMsg(" ", true);
		this.ui.DisplayMsg("A skunk and a deuce voids the score for that series only" , true); 
		this.ui.DisplayMsg("and draws a penalty of 2 chips placed in the \"kitty\", and ",true);
		this.ui.DisplayMsg("loss of dice.", true);
		this.ui.DisplayMsg(" ", true);
		this.ui.DisplayMsg("TWO skunks void the ENTIRE accumulated score and draws a ", true);
		this.ui.DisplayMsg("penalty of 4 chips placed in the \"kitty\", and loss of dice.", true);
		this.ui.DisplayMsg("Player must again start to score from scratch.",true);
		this.ui.DisplayMsg(" ", true);

		
		this.ui.DisplayMsg("********************************************",true);		
	}
	//
	// Setup the Match
	public int SetupSkunkMatch()
	{
		int rc = 0;
		int prompt;
		String[] menuMsgs = new String[4];
		
		menuMsgs[0] = "Play one Game of Skunk";
		menuMsgs[1] = "Play games until winner";
		menuMsgs[2] = "Review the rules for Skunk game";
		menuMsgs[3] = "Quit";
				
		this.ui.DisplayMsg("********************************************",true);
		this.ui.DisplayMsg("***   Welcome to the game of SKUNK!!    ***", true);
		this.ui.DisplayMsg("********************************************",true);
		
		do {
			prompt = this.ui.DisplayMenuPrompt("Skunk Match Options", menuMsgs, 4);		
		
			if(prompt == 3) {
				this.DumpSkunkRules();
			}
		}while(prompt == 3);
			
		
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
					      " - Chips = " + thePlayer.GetTotalChips() +
					      " Games Won = " + thePlayer.GamesWon(), true);			
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
			
			if(numPlayers > 0) {
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
			}else {
				ui.DisplayMsg("**** CRITICAL ERROR - Invalid Number of Players ****", true);
			}
		}		
		
		match.DumpMatchResults(numPlayers);
		
		ui.DisplayMsg("*************************************", true);
		ui.DisplayMsg("**  Thank You for Playing SKUNK    **", true);
		ui.DisplayMsg("*************************************", true);
		
	}
}
