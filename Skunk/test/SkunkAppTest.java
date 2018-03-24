import static org.junit.Assert.*;

import org.junit.Test;

public class SkunkAppTest {

	@Test
	public void testSetupMatchQuit() {
		int match;
		String[] virtPrompt = new String[3];
		virtPrompt[0] = "4";
		
		UI ui = new UI(virtPrompt, 1);	
		
		SkunkApp s = new SkunkApp(ui);
		
		match = s.SetupSkunkMatch();
		
		assertTrue("Match Status != -1",
				   (match == -1));
		
	}
	@Test
	public void testSetupMatchRules() {
		int match;
		String[] virtPrompt = new String[3];
		virtPrompt[0] = "3";
		virtPrompt[1] = "4";
		
		UI ui = new UI(virtPrompt, 2);	
		
		SkunkApp s = new SkunkApp(ui);
		
		match = s.SetupSkunkMatch();
		
		assertTrue("Match Status != -1",
				   (match == -1));
		
	}
	
	@Test
	public void testSetupMatchOneGame() {
		int match;
		String[] virtPrompt = new String[1];
		virtPrompt[0] = "1";
		
		UI ui = new UI(virtPrompt, 1);	
		
		SkunkApp s = new SkunkApp(ui);
		
		match = s.SetupSkunkMatch();
		
		assertTrue("Match Status != 0",
				   (match == 0));
		
		assertTrue("Just one game == false",
				   (s.GetJustOneGameFlag() == true));
		
	}
	
	@Test
	public void testSetupMatchWinner() {
		int match;
		String[] virtPrompt = new String[1];
		virtPrompt[0] = "2";
		
		UI ui = new UI(virtPrompt, 1);	
		
		SkunkApp s = new SkunkApp(ui);
		
		match = s.SetupSkunkMatch();
		
		assertTrue("Match Status != 0",
				   (match == 0));
		
		assertTrue("Just one game == true",
				   (s.GetJustOneGameFlag() == false));
		
	}
	
	@Test
	public void testSetupNumberPlayer() {
		int numPlayers;
		String[] virtPrompt = new String[5];
		virtPrompt[0] = "0";
		virtPrompt[1] = "2";
		virtPrompt[2] = "4";
		virtPrompt[3] = "8";
		virtPrompt[4] = "9";
		
		UI ui = new UI(virtPrompt, 5);	
		
		SkunkApp s = new SkunkApp(ui);
		// Bomb out with 0
		numPlayers = s.SetupNumPlayers();		
		assertTrue("Number Players != 0",
				   (numPlayers == 0));
		// Should work with 2
		numPlayers = s.SetupNumPlayers();		
		assertTrue("Number Players != 2",
				   (numPlayers == 2));
		// Should work with 4
		numPlayers = s.SetupNumPlayers();		
		assertTrue("Number Players != 4",
				   (numPlayers == 4));
		// Should work with 8
		numPlayers = s.SetupNumPlayers();		
		assertTrue("Number Players != 8",
				   (numPlayers == 8));
		// Bomb out with 9
		numPlayers = s.SetupNumPlayers();		
		assertTrue("Number Players != 0",
				   (numPlayers == 0));				
	}
	
	@Test
	public void testSetupPlayerNames() {
		int numPlayers;
		String[] virtPrompt = new String[4];
		virtPrompt[0] = "Player1";
		virtPrompt[1] = "N";
		virtPrompt[2] = "Player2";
		virtPrompt[3] = "N";
		String[] test = new String[3];
		
		UI ui = new UI(virtPrompt, 4);	
		
		SkunkApp s = new SkunkApp(ui);
	
		assertTrue("Number Players != 0",
				   (s.GetNumberOfPlayers() == 0));
		
		numPlayers = s.SetupPlayers(2);
		// Should be 2 players
		assertTrue("Number Players != 2",
				   (numPlayers == 2));
		// Should be 2 players		
		assertTrue("Number Players != 2",
				   (s.GetNumberOfPlayers() == 2));
		
		test[0] =  s.GetPlayerName(0);
		assertTrue("Player 1 Name != Player1",
				   (test[0] == virtPrompt[0]));
		test[1] =  s.GetPlayerName(1);
		assertTrue("Player 2 Name != Player2",
				   (test[1] == virtPrompt[2]));
		test[2] =  s.GetPlayerName(2);
		assertTrue("Player 3 Name != Invalid Index",
				   (test[2] == "Invalid Index"));
		
		
	}
	


	

}
