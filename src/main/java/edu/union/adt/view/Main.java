package edu.union.adt.view;

import javax.swing.JFrame;
import edu.union.adt.fsm.ConcreteFSM;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Main {
	private ConcreteFSM finiteStateMachine;
	private Display display;
	private JFrame frame;
	
	
	public void start()
	{
		finiteStateMachine = new ConcreteFSM ();
		
		frame = new JFrame("Finite State Machine Display 1");
		display = new Display(finiteStateMachine);
		frame.getContentPane().add(display);
		ButtonPanel fileChooser = new ButtonPanel(display, finiteStateMachine);
		SimulatorPanel simulator = new SimulatorPanel(display, finiteStateMachine);
		JPanel p = new JPanel();

		
		FlowLayout layout = new FlowLayout();
		p.setLayout(layout);
		Container cp = frame.getContentPane();
   		cp.add(p, BorderLayout.NORTH);
   		p.add(simulator.getLabel());
   		p.add(simulator.getSimulate());
   		p.add(simulator.getNext());

		p.add(fileChooser.getOpen());
		p.add(fileChooser.getSave());

		

		
		finiteStateMachine.addListener(display);
		display.go();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		System.out.println("go");

	}
	
	public static void main(String[] args)
	{
		Main app = new Main();
		app.start();
	}
}
