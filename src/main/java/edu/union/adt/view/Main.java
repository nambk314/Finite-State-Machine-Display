package edu.union.adt.view;

import javax.swing.JFrame;
import edu.union.adt.fsm.ConcreteFSM;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;

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
		FileChooser fileChooser = new FileChooser(display, finiteStateMachine);
		JPanel p = new JPanel();
		Container cp = frame.getContentPane();
   		cp.add(p, BorderLayout.SOUTH);

		p.add(fileChooser.getOpen());
		p.add(fileChooser.getSave());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		//finiteStateMachine.addListener(display);
		
		display.go();
	}
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.start();
	}
}
