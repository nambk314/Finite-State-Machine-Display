package edu.union.adt.view;

import javax.swing.JOptionPane;
import edu.union.adt.fsm.ConcreteFSM;

public class UpdateHandler implements Runnable 
{
	private Display myDisplay;
	private ConcreteFSM myFiniteStateMachine;
	
	public UpdateHandler(Display d, ConcreteFSM f)
	{
		myDisplay = d;
		myFiniteStateMachine = f;
	}
	
	public void run() 
	{
		myDisplay.repaint();
	}
}
