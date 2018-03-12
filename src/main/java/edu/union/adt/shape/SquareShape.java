/*
Circle Shape that represent the ShapeNode
Author: Nam Bui
*/
package edu.union.adt.shape;
import edu.union.adt.view.*;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.geom.Line2D;


public class SquareShape implements ShapeNode {

	//For circle shape   
    Rectangle2D.Double square; // int int int int
    Rectangle2D.Double smallSquare;
    private double posX;
    private double posY;
    private double posW;

    Line2D startPathOne;
    Line2D startPathTwo;
//Graphics for the node
    public SquareShape(ViewNode viewNode) {
    	posX = viewNode.getX();
    	posY = viewNode.getY();
    	posW = viewNode.getW();
    	startPathOne = new Line2D.Double();
        startPathTwo = new Line2D.Double();
    }
	public void makeShape() {
		square = new Rectangle2D.Double(posX - posW/2, posY - posW/2, posW ,posW);
        smallSquare = new Rectangle2D.Double(posX -(posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7);

	}
	public void makeStart(){
		double radius = posW/2;
        startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
        startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
	}

	public Shape getNodeShape() {
		return (Shape) square;
	}

	public Shape getSmallShape() {
		return (Shape) smallSquare;
	}

	public void moveShape(double x, double y) {
		posX = x;
        posY = y;
        square.setFrame(posX - posW/2, posY-posW/2, posW, posW);
        smallSquare.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7);
	}

	public Shape[] getStart(){
		Shape[] startShape = new Shape[2];
        startShape[0] = startPathOne;
        startShape[1] = startPathTwo;
        return startShape;
	}
//Graphics for the edge
    
}