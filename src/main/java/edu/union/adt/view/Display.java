/*
 Author: Nam Bui
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
import java.awt.geom.RectangularShape;
import java.awt.Shape;

import java.awt.Shape;
import java.awt.Color;

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

//For the node's name
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import edu.union.adt.fsm.*;
import java.util.ArrayList;
//Buttons, textField, FileChooser

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
// import java.text.StringCharacterIterator;
// import java.text.AttributedCharacterIterator;


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
	private static int WIDTH = 50;
	private static int HEIGHT = 50;

	//Keep track of coordinates
	private int x;
	private int y;

	//Keep track of the key being pressed
	private String Pressed = "";

	private ViewNode selectedNode = null;
	private ViewEdge selectedEdge = null;

	//Keep track of viewNode and edgeNode
	ArrayList<ViewNode> viewNodeList = new ArrayList<ViewNode>();
	ArrayList<ViewEdge> viewEdgeList = new ArrayList<ViewEdge>();

	//count number of time T being press
	private int tCount = 1;
	private Node fromNode;
	private Node toNode;

	//Variable to know if it is inDrag or not
	private boolean inDrag = false;
	//Variable to know the distance went dragging
	private double distanceX = 0;
	private double distanceY = 0;

	//Variable to indicated start view ndoe
	private ViewNode startViewNode = null;


	//Hash table to hold the key and value
	HashMap<Node, ViewNode> map;

	//File saver variable
	FileSaver file;

	//For testing theme
	int themeCount = 0;

	//Buttons
	private JButton open = new JButton("Open"), save = new JButton("Save");

	public Display(ConcreteFSM theFiniteStateMachine)
	{
		Font myFont = new Font("TimesRoman", Font.PLAIN, 12);
		this.setFont(myFont);
		FontMetrics metrics = getFontMetrics(myFont);

		setSize(new Dimension(1000, 1000));
		setPreferredSize(new Dimension(1000, 1000));

		finiteStateMachine = theFiniteStateMachine;

		myHandler = new UpdateHandler(this, finiteStateMachine);
		map = new HashMap<>();
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
				piece.makeShape();
				Shape Shape = piece.getNodeShape();
				g.setStroke(new BasicStroke(2));
				g.setColor(piece.getColor());
				g.draw(Shape);

				if (piece.isAccept()) {
					Shape smallCircle = piece.getSmallShape();
					// g.setStroke(new BasicStroke(2));
					// g.setColor(Color.BLACK);
					g.draw(smallCircle);
				}
				int stateX = (int)piece.getX();
				int stateY = (int)piece.getY();
				g.drawString(piece.getNode().getLabel(), stateX, stateY);

				if (piece == startViewNode) {
					startViewNode.makeStart();
					Shape[] startShape = startViewNode.getStartShape();
					// g.setStroke(new BasicStroke(2));
					// g.setColor(Color.BLACK);
					g.draw(startShape[0]);
					g.draw(startShape[1]);
				}
				//g.drawString("a", 100, 100);
			}
			ViewEdge edgePiece;
			for (int i=0; i < viewEdgeList.size(); i++) {
				edgePiece = viewEdgeList.get(i);
				
				
				Line2D line = edgePiece.getLine();
				Path2D path = edgePiece.getPath();
				// g.setStroke(new BasicStroke(2));
				// g.setColor(Color.BLACK);
				g.draw(line);
				g.draw(path);
				int edgeX = (int)edgePiece.getTextX() - 10;
				int edgeY = (int)edgePiece.getTextY()  - 10;
				g.drawString(edgePiece.getEdge().getLabel(), edgeX, edgeY);

			}

			// if (startViewNode != null) {
			// 	startViewNode.makeStart();
			// 	Path2D startShape = startViewNode.getStartShape();
			// 	// g.setStroke(new BasicStroke(2));
			// 	// g.setColor(Color.BLACK);
			// 	g.draw(startShape);
			// }

			if (selectedEdge != null && Pressed.equals("E")) {
				Line2D selectedLine = selectedEdge.getLine();
				Path2D selectedPath = selectedEdge.getPath();
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.BLUE);
				g.draw(selectedLine);
				g.draw(selectedPath);
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

	public void paint(){
		repaint();
	}

	public void go()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
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

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressed A");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "released A");
	    Ac.put("pressed A", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "A";
	                System.out.println("Pressed A");
	            }
	        });

	    Ac.put("released A", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released A");
	        }
	    });

	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), "pressed H");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, true), "released H");
	    Ac.put("pressed H", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Pressed = "H";
	                System.out.println("Pressed H");
	            }
	        });

	    Ac.put("released H", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("released H");
	        }
	    });
//TEST CHANGING THEME
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressed P");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true), "released P");
	    Ac.put("pressed P", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	ViewNode temp = viewNodeList.get(0);
	            	if (themeCount % 2 == 0) {
	            		temp.changeTheme("rectangle", "blue");
	            		themeCount = 1;
	            	} else if (themeCount % 2 == 1){
	            		temp.changeTheme("circle", "black");
	            		themeCount = 0;
	            	}
	            	
	            	Pressed = "P";
	                System.out.println("Pressed P");
	            }
	        });

	    Ac.put("released P", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

	            System.out.println("released P");
	            repaint();
	        }
	    });

//TEST SAVING
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, false), "pressed L");
	    In.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, true), "released L");
	    Ac.put("pressed L", new AbstractAction() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	Pressed = "L";
	                System.out.println("Pressed L");
	            }
	        });

	    Ac.put("released L", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {

	            System.out.println("released L");
	        }
	    });


	    setFocusable(true);
	    requestFocusInWindow();
	}

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
	 		String name = JOptionPane.showInputDialog("Please input a chararter for the node name");
	 		while (name.equals("")) {
	 			name = JOptionPane.showInputDialog("You can not have node without label");
	 		}
	 		System.out.println(name);
	 		Node newNode = finiteStateMachine.addNode(name);
	 		if (newNode != null) {
	 			ViewNode newViewNode = new ViewNode(x,y,WIDTH, HEIGHT, newNode);
	 			viewNodeList.add(newViewNode);
	 			map.put(newNode, newViewNode);
	 		}
	 		finiteStateMachine.notifyListeners();
	 		//repaint();
	 	}

	 	if (Pressed.equals("C")) {
	 		tCount = 1;
	 		selectedNode = null;
	 	}

	 	if (Pressed.equals("E")) {
	 		tCount = 1;
	 		if (checkOccupied) {
	 			String newNodeName = JOptionPane.showInputDialog("Please input a chararter for the node name");
		 		while (newNodeName.equals("") || finiteStateMachine.setNodeLabel(selectedNode.getNode(), newNodeName) == false) {
		 			newNodeName = JOptionPane.showInputDialog("Your label can not be blank or be the same as other nodes");
		 		}

	 			finiteStateMachine.notifyListeners();
	 		} else if (checkOccupied2) {
	 			String newEdgeName = JOptionPane.showInputDialog("Please input a chararter for the node name");
		 		while (newEdgeName.equals("")) {
		 			newEdgeName = JOptionPane.showInputDialog("Your label can not be blank or be the same as other nodes");
		 		}
		 		finiteStateMachine.setEdgeLabel(selectedEdge.getEdge(), newEdgeName);
	 			finiteStateMachine.notifyListeners();
	 		}

	 	}

	 	if (Pressed.equals("T")) {



	 		if (checkOccupied) {
		 		if (tCount % 2 != 0) {
		 			double posX = selectedNode.getX();
		 			double posY = selectedNode.getY();
		 			fromNode = selectedNode.getNode();
		 			tCount = 0;
 		 		} else {
 		 			double posX = selectedNode.getX();
		 			double posY = selectedNode.getY();
		 			toNode = selectedNode.getNode();
		 			String edgeName = JOptionPane.showInputDialog("Please input a chararter for the edge name");
			 		while (edgeName.equals("")) {
			 			edgeName = JOptionPane.showInputDialog("You can not have edge without label");
			 		}
			 		System.out.println(edgeName);
		 			Edge newEdge = finiteStateMachine.addEdge(fromNode, toNode, edgeName);
		 			ViewEdge newViewEdge = new ViewEdge(map, newEdge);
		 			viewEdgeList.add(newViewEdge);
		 			tCount = 1;
		 			selectedNode = null;
		 			finiteStateMachine.notifyListeners();
 		 		}
	 		}
	 	}

	 	if (Pressed.equals("A")) {
	 		if (checkOccupied) {
	 			finiteStateMachine.changeAccept(selectedNode.getNode());
	 			repaint();
	 		}
	 	}

	 	if (Pressed.equals("H")) {
	 		if (checkOccupied) {
	 			startViewNode = selectedNode;
	 			finiteStateMachine.setStart(startViewNode.getNode());
	 			finiteStateMachine.notifyListeners();
	 		}
	 	}

	 	if(Pressed.equals("L")) {
	 		String fileName = JOptionPane.showInputDialog("Please input a name for the file name");
	 		while (fileName.equals("")) {
	 			fileName = JOptionPane.showInputDialog("You can not have a file without a name");
	 		}
	    	file = new FileSaver(fileName);
	    	ArrayList<ArrayList> ViewMachine = new ArrayList<>();
	    	ViewMachine.add(viewNodeList);
	    	ViewMachine.add(viewEdgeList);
	    	file.save(ViewMachine);
	    	System.out.println("saving");
	 	}
	 }

	 //Check if the place the mouse click is occupied or not
	 private boolean isStateOccupied (MouseEvent e) {
	 	boolean temp = false;
	 	for (ViewNode element : viewNodeList) {
	 		if (element.getNodeShape().contains(e.getX(), e.getY())) {
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
			Line2D checkLine = (Line2D) element.getLine();
	 		if (checkLine.ptSegDist(point) <= 5) {
	 			selectedNode = null;
	 			selectedEdge = element;
	 			temp = true;
	 		}
	 	}
	 	System.out.println(temp);
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
     	//System.out.println("mousePressed at " + e.getX() + ", " + e.getY());
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
     	//System.out.println(distanceX + ", " + distanceY);
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
	    //selectedNode.makeStart();
	    selectedNode.makeStart();
	    selectedNode.moveShape(e.getX(), e.getY());
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
