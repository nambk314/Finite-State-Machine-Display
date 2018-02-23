package edu.union.adt.view;

import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.Node;

public class ViewNode
{
    Node node; //String, boolean
    Ellipse2D.Double circle; // int int int int
    private double posX;
    private double posY;
    private double posW;

    public ViewNode(double x, double y, double w, double h, Node newNode) {
    	circle = new Ellipse2D.Double(x - w/2, y - w/2, w ,h);
    	node = newNode;
    	posX = x;
    	posY = y;
    	posW = w;
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

    public void setX(double x) {
    	circle = new Ellipse2D.Double(x - posW/2, posY - posW/2, posW ,posW);
    	posX = x;
    }

    public void setY(double y) {
    	circle = new Ellipse2D.Double(posX - posW/2, y - posW/2, posW ,posW);
    	posY = y;
    }

    public void moveCircle(double x, double y) {
    	circle = new Ellipse2D.Double(x - posW/2, y-posW/2, posW, posW);
    }

}
