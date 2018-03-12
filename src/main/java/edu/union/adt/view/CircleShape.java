/*
Circle Shape that represent the ShapeNode
Author: Nam Bui
*/
package edu.union.adt.view;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.geom.Line2D;


public class CircleShape implements ShapeNode {

	//For circle shape   
    Ellipse2D.Double circle; // int int int int
    Ellipse2D.Double smallCircle;
    private double posX;
    private double posY;
    private double posW;

    Line2D startPathOne;
    Line2D startPathTwo;
//Graphics for the node
    public CircleShape(ViewNode viewNode) {
    	posX = viewNode.posX;
    	posY = viewNode.posY;
    	posW = viewNode.posW;
    	startPathOne = new Line2D.Double();
        startPathTwo = new Line2D.Double();
    }
	public void makeShape() {
		circle = new Ellipse2D.Double(posX - posW/2, posY - posW/2, posW,posW);
        smallCircle = new Ellipse2D.Double(posX -(posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7);	

	}
	public void makeStart(){
		double radius = posW/2;
        startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
        startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
	}

	public Shape getNodeShape() {
		return (Shape) circle;
	}

	public Shape getSmallShape() {
		return (Shape) smallCircle;
	}

	public void moveShape(double x, double y) {
		posX = x;
        posY = y;
        circle.setFrame(posX - posW/2, posY-posW/2, posW, posW);
        smallCircle.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7);
	}

	public Shape[] getStart(){
		Shape[] startShape = new Shape[2];
        startShape[0] = startPathOne;
        startShape[1] = startPathTwo;
        return startShape;
	}
//Graphics for the edge
    
}