import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetName() {
		String playerName = new String();
		Player p1 = new Player("Player_1");
		
		playerName = p1.GetName();
		if ( playerName == "Player_1")
			System.out.printf("  Get Name: Name = %s, expected = %s\n", 
					playerName, "Player_1");		
		else
			fail("Invalid Return Value");
	}
	
	@Test
	public void testGetScore() {
		int playerScore;
		Player p1 = new Player("Player_1");
		
		playerScore = p1.GetScore();
		if ( playerScore == 0)
			System.out.printf("  Get Score: Score = %d, expected = %d\n", 
					playerScore, 0);		
		else
			fail("Invalid Return Value");
	}
	
	@Test
	public void testGetChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.GetTotalChips();
		if ( playerChips == 50)
			System.out.printf("  GetTotalChips: Chips = %d, expected = %d\n", 
					playerChips, 50);		
		else
			fail("Invalid Return Value");
	}
	
	@Test
	public void testTakeChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.TakeChips(20);
		if (playerChips == 20)
			System.out.printf("  Take (%d) Chips: Chips = %d, Expected = %d, Remain = %d\n", 
					20, playerChips, 20, p1.GetTotalChips() );		
		else
			fail("Invalid Return Value");
		
		playerChips = p1.TakeChips(40);
		if (playerChips == 30)
			System.out.printf("  Take (%d) Chips: Chips = %d, Expected = %d, Remain = %d\n", 
					40, playerChips, 30, p1.GetTotalChips() );		
		else
			fail("Invalid Return Value");		
	}
	
	@Test
	public void testPutChips() {
		int playerChips;
		Player p1 = new Player("Player_1");
		
		playerChips = p1.PutChips(20);
		if (playerChips == 70)
			System.out.printf("  Put (%d) Chips: Chips = %d, Expected = %d, Balance = %d\n", 
					20, playerChips, 70, p1.GetTotalChips() );		
		else
			fail("Invalid Return Value");
		
		playerChips = p1.PutChips(40);
		if (playerChips == 110)
			System.out.printf("  Put (%d) Chips: Chips = %d, Expected = %d, Balance = %d\n", 
					40, playerChips, 110, p1.GetTotalChips() );		
		else
			fail("Invalid Return Value");		
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
		if ( playerTurn == 1)
			System.out.printf("  One Skunk: Turn = %d, expected = %d\n", 
					playerTurn, 1);		
		else
			fail("Invalid Return Value");
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
		if ( playerTurn == 2)
			System.out.printf("  Skunk/DEUCE: Turn = %d, expected = %d\n", 
					playerTurn, 2);		
		else
			fail("Invalid Return Value");
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
		if ( playerTurn == 4)
			System.out.printf("  TWO Skunk: Turn = %d, expected = %d\n", 
					playerTurn,4);		
		else
			fail("Invalid Return Value");
	}

}
