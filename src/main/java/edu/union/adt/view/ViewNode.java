package edu.union.adt.view;

import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.*;
import java.awt.geom.Line2D;

public class ViewNode
{
    Node node; //String, boolean
    Ellipse2D.Double circle; // int int int int
    private double posX;
    private double posY;
    private double posW;
    Ellipse2D.Double smallCircle;
    Line2D startPathOne;
    Line2D startPathTwo;
    double diameter;

    public ViewNode(double x, double y, double w, double h, Node newNode) {
    	
    	node = newNode;
    	posX = x;
    	posY = y;
    	posW = w;
        circle = new Ellipse2D.Double(posX - w/2, posY - w/2, w ,h);
        smallCircle = new Ellipse2D.Double(posX -(w/2 - 3.5), posY-(w/2 - 3.5), w-7, h-7);
        startPathOne = new Line2D.Double();
        startPathTwo = new Line2D.Double();

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
        posX = x;
    }

    public void setY(double y) {
        posY = y;
    }

    public void moveCircle(double x, double y) {
        posX = x;
        posY = y;
    	circle.setFrame(x - posW/2, y-posW/2, posW, posW);
        smallCircle.setFrame(x - (posW/2 - 3.5), y-(posW/2 - 3.5), posW-7, posW-7); 
    }
// Function to create the start symbol for the starting node
    public void makeStart() {
        double radius = posW/2;
        startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
        startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
    }

    // public void setStart() {
    //     double radius = posW/2;
    //     startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
    //     startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
    // }

    public Line2D[] getStartShape() {
        Line2D[] startShape = new Line2D[2];
        startShape[0] = startPathOne;
        startShape[1] = startPathTwo;
        return startShape;
    }
    public boolean isAccept (){
        return node.getAccepting();
    }

    public Ellipse2D getSmallCircle() {
        return smallCircle;
    }

}
