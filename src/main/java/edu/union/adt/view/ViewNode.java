package edu.union.adt.view;

import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.Node;

public class ViewNode
{
    boolean selected; //default false?
    Node node; //String, boolean
    Ellipse2D.Double circle; // int int int int
    private int posX;
    private int posY;

    public ViewNode(int x, int y, int w, int h, Node newNode) {
    	circle = new Ellipse2D.Double(x - w/2, y - w/2, w ,h);
    	selected = false;
    	node = newNode;
    	posX = x - w/2;
    	posY = y - w/2;
    }

    public Ellipse2D getCircle() {
    	return circle;
    }

    public void changeSelected(boolean change) {
    	selected = change;
    }

    public int getX() {
    	return posX;
    }

    public int getY() {
    	return posY;
    }

}
