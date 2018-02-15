import static org.junit.Assert.*;

import org.junit.Test;

public class DiceTest {

	
	@Test  
	public void testLoadedDice1() {
		int load1[] = {1};
		int load2[] = {2};
		
		Die die1 = new Die(load1);
		Die die2 = new Die(load2);
		
		Dice dice = new Dice(die1, die2);
		int diceRoll[];		
		
		System.out.printf("Dice - Loaded Dice (1,2) Test\n");
		
		for (int i=0; i<5; i++) {
			diceRoll = dice.Roll();
			
			if(diceRoll[0] != 1) {
				fail("Illegal Die 1 Value");
			}
			
			if(diceRoll[1] != 2) {
				fail("Illegal Die 2 Value");
			}
			
			System.out.printf("  Rol[%d]: Die 1 = %d, Die 2 = %d\n", i, 
					diceRoll[0], diceRoll[1]);			
		}		
	}
	
	@Test  
	public void testLoadedDice2() {
		int load1[] = {1,2,3};
		int load2[] = {4,5,6};
		int index = 0;
		
		Die die1 = new Die(load1);
		Die die2 = new Die(load2);
		
		Dice dice = new Dice(die1, die2);
		int diceRoll[];		
		
		System.out.printf("Dice - Loaded Dice2 ({1,2,3},{4,5,6}) Test\n");
		
		for (int i=0; i<5; i++) {
			diceRoll = dice.Roll();
			
			if(diceRoll[0] != load1[index]) {
				fail("Illegal Die 1 Value");
			}
			
			if(diceRoll[1] != load2[index]) {
				fail("Illegal Die 2 Value");
			}
			
			index = index + 1;
			if(index >= load1.length) {
				index = 0;
			}
			
			System.out.printf("  Rol[%d]: Die 1 = %d, Die 2 = %d\n", i, 
					diceRoll[0], diceRoll[1]);			
		}		
	}
	
	@Test  
	public void testLoadedDice3() {
		int load1[] = {1};
		
		int index = 0;
		
		Die die1 = new Die(load1);
		Die die2 = new Die();
		
		Dice dice = new Dice(die1, die2);
		int diceRoll[];		
		
		System.out.printf("Dice - Loaded Dice2 ({1},Non-loaded)) Test\n");
		
		for (int i=0; i<5; i++) {
			diceRoll = dice.Roll();
			
			if(diceRoll[0] != load1[index]) {
				fail("Illegal Die 1 Value");
			}
			
			if(diceRoll[1] < 1 || diceRoll[1] > 6) {
				fail("Illegal Die 2 Value");
			}
			
			index = index + 1;
			if(index >= load1.length) {
				index = 0;
			}
			
			System.out.printf("  Rol[%d]: Die 1 = %d, Die 2 = %d\n", i, 
					diceRoll[0], diceRoll[1]);			
		}		
	}
	
	
	
	@Test  
	public void testDice() {
		Dice d = new Dice();
		int diceRoll[];		
		
		System.out.printf("Dice - Non-Loaded Dice Test\n");
		
		for (int i=0; i<5; i++) {
			diceRoll = d.Roll();
			
			if(diceRoll[0] < 1 || diceRoll[0] > 6) {
				fail("Illegal Die 1 Value");
			}
			if(diceRoll[1] < 1 || diceRoll[1] > 6) {
				fail("Illegal Die 2 Value");
			}
			
			System.out.printf("  Rol[%d]: Die 1 = %d, Die 2 = %d\n", i, 
					diceRoll[0], diceRoll[1]);			
		}		
	}
}
