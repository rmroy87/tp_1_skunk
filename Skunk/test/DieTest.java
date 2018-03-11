import static org.junit.Assert.*;

import org.junit.Test;

public class DieTest {

	@Test
	public void testDie() {
		Die d = new Die();
		
		// Should return false as it is not a loaded die
		if(d.getLoadedDie() == true)
			fail("Non-Loaded Die Returns Loaded");		
	}
	@Test
	public void testDieIndex() {
		Die d = new Die();
		
		// Should return false as it is not a loaded die
		if(d.getDieIndex() != 0)
			fail("Non-Loaded Die Should always be ZERO");		
	}
	@Test
	public void testDieLoadedDieRollLength() {
		Die d = new Die();
		
		// Should return false as it is not a loaded die
		if(d.loadedDieRollsLength() != 0)
			fail("Non-Loaded Die Should always be ZERO");		
	}
	@Test
	public void testDieRoll() {
		Die d = new Die();
		int dieRoll;
		
		// Should return false as it is not a loaded die
		dieRoll = d.Roll();
		if((dieRoll < 1) || (dieRoll > 6))
			fail("Non-Loaded Die Should always be ZERO");		
	}
	
	@Test
	public void testLoadedDie() {
		int loads[] = {1,2,3,4,5,6};
		Die d = new Die(loads);
		
		// Should return true as it is a loaded die
		if(d.getLoadedDie() == false)
			fail("Loaded Die Returns Non-Loaded");		
	}
	
	@Test
	public void testLoadedDieIndex() {
		int loads[] = {1,2,3,4,5,6};
		Die d = new Die(loads);
		
		// Should return false as it is not a loaded die
		if(d.getDieIndex() != 0)
			fail("Non-Loaded Die Should always be ZERO");		
	}
	@Test
	public void testLoadedDieLoadedDieRollLength() {
		int loads[] = {1,2,3,4,5,6};
		Die d = new Die(loads);
		
		// Should return false as it is not a loaded die
		if(d.loadedDieRollsLength() != 6)
			fail("Non-Loaded Die Should always be ZERO");		
	}
	@Test
	public void testLoadedDieRoll() {
		int loads[] = {1,2,3,4,5,6};
		Die d = new Die(loads);
		
		// Should return false as it is not a loaded die
		if(d.Roll() != 1)
			fail("Non-Loaded Die Should always be ONE");
		if(d.Roll() != 2)
			fail("Non-Loaded Die Should always be TWO");
		if(d.Roll() != 3)
			fail("Non-Loaded Die Should always be THREE");
		if(d.Roll() != 4)
			fail("Non-Loaded Die Should always be FOUR");
		if(d.Roll() != 5)
			fail("Non-Loaded Die Should always be FIVE");
		if(d.Roll() != 6)
			fail("Non-Loaded Die Should always be SIX");
		if(d.Roll() != 1)
			fail("Non-Loaded Die Should always be ONE");
		
	}

}
