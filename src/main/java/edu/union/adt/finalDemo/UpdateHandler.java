package cs260;

import javax.swing.JOptionPane;

public class UpdateHandler implements Runnable 
{
	private FancyDisplay myDisplay;
	private addCircle myGame;
	
	public UpdateHandler(FancyDisplay d, addCircle g)
	{
		myDisplay = d;
		myGame = g;
	}
	
	public void run() 
	{
		myDisplay.repaint();
	}
}
