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
    Ellipse2D.Double smallCircle;

    public ViewNode(double x, double y, double w, double h, Node newNode) {
    	circle = new Ellipse2D.Double(x - w/2, y - w/2, w ,h);
    	node = newNode;
    	posX = x;
    	posY = y;
    	posW = w;
        smallCircle = new Ellipse2D.Double(x -(w/2 - 3.5), y-(w/2 - 3.5), w-7, h-7);
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
        smallCircle = new Ellipse2D.Double(x - (posW/2 - 3.5), y-(posW/2 - 3.5), posW-7, posW-7); 
    }

    public boolean isAccept (){
        return node.getAccepting();
    }

    public Ellipse2D getSmallCircle() {
        return smallCircle;
    }

}
