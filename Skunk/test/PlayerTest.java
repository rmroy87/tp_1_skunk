import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testGetName() {
		String playerName = new String();
		Player p1 = new Player("Player_1");
		
		playerName = p1.GetName();
		assertTrue("Player Name is not Player_1",
				   (playerName == "Player_1"));		
	}
	
	@Test
	public void testGetScore() {
		int playerScore;
		Player p1 = new Player("Player_1");
		
		playerScore = p1.GetScore();
		assertTrue("Player Score is not 0",
				   (playerScore == 0));		
		
	}
	
	@Test
	public void testGetChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.GetTotalChips();
		assertTrue("Player Chips are not 50",
				   (playerChips == 50));			
	}
	
	@Test
	public void testTakeChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.TakeChips(20);
		assertTrue("Player Chips are not 20",
				   (playerChips == 20));		
		
		playerChips = p1.TakeChips(40);
		assertTrue("Player Chips are not 30",
				   (playerChips == 30));		
	}
	
	@Test
	public void testPutChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.PutChips(20);
		assertTrue("Player Chips are not 70",
				   (playerChips == 70));		
		
		playerChips = p1.PutChips(40);
		assertTrue("Player Chips are not 110",
				   (playerChips == 110));				
	}
	
	
	@Test
	public void testRollDiceOneSkunk() {
		int playerTurn;
		int load1[] = {1};
		int load2[] = {3};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		Player p1 = new Player("Player_1");
		
		playerTurn = p1.RollDice(dice);
		assertTrue("Player Roll Loaded Die is not SKUNK",
				   (playerTurn == 1));		
	}
	
	@Test
	public void testRollDiceOneSkunkDuece() {
		int playerTurn;
		int load1[] = {1};
		int load2[] = {2};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		Player p1 = new Player("Player_1");
		
		playerTurn = p1.RollDice(dice);
		assertTrue("Player Roll Loaded Die is not SKUNK/DEUCE",
				   (playerTurn == 2));
	}
	
	@Test
	public void testRollDiceTwoSkunk() {
		int playerTurn;
		int load1[] = {1};
		int load2[] = {1};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		Player p1 = new Player("Player_1");	
		
		playerTurn = p1.RollDice(dice);
		assertTrue("Player Roll Loaded Die is not TWO SKUNK",
				   (playerTurn == 4));
	}

	@Test
	public void testRollDiceGoodRoll() {
		int playerTurn;
		int playerScore;
		int load1[] = {3};
		int load2[] = {4};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		Player p1 = new Player("Player_1");	
		
		playerTurn = p1.RollDice(dice);
		assertFalse("Player Roll Loaded Die is not Good Roll",
				   (playerTurn != 0));		
	
		p1.EndSeries();
		playerScore = p1.GetScore();
		assertTrue("Player Roll Loaded Die is not Score of 7",
				   (playerScore == 7));
		
	}
	
	@Test
	public void testRollDiceGoodRollDie1Die2() {
		int playerTurn;
		int load1[] = {3};
		int load2[] = {4};
		Die die1  = new Die(load1);
		Die die2  = new Die(load2);		
		Dice dice = new Dice(die1, die2);
		Player p1 = new Player("Player_1");	
		
		playerTurn = p1.RollDice(dice);
		assertFalse("Player Roll Loaded Die is not Good Roll",
				   (playerTurn != 0));
		
	
		assertFalse("Player Die 1 Not 3", p1.GetLastRollDie1() != 3);
		
		assertFalse("Player Die 2 Not 4", p1.GetLastRollDie2() != 4);
			
	}
	
	@Test
	public void testGetStatus() {
		String playerStatus;// = new String();
		String playerTest = new String("Player_1: Score= 0 Chips: 50 (Blue= 2,Red= 4,White= 10)");
		Player p1 = new Player("Player_1");
		
		playerStatus = p1.GetStatus();
		
		System.out.printf("  Get Status--> %s, len=%d\n", 
				playerStatus, playerStatus.length());
		System.out.printf("  Get Status--> %s, len=%d\n", 
				playerTest, playerTest.length());
		
		assertTrue("Player Status is not as expected",
				   (playerStatus.compareTo(playerTest) == 0));
		
		
	}

}
