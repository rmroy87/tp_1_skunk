//
// Two Die, used (Rolled) together for a game
public class Dice {
	private Die die1;
	private Die die2;
	private int lastRoll[] = new int[2];
	
	// Normal Dice constructor, this will never use loaded Die
	public Dice()
	{
		this.die1 = new Die();
		this.die2 = new Die();		
	}
	
	// Overloaded Dice constructor, this constructor will allow the
	// use of loaded Die...
	public Dice(Die die1, Die die2)
	{
		this.die1 = die1; // Save the parameter die1 , could be loaded
		this.die2 = die2; // Save the parameter die2 , could be loaded
	}
	
	// Roll the Die and return both die
	public int[] Roll()
	{
		this.lastRoll[0] = this.die1.Roll();
		this.lastRoll[1] = this.die2.Roll();
		
		return this.lastRoll;
	}

}
