/*
 * Created on Jan 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package cs260;
import java.util.Vector;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;

import java.awt.Shape;


import javax.swing.JComponent;
import javax.swing.JOptionPane;



/**
 * @author cassa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FancyDisplay extends JComponent
	implements addCircleListener, MouseListener, KeyListener
{
	private static final long serialVersionUID = 1;
	
	private addCircle game;
	private UpdateHandler myHandler;
	
	private static int WIDTH = 80;
	private static int HEIGHT = 80;
	private int x;
	private int y;
	private boolean blue = false;
	private String Pressed = "";

	private Shape circle = new Ellipse2D.Float(0, 0, 0, 0);
	private Shape arc = new Arc2D.Float(0,0,0,0,0,0,0);
	
	public FancyDisplay(addCircle theGame)
	{
		Font myFont = new Font("TimesRoman", Font.PLAIN, 12);
		this.setFont(myFont);
		FontMetrics metrics = getFontMetrics(myFont);
		
		setSize(new Dimension(WIDTH*4, WIDTH*4));
		setPreferredSize(new Dimension(WIDTH*4, WIDTH*4));
				
		game = theGame;
		
		myHandler = new UpdateHandler(this, game);
	}
	
	
	public void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics;
		
		super.paintComponent(g);
		
		
			Vector<Integer> piece;
			for (int i=0; i< game.length(); i++) {
				piece = game.getPosition(i);
				// if (blue == true) {
				// 	g.setStroke(new BasicStroke(2));
				// 	g.setColor(Color.BLUE);
				// }
				circle = new Ellipse2D.Float(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT);
				// arc = new Arc2D.Float(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT,0, 60, 0);
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.BLACK);
				g.draw(circle);
			
				// g.drawOval(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT);
			}
	}
	public void update()
	{
		new Thread(myHandler).start();
	}
	
	public void go()
	{
		addMouseListener(this);
		addKeyListener(this);
	}
	
	public void mouseClicked(MouseEvent e)
	 {
	 	x = e.getX();
	 	y = e.getY();

	 	if (circle.contains(x, y)) {
	 		blue = true;
	 		repaint();
	 	} 
	 	if (Pressed.equals("E")) {
	 		game.placeCircle(x, y);
	 	}

	 	
	 	
	 }

	 public void keyPressed(KeyEvent e) {
	    System.out.println("Press");
	    switch (e.getKeyCode()){
	        case KeyEvent.VK_E :
	            Pressed = "E";
	            break;
	    }
	}



    public void mouseEntered(MouseEvent e)
     {
     	
     }
     public void mouseExited(MouseEvent e)
     {
     	
     }
     public void mousePressed(MouseEvent e)
     {
     	
     }
     
     public void mouseReleased(MouseEvent e) 
     {
     	
     }

     public void keyEntered(KeyEvent e)
     {
     	
     }
     public void keyExited(KeyEvent e)
     {
     	
     }
     
     public void keyReleased(KeyEvent e) 
     {
     	Pressed = "";
     }

     public void keyTyped(KeyEvent e)
     {
     	
     }


    // public boolean isInsideEllipse(Point point) {
    //     return new Ellipse2D.Float(x, y, width, height).contains(point);
    // }
}
