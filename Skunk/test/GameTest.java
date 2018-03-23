import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class GameTest {

	@Test
	public void testGetKittyZero() {
		int kitty;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);		
		UI ui = new UI();
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Game g  = new Game(ui, dice, players);
		
		kitty = g.GetKitty();
		
		assertTrue("Kitty Not equal to ZERO",
				   (kitty == 0));		
	}
	
	@Test
	public void testSetKitty32() {
		int kitty;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);		
		UI ui = new UI();
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Game g  = new Game(ui, dice, players);
		
		kitty = g.GetKitty();
		
		assertTrue("Kitty Not equal to ZERO",
				   (kitty == 0));
		
		g.SetKitty(32);
		kitty = g.GetKitty();
		assertTrue("Kitty Not equal to 32",
				   (kitty == 32));
	}
	
	@Test
	public void testDumpPlayerScores() {
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);		
		UI ui = new UI();
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Game g  = new Game(ui, dice, players);
		
		g.DumpPlayerScores();		
	}
	
	@Test
	public void testHandleWinnerNoWinner() {
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);		
		UI ui = new UI();
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Game g  = new Game(ui, dice, players);
		
		g.HandleGameWinner();		
	}
	
	
	
	@Test
	public void testGameWithP2Zero() {
		int status;
		int kitty;
		int load1[] = {6};
		int load2[] = {6};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[12];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "Y";	
		virtPrompt[2] = "Y";
		virtPrompt[3] = "Y";
		virtPrompt[4] = "Y";
		virtPrompt[5] = "Y";	
		virtPrompt[6] = "Y";
		virtPrompt[7] = "Y";
		virtPrompt[8] = "Y";
		virtPrompt[9] = "Y";
		virtPrompt[10] = "N";
		virtPrompt[11] = "N";
		UI ui = new UI(virtPrompt, 12);			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);
		
		Game g  = new Game(ui, dice, players);
		
		ui.DisplayMsg("*** Test Game: Press Y on first player until over 100, then N ***", true);

		status = g.PlayGame();
		
		assertTrue("Game Series Count != 2",
				   (status == 2));
		
		kitty = g.GetKitty();
		
		assertTrue("Kitty Not equal to 0",
				   (kitty == 0));
		
		assertTrue("Player Chips Not P1=60,P2=40",
		        ((p1.GetTotalChips() == 60) && 
				 (p2.GetTotalChips() == 40)));
		
	}
	
	@Test
	public void testGameWithP2NotZero() {
		int status;
		int kitty;
		int load1[] = {6};
		int load2[] = {6};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[12];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "Y";	
		virtPrompt[2] = "Y";
		virtPrompt[3] = "Y";
		virtPrompt[4] = "Y";
		virtPrompt[5] = "Y";	
		virtPrompt[6] = "Y";
		virtPrompt[7] = "Y";
		virtPrompt[8] = "Y";
		virtPrompt[9] = "N";
		virtPrompt[10] = "Y";
		virtPrompt[11] = "N";
		UI ui = new UI(virtPrompt, 12);			
		Player p1 = new Player("Player_1", false);
		Player p2 = new Player("Player_2", false);
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);
		
		Game g  = new Game(ui, dice, players);
		
		ui.DisplayMsg("*** Test Game: Press Y on first player until over 100, then N ***", true);

		status = g.PlayGame();
		
		assertTrue("Game Series Count != 2",
				   (status == 2));
		
		kitty = g.GetKitty();
		
		assertTrue("Kitty Not equal to 0",
				   (kitty == 0));
		
		assertTrue("Player Chips Not P1=55,P2=45",
				        ((p1.GetTotalChips() == 55) && 
						 (p2.GetTotalChips() == 45)));
		
	}



}
