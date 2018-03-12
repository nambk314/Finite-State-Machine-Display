package edu.union.adt.view;

import javax.swing.JFrame;
import edu.union.adt.fsm.ConcreteFSM;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
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
		ButtonPanel fileChooser = new ButtonPanel(display);
		SimulatorPanel simulator = new SimulatorPanel(display);
		ThemePanel themePanel = new ThemePanel(display);
		JPanel p = new JPanel();
		JPanel p2 = new JPanel();
		JLabel intrustion = new JLabel();
		String text = "<html>" +"HOW TO USE FSM DISPLAY:<br>S: create node <br>E: Click on the node/edge to edit the label <br>T: create edge (Click on 1 node and then click on the other node to create edge) <br>C: nothing <br>D: drag <br>A: change accepting node <br>H: set the start node <br> <br>Press the key and follow with the mouse click behavior to control the FSM <br>(EX: Click S and then click on the screen => create a node)<br>"+ "<html>";
		intrustion.setText(text);
		p2.add(intrustion);
		// JPanel p2 = new JPanel();
		// Container cp2 = frame.getContentPane();
		// cp2.add(p2, BorderLayout.NORTH);
		// p2.add(simulator.getLabel());
		
		FlowLayout layout = new FlowLayout();
		p.setLayout(layout);
		Container cp = frame.getContentPane();
   		cp.add(p, BorderLayout.NORTH);
   		cp.add(p2, BorderLayout.SOUTH);
   		//Simulator
   		p.add(simulator.getLabel());
   		p.add(simulator.getSimulate());
   		p.add(simulator.getNext());
   		p.add(simulator.getAccept());
   		

   		//Theme
   		p.add(themePanel.getLabel());
   		p.add(themePanel.getShapeMenu());
   		p.add(themePanel.getColorMenu());
   		p.add(themePanel.getOK());
   		//Open and Save
   		p.add(fileChooser.getReset());
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
