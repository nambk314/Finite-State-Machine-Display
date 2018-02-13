package cs260;

import javax.swing.JFrame;

public class Execute {
	private addCircle game;
	private FancyDisplay display, display2;
	private JFrame frame, frame2;
	
	
	public void start()
	{
		game = new addCircle();
		
		frame = new JFrame("Fancy addCircle 1");
		display = new FancyDisplay(game);
		frame.getContentPane().add(display);
		frame.pack();
		frame.setVisible(true);
		
		game.addListener(display);
		
		display.go();
	}
	
	public static void main(String[] args)
	{
		Execute app = new Execute();
		app.start();
	}
}
