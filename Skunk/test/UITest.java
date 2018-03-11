import static org.junit.Assert.*;

import org.junit.Test;

public class UITest {

	//@Test -- Used in development, but not automated
	//         so remove from unit test run
	public void testMsgNoNL() {
		UI testUI = new UI();
		
		testUI.DisplayMsg("1 UI Testwith NO LF ", false);
		testUI.DisplayMsg("2 UI Testwith NO LF ", false);
	}
	
	//@Test -- Used in development, but not automated
	//         so remove from unit test run
	public void testMsgWithNL() {
		UI testUI = new UI();
		
		testUI.DisplayMsg("", true);
		testUI.DisplayMsg("1 UI Testwith WITH LF ", true);
		testUI.DisplayMsg("2 UI Testwith WITH LF ", true);
	}
	
	//@Test -- Used in development, but not automated
	//         so remove from unit test run
	public void testMsgYesNoPrompt() {
		UI testUI = new UI();
		boolean prompt;
		
		prompt = testUI.DisplayYesNoPrompt("Please press Y");
		if(prompt == true) {
			testUI.DisplayMsg("---> User Pressed Y", true);
		}else {
			testUI.DisplayMsg("---> User Pressed N", true);
		}
		
	}
	
	//@Test -- Used in development, but not automated
	//         so remove from unit test run
	public void testMenuPrompt() {
		UI testUI = new UI();
		int prompt;
		String[] menuMsgs = new String[3];
		
		menuMsgs[0] = "Select First Option";
		menuMsgs[1] = "Select Second Option";
		menuMsgs[2] = "Select Third  Option";
				
		prompt = testUI.DisplayMenuPrompt("Test Menu 1", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 2", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 3", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 4", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		
		prompt = testUI.DisplayMenuPrompt("Test Menu Q", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		
	}
	
	@Test
	public void testVrirtualMenuPrompt() {
		String[] virtPrompt = new String[6];
		virtPrompt[0] = "1";
		virtPrompt[1] = "2";
		virtPrompt[2] = "3";
		virtPrompt[3] = "4";
		virtPrompt[4] = "5";
		virtPrompt[5] = "Q";
		
		UI testUI = new UI(virtPrompt, 6);
		int prompt;
		String[] menuMsgs = new String[3];
		
		menuMsgs[0] = "Select First Option";
		menuMsgs[1] = "Select Second Option";
		menuMsgs[2] = "Select Third  Option";
				
		prompt = testUI.DisplayMenuPrompt("Test Menu 1", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		assertTrue("Prompt Status != 1", (prompt == 1));
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 2", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		assertTrue("Prompt Status != 1", (prompt == 2));
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 3", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		assertTrue("Prompt Status != 1", (prompt == 3));
		
		prompt = testUI.DisplayMenuPrompt("Test Menu 4", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		assertTrue("Prompt Status != -1", (prompt == -1));
		
		prompt = testUI.DisplayMenuPrompt("Test Menu Q", menuMsgs, 3);		
		testUI.DisplayMsg("---> User Pressed " + prompt, true);
		assertTrue("Prompt Status != -1", (prompt == -1));
		
	}
	
	@Test
	public void testVirtualMsgYesNoPrompt() {
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Y";
		virtPrompt[1] = "N";
		
		UI testUI = new UI(virtPrompt, 2);
		
		boolean prompt;
		
		prompt = testUI.DisplayYesNoPrompt("Please press Y");
		
		assertTrue("Prompt Status == F, should be T",
				   (prompt == true));
		
		prompt = testUI.DisplayYesNoPrompt("Please press N");
		
		assertTrue("Prompt Status == T, should be F",
				   (prompt == false));
		
	}
	
	@Test
	public void testVirtualInputString() {
		String[] virtPrompt = new String[2];
		virtPrompt[0] = "Player_1";
		virtPrompt[1] = "Player_2";
		String[] test = new String[2];
		
		UI testUI = new UI(virtPrompt, 2);	

		
		test[0] = testUI.GetInputString("Please Enter Player Name: ");
		
		assertTrue("Input String != " + virtPrompt[0],
				   (test[0] == virtPrompt[0]));
		
		test[1] = testUI.GetInputString("Please Enter Player Name: ");
		
		assertTrue("Input String != " + virtPrompt[1],
				   (test[1] == virtPrompt[1]));
		
	}

}
