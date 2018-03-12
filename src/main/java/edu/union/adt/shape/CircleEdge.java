/*
Circle Shape that represent the ShapeNode
Author: Nam Bui
*/
package edu.union.adt.shape;
import edu.union.adt.view.*;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import java.awt.geom.Arc2D;


public class CircleEdge implements ShapeEdge {

	//For circle edge  
    Line2D.Double line;
    Path2D myPath;
    Arc2D.Double selfLoop;
    Path2D mySelfPath;
    double[] original;
    double diameter = 50;
    ViewEdge newVE;


    public CircleEdge(ViewEdge viewEdge) {
        original = viewEdge.getOriginal();
        newVE = viewEdge;
    }

    private double[] niceLine(double[] posList) {
        double radius = diameter/2;
        double x1 = posList[0];
        double y1 = posList[1];
        double x2 = posList[2];
        double y2 = posList[3];

        double distance = Math.hypot(x2-x1,y2-y1);
        double distanceRatio = radius/distance;
        double xyRatio = (x2-x1)/(y2-y1);

        double newX1 = x1 + (x2-x1)*distanceRatio;
        double newY1 = y1 + (y2-y1)*distanceRatio;
        double newX2 = x2 -(x2-x1)*distanceRatio;
        double newY2 = y2 - (y2-y1)*distanceRatio;

        double polygonPointX = x2-(x2-x1)*(radius + 5)/distance;
        double polygonPointY = y2-(y2-y1)*(radius + 5)/distance;
        double polygonPointX1 = polygonPointX + (y2-y1)*3/distance;
        double polygonPointY1 = polygonPointY - (x2-x1)*3/distance;

        double polygonPointX2 = polygonPointX - (y2-y1)*3/distance;
        double polygonPointY2 = polygonPointY + (x2-x1)*3/distance;



        double[] newList = new double[8];
        newList[0] = newX1;
        newList[1] = newY1;
        newList[2] = newX2;
        newList[3] = newY2;

        //Coordinate for the pointer
        newList[4] = polygonPointX1;
        newList[5] = polygonPointY1;
        newList[6] = polygonPointX2;
        newList[7] = polygonPointY2;


        return newList;
         }
        public void updatePosition() {
            original[0] = newVE.getFromViewNode().getX();
            original[1] = newVE.getFromViewNode().getY();
            original[2] = newVE.getToViewNode().getX();
            original[3] = newVE.getToViewNode().getY();
        }
        public void makeLine() {
        updatePosition();
        line = new Line2D.Double(niceLine(original)[0], niceLine(original)[1], niceLine(original)[2], niceLine(original)[3]);
        }   

        public Shape getLine() {
        
        return (Shape) line;
    }
        public void makePath() {
        myPath = new Path2D.Double();
        myPath.moveTo(niceLine(original)[2], niceLine(original)[3]);
        myPath.lineTo(niceLine(original)[4], niceLine(original)[5]);
        myPath.lineTo(niceLine(original)[6], niceLine(original)[7]);
        myPath.closePath();
    }

    public Shape getPath() {
        return (Shape) myPath;
    }


    public void makeSelfLoop() {
        
        updatePosition();
        double newX = original[0] - diameter;
        double newY = original[1] - diameter;

        selfLoop = new Arc2D.Double(newX,newY,diameter,diameter, 0, 270, Arc2D.OPEN);
    }

    public Shape getSelfLoop() {
        return (Shape) selfLoop;
    }

    public void makeSelfLoopPath() {
        updatePosition();
        double radius = diameter/2;
        mySelfPath = new Path2D.Double();
        mySelfPath.moveTo(original[0] - radius, original[1]);
        mySelfPath.lineTo(original[0] -radius - 5, original[1] - 5);
        mySelfPath.lineTo(original[0] - radius - 5, original[1] + 5);
        mySelfPath.closePath();
    }

    public Shape getSelfLoopPath() {
        return mySelfPath;
    }

    public double getTextX() {
        double textX = (3*original[0] + original[2])/4;
        return textX;
    }

    public double getTextY() {
        double textY = (3*original[1] + original[3])/4;
        return textY;
    }

    public double getTextXSelf() {

        double textX = original[0] - diameter/2;
        return textX;
    }

    public double getTextYSelf() {
        double textY = original[1] - diameter;
        return textY;
    }


    
}