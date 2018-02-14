package edu.union.adt.view;

import javax.swing.JFrame;
import edu.union.adt.fsm.ConcreteFSM;
public class Main {
	private ConcreteFSM finiteStateMachine;
	private Display display, display2;
	private JFrame frame, frame2;
	
	
	public void start()
	{
		finiteStateMachine = new ConcreteFSM ();
		
		frame = new JFrame("Finite State Machine Display 1");
		display = new Display(finiteStateMachine);
		frame.getContentPane().add(display);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		finiteStateMachine.addListener(display);
		
		display.go();
	}
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.start();
	}
}
