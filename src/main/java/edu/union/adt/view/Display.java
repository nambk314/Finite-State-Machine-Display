/*
 * Created on Jan 18, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.union.adt.view;
import java.util.Vector;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import java.awt.Shape;


import javax.swing.JComponent;
import javax.swing.JOptionPane;

//For keyevent
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;

import edu.union.adt.fsm.*;
import java.util.ArrayList;

/**
 * @author cassa
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Display extends JComponent
	implements addFSMListener, MouseListener, MouseMotionListener
{
	private static final long serialVersionUID = 1;
	//Finite state machine
	private ConcreteFSM finiteStateMachine;
	private UpdateHandler myHandler;
	
	//defaut WIDTH and HEIGHT for the circle
	private static int WIDTH = 30;
	private static int HEIGHT = 30;

	//Keep track of coordinates
	private int x;
	private int y;

	//Keep track of the key being pressed
	private String Pressed = "";

	private ViewNode selectedNode = null;
	private ViewEdge selectedEdge = null;

	//Keep track of viewNode and edgeNode
	private ArrayList<ViewNode> viewNodeList = new ArrayList<ViewNode>();
	private ArrayList<ViewEdge> viewEdgeList = new ArrayList<ViewEdge>();

	//count number of time T being press
	private int tCount = 1;
	private double[] T1= new double[2];
	private double[] T2= new double[2];
	private Node fromNode;
	private Node toNode;

	//Variable to know if it is inDrag or not
	private boolean inDrag = false;
	//Variable to know the distance went dragging
	private double distanceX = 0;
	private double distanceY = 0;
	
	public Display(ConcreteFSM theFiniteStateMachine)
	{
		Font myFont = new Font("TimesRoman", Font.PLAIN, 12);
		this.setFont(myFont);
		FontMetrics metrics = getFontMetrics(myFont);
		
		setSize(new Dimension(400, 400));
		setPreferredSize(new Dimension(400, 400));
				
		finiteStateMachine = theFiniteStateMachine;
		
		myHandler = new UpdateHandler(this, finiteStateMachine);
		addMouseListener(this);
		addMouseMotionListener(this);
		buttonActions();

	}
	
	//Paint the graphics
	public void paintComponent(Graphics graphics)
	{
		Graphics2D g = (Graphics2D) graphics;
		
		super.paintComponent(g);
		
			ViewNode piece;
			for (int i = 0; i < viewNodeList.size(); i++) {
				piece = viewNodeList.get(i);
				// Ellipse2D circle = new Ellipse2D.Float(x + WIDTH, y - WIDTH, WIDTH, HEIGHT);
				Ellipse2D circle = piece.getCircle();
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.BLACK);
				g.draw(circle);
			}
			ViewEdge egdePiece;
			for (int i=0; i < viewEdgeList.size(); i++) {


				egdePiece = viewEdgeList.get(i);
				Line2D line = egdePiece.getLine();
				Path2D path = egdePiece.getPath();
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.BLACK);
				g.draw(line);
				g.draw(path);
			}

			if (selectedNode != null && Pressed.equals("D")) {
				Ellipse2D circle = selectedNode.getCircle();
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.BLACK);
				g.draw(circle);
			}


//Function to draw the State and transition to Blue
			// if (selectedNode != null) {
			// 	Ellipse2D circle = selectedNode.getCircle();
			// 	g.setStroke(new BasicStroke(2));
			// 	g.setColor(Color.BLUE);
			// 	g.draw(circle);
			// }

			// if (selectedEdge != null) {
			// 	Line2D line = selectedEdge.getLine();
			// 	g.setStroke(new BasicStroke(2));
			// 	g.setColor(Color.BLUE);
			// 	g.draw(line);
			// }

			// Vector<Integer> piece;
			// for (int i=0; i< finiteStateMachine.length(); i++) {
			// 	piece = finiteStateMachine.getPosition(i);
			// 	// if (blue == true) {
			// 	// 	g.setStroke(new BasicStroke(2));
			// 	// 	g.setColor(Color.BLUE);
			// 	// }
			// 	circle = new Ellipse2D.Float(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT);
			// 	// arc = new Arc2D.Float(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT,0, 60, 0);
			// 	g.setStroke(new BasicStroke(2));
			// 	g.setColor(Color.BLACK);
			// 	g.draw(circle);
			
			// 	// g.drawOval(piece.get(0).intValue(), piece.get(1).intValue(), WIDTH, HEIGHT);
			// }
	}
	public void update()
	{
		new Thread(myHandler).start();
	}
	
	public void go()
	{
		addMouseListener(this);
	}

	//Handling the keyboard actions
		public void buttonActions() {
		InputMap In = getInputMap(WHEN_IN_FOCUSED_WINDOW);
	    ActionMap Ac = getActionMap();

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "pressed S");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "released S");

	    Ac.put("pressed S", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "S";
	                System.out.println("Pressed S");
	            }
	        });

	    Ac.put("released S", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released S");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, false), "pressed C");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, true), "released C");
	    Ac.put("pressed C", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "C";
	                System.out.println("Pressed C");
	            }
	        });

	    Ac.put("released C", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released C");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "pressed E");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true), "released E");
	    Ac.put("pressed E", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "E";
	                System.out.println("Pressed E");
	            }
	        });

	    Ac.put("released E", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released E");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "pressed E");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true), "released E");
	    Ac.put("pressed E", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "E";
	                System.out.println("Pressed E");
	            }
	        });

	    Ac.put("released E", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released E");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, false), "pressed T");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, true), "released T");
	    Ac.put("pressed T", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "T";
	                System.out.println("Pressed T");
	            }
	        });

	    Ac.put("released T", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released T");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "pressed D");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "released D");
	    Ac.put("pressed D", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "D";
	                System.out.println("Pressed D");
	            }
	        });

	    Ac.put("released D", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released D");
	        }
	    });



	    setFocusable(true);
	    requestFocusInWindow(); 
	}

	// private void buttonInput(String input) {
	// 		InputMap In = getInputMap(WHEN_IN_FOCUSED_WINDOW);
	//     	ActionMap Ac = getActionMap();
	//     	In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "pressed " + input);
	// 	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true), "released " + input);
	// 	    Ac.put("pressed "  + input, new AbstractAction() {
	// 	            @Override
	// 	            public void actionPerformed(ActionEvent e) {
	// 	            	Pressed =  input;
	// 	                System.out.println("Pressed "  + input);
	// 	            }
	// 	        });

	// 	    Ac.put("released "  + input, new AbstractAction() {
	// 	        @Override
	// 	        public void actionPerformed(ActionEvent e) {
	// 	            System.out.println("released " + input);
	// 	        }
	// 	    });
	//     }

	//Handling mouse click events	
	public void mouseClicked(MouseEvent e)
	 {
	 	boolean checkOccupied = isStateOccupied(e);
	 	boolean checkOccupied2 = isEdgeOccupied(e);
	 	x = e.getX();
	 	y = e.getY();

	 	if (Pressed.equals("S")) {
	 		tCount = 1;
	 		selectedNode = null;
	 		System.out.println("click S");
	 		Node newNode = finiteStateMachine.addNode('a');
	 		ViewNode newViewNode = new ViewNode(x,y,WIDTH, HEIGHT, newNode);
	 		viewNodeList.add(newViewNode);
	 		repaint();
	 	}

	 	if (Pressed.equals("C")) {
	 		tCount = 1;
	 		selectedNode = null;
	 	}

	 	if (Pressed.equals("E")) {
	 		tCount = 1;
	 		if (checkOccupied || checkOccupied2) {
	 			repaint();
	 		}
	 		
	 	}

	 	if (Pressed.equals("T")) {
	 		
	 		
	 		
	 		if (checkOccupied) {
		 		if (tCount % 2 != 0) {
		 			double posX = selectedNode.getX();
		 			double posY = selectedNode.getY();
		 			fromNode = selectedNode.getNode();
		 			T1 = new double[2];
		 			T1[0] = posX;
		 			T1[1] = posY;
		 			tCount = 0;
 		 		} else {
 		 			double posX = selectedNode.getX();
		 			double posY = selectedNode.getY();
		 			toNode = selectedNode.getNode();
		 			T2 = new double[2];
		 			T2[0] = posX;
		 			T2[1] = posY;
		 			Edge newEdge = finiteStateMachine.addArrow(fromNode, toNode, "a");
		 			ViewEdge newViewEdge = new ViewEdge(T1[0],T1[1],T2[0],T2[1], newEdge);
		 			viewEdgeList.add(newViewEdge);
		 			tCount = 1;
		 			selectedNode = null;
		 			repaint();
 		 		}
	 		}
	 	}
	 }
	 //Check if the place the mouse click is occupied or not
	 private boolean isStateOccupied (MouseEvent e) {
	 	boolean temp = false;
	 	for (ViewNode element : viewNodeList) {
	 		if (element.getCircle().contains(e.getX(), e.getY())) {
	 			selectedNode = element;
	 			selectedEdge = null;
	 			temp = true;
	 		}
	 	}

	 	return temp;
	 }

	 private boolean isEdgeOccupied (MouseEvent e) {
	 	boolean temp = false;
	 	double posX = (double) e.getX();
		double posY = (double) e.getY();
		Point2D point = new Point2D.Double(posX, posY);	
			for (ViewEdge element : viewEdgeList) {
			
	 		if (element.getLine().contains(point)) {
	 			selectedNode = null;
	 			selectedEdge = element;
	 			temp = true;
	 		}
	 	}
	 	return temp;
	 }

	 // private ViewNode getOccupied () {

	 // }


    public void mouseEntered(MouseEvent e)
     {
     	
     }
     public void mouseExited(MouseEvent e)
     {
     	
     }
     public void mousePressed(MouseEvent e)
     {
     	System.out.println("mousePressed at " + e.getX() + ", " + e.getY());
     	if (Pressed.equals("D") && isStateOccupied(e)){
		    
		    distanceX = e.getX();
		    distanceY = e.getY();
		    System.out.println("mousePressed at " + distanceX + ", " + distanceY);
		    inDrag = true;
     	}
     	
     }
     
     public void mouseReleased(MouseEvent e) 
     {
     	inDrag = false;
     	System.out.println(distanceX + ", " + distanceY);
     	distanceX = 0;
     	distanceY = 0;
	    
     }

     public void mouseDragged(MouseEvent e) {
	    // System.err.println("mouse drag to " + p);

	    distanceX = e.getX() - distanceX;
	    distanceY = e.getY() - distanceY;

	    double curX = selectedNode.getX();
	    double curY = selectedNode.getY();
	    // selectedNode.setX(curX + distanceX);
	    // selectedNode.setY(curY + distanceY);
	    selectedNode.moveCircle(e.getX(), e.getY());
	    if (inDrag) {
	      repaint();
	    }
	  }

  public void mouseMoved(MouseEvent e) {
    // System.out.println("mouse Moved to " + e.getX() + e.getY());
  }



    // public boolean isInsideEllipse(Point point) {
    //     return new Ellipse2D.Float(x, y, width, height).contains(point);
    // }
}
