package edu.union.adt.view;

import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.Node;

public class ViewNode
{
    Node node; //String, boolean
    Ellipse2D.Double circle; // int int int int
    private double posX;
    private double posY;

    public ViewNode(double x, double y, double w, double h, Node newNode) {
    	circle = new Ellipse2D.Double(x - w/2, y - w/2, w ,h);
    	node = newNode;
    	posX = x;
    	posY = y;
    }

    public Ellipse2D getCircle() {
    	return circle;
    }

    public Node getNode() {
    	return node;
    }

    public double getX() {
    	return posX;
    }

    public double getY() {
    	return posY;
    }

}
