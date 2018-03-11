package edu.union.adt.view;

import javax.swing.JOptionPane;
import edu.union.adt.fsm.*;

public class UpdateHandler implements Runnable
{
	private Display myDisplay;
	private FSM myFiniteStateMachine;

	public UpdateHandler(Display d, FSM f)
	{
		myDisplay = d;
		myFiniteStateMachine = f;
	}

	public void run()
	{
		myDisplay.repaint();
	}
}
