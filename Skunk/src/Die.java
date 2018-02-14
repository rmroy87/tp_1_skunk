
public class Die 
{
	private int dieIndex;
	private boolean loadedDie;
	private int loadedDieRolls[];
	
	// Normal Die constructor, non-loaded die
	public Die()
	{
		this.dieIndex  = 0;
		this.loadedDie = false;
		this.loadedDieRolls = new int[0];
	}
	
	// Overloaded constructor to create "loaded" die
	public Die(int[] loadedRolls)
	{
		this.dieIndex  = 0;
		
		if(loadedRolls.length > 0){
			this.loadedDieRolls = new int[loadedRolls.length];
			
			for (int i=0; i<loadedRolls.length; i++)
				this.loadedDieRolls[i] = loadedRolls[i];
			
			this.loadedDie = true;
		}else {
			this.loadedDie = false;
		}
	}
	// This is the roll, value of the die is returned
	public int Roll()
	{
		int dieVal;
		
		if(this.loadedDie == true) {
			dieVal = this.loadedDieRolls[this.dieIndex];
			this.dieIndex = this.dieIndex + 1;
			if(this.dieIndex >= this.loadedDieRolls.length) {
				this.dieIndex = 0;
			}
		}else {
			// not loaded, return a sudo random number
			dieVal = (int) (Math.random() * 6 + 1);
		}
		
		return dieVal;
	}
	
	// Index into loaded die array, this is the index that will
	// be used next Roll()
	public int getDieIndex()
	{
		return this.dieIndex;
	}
	
	// Are we using loaded die?
	public boolean getLoadedDie()
	{
		return this.loadedDie;
	}
	
	// How many elements in the loadedDieRolls[]
	public int loadedDieRollsLength()
	{
		return this.loadedDieRolls.length;
	}
}
