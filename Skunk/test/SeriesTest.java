import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SeriesTest {

	@Test
	public void testGetKittyZero() {
		int kitty;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
			
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";
		
		UI ui = new UI();
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to ZERO",
				   (kitty == 0));		
	}
	@Test
	public void testSetKitty25() {
		int kitty;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		UI ui     = new UI();		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		s.SetKitty(25);
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 25",
				   (kitty == 25));		
	}
	
	@Test
	public void testAskPlayerToRoll() {
		boolean prompt;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);	
		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		ui.DisplayMsg("*** Test Yes/No: Press Y first, N on second ***", true);
				
		prompt = s.AskPlayerToRoll(p1);
		
		assertTrue("Prompt not Y",
				   (prompt == true));
		
		prompt = s.AskPlayerToRoll(p2);
		
		assertTrue("Prompt not N",
				   (prompt == false));
	}
	
	@Test
	public void testTurnGood() {
		int status;
		int load1[] = {3};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test (GOOD) Turn: Press Y on first roll, N on second roll ***", true);
		status = s.PlayerTurn(p1);
		
		assertTrue("Prompt Status == 1",
				   (status == 1));
		
	}
	
	@Test
	public void testTurnSkunk() {
		int status;
		int kitty;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test (SKUNK) Turn: Press Y on first roll ***", true);
		status = s.PlayerTurn(p1);
		
		assertTrue("Prompt Status == 1",
				   (status == 1));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 1",
				   (kitty == 1));	
		
	}

	@Test
	public void testTurnSkunkDeuce() {
		int status;
		int kitty;
		int load1[] = {1};
		int load2[] = {2};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);	
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test (SKUNK/DEUCE) Turn: Press Y on first roll ***", true);
		status = s.PlayerTurn(p1);
		
		assertTrue("Prompt Status == 1",
				   (status == 1));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 2",
				   (kitty == 2));	
		
	}
	
	@Test
	public void testTurnDoubleSkunk() {
		int status;
		int kitty;
		int load1[] = {1};
		int load2[] = {1};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test (DOUBLE SKUNK) Turn: Press Y on first roll ***", true);
		status = s.PlayerTurn(p1);
		
		assertTrue("Prompt Status == 1",
				   (status == 1));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 4",
				   (kitty == 4));	
		
	}
	
	@Test
	public void testTurnBankrupt() {
		int status;
		int kitty;
		int load1[] = {1};
		int load2[] = {1};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";		
		UI ui = new UI(virtPrompt, 2);		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		p1.TakeChips(49);
		
		ui.DisplayMsg("*** Test (Bankrupt) Turn: Press Y on first roll ***", true);
		status = s.PlayerTurn(p1);
		
		assertTrue("Prompt Status == -1",
				   (status == -1));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 1",
				   (kitty == 1));	
		
	}
	
	@Test
	public void testNormalSeries() {
		int status;
		int kitty;
		int load1[] = {6};
		int load2[] = {6};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		String[] virtPrompt = new String[4];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";	
		virtPrompt[2] = "Y";
		virtPrompt[3] = "N";
		UI ui = new UI(virtPrompt, 4);		
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test Normal Series: Press Y on first roll, then N ***", true);
		status = s.PlaySeries(1);
		
		assertTrue("Prompt Status == 0",
				   (status == 0));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 0",
				   (kitty == 0));	
		
	}
	
	@Test
	public void testLastSeries() {
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
		virtPrompt[10] = "Y";
		virtPrompt[11] = "N";
		UI ui = new UI(virtPrompt, 12);			
		Player p1 = new Player("Player_1");
		Player p2 = new Player("Player_2");
		
		ArrayList<Player> players = new ArrayList<Player> (2);
		players.add(p1);
		players.add(p2);		
		Series s  = new Series(ui, dice, players);
		
		ui.DisplayMsg("*** Test Last Series: Press Y on first player until over 100, then N ***", true);
		status = s.PlaySeries(1);
		
		assertTrue("Prompt Status == 1",
				   (status == 1));
		
		kitty = s.GetKitty();
		
		assertTrue("Kitty Not equal to 0",
				   (kitty == 0));	
		
	}



}
