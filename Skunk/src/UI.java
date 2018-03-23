//import java.util.ArrayList;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

//
// Basic UI used for SKUNK
public class UI {

	private boolean virtualUI;
	private int     virtualIndex;
	private int     virtualResponseCount;
	private String[] virtualPrompts;
	
	
	
	//
	// Overloaded UI to support Virtual YES/NO Prompts
	public UI(String[] theResponses, int theCount)
	{
		this.virtualUI    = true;
		this.virtualIndex = 0;
		this.virtualResponseCount = theCount;
		this.virtualPrompts = theResponses;
	}
	
	//
	// Normal UI constructor, uses real I/O
	public UI()
	{
		this.virtualUI = false;
	}
	
	//
	// Display a message and wait for a prompt
	public int DisplayMenuPrompt(String titleMsg, String[] promptMsgs, int msgCount)
	{
		int result;
		String reply;
		int i;
		
		
		StdOut.println();
		StdOut.println("****************************************");
		StdOut.println(titleMsg);
		
		for(i=0;i<msgCount;i++) {
			StdOut.println(" " + (i+1) + ": " + promptMsgs[i]);
		}
		StdOut.print("Please select choice: ");
		
		reply = GetNextPrompt();	
		
		result = reply.charAt(0) - '0';
		
		StdOut.println();
		//
		// If the user did something stupid, return a -1 to indicate
		// an invalid response
		if((result > msgCount) || (result < 1)){
			result = -1;
		}
		
		return result;
	}
	
	private String GetNextPrompt()
	{
		String prompt = new String();
		
		//
		// If we are a virtual UI used for Unit Testing
		// then fake the response, else get it from User	
		if(this.virtualUI == false) {
			prompt = StdIn.readString();
		}else {
			prompt = this.virtualPrompts[this.virtualIndex];
			StdOut.println(prompt + " (virtual)");
			this.virtualIndex += 1;
			if(this.virtualIndex > this.virtualResponseCount) {
				this.virtualIndex = 0;
			}
		}
		
		return prompt;
	}
	//
	// Display a message and wait for a prompt
	public boolean DisplayYesNoPrompt(String promptMsg)
	{
		boolean result;
		String yesOrNo;
		
		StdOut.print(promptMsg + " (Y or N): ");
		//
		// Get the next prompt, regardless of virtual	
		yesOrNo = GetNextPrompt();		
		
		if((yesOrNo.charAt(0) == 'Y') || (yesOrNo.charAt(0) == 'y')) {
			result = true;
		}else {
			result = false;
		}
		
		return result;
	}
	
	//
	// Display a message with optional line feed
	public void DisplayMsg(String thePromptMsg, boolean newLine)
	{
		StdOut.print(thePromptMsg);
		
		if(newLine == true) {
			StdOut.println();
		}
		
	}
	
	//
	// Get a user input string
	public String GetInputString(String thePromptMsg)
	{
		StdOut.print(thePromptMsg);
		return GetNextPrompt();	
	}
	
}
