package edu.union.adt.view;
//Package for shapes
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import edu.union.adt.fsm.Edge;

import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

import edu.union.adt.fsm.*;

public class ViewEdge {

    Edge edge;
    Line2D.Double line;
    Path2D myPath;
    double[] original = new double[4];
    ViewNode fromViewNode;
    ViewNode toViewNode;
    int diameter =50;
    HashMap<Node, ViewNode> map;

//Constructor that take a hashmap and an edge as parameters
    public ViewEdge(HashMap map, Edge newEdge) {
        Node fromNode = newEdge.getFrom();
        Node toNode = newEdge.getTo();
        fromViewNode = (ViewNode) map.get(fromNode);
        toViewNode = (ViewNode) map.get(toNode);
        original[0] = fromViewNode.getX();
        original[1] = fromViewNode.getY();
        original[2] = toViewNode.getX();
        original[3] = toViewNode.getY();
        edge = newEdge;
    }
//Constructor that take a fromViewNode toViewNode and a newEdge as constructor
    public  ViewEdge(ViewNode fromViewNodeNew, ViewNode toViewNodeNew, Edge newEdge) {
	// line = new Line2D.Double(x1, y1, x2, y2);
    fromViewNode = fromViewNodeNew;
    toViewNode = toViewNodeNew;
	original[0] = fromViewNode.getX();
	original[1] = fromViewNode.getY();
	original[2] = toViewNode.getX();
	original[3] = toViewNode.getY();
	edge = newEdge;
    }

    //Function that produce the nice line that do not go into the circle
    private double[] niceLine(double[] posList) {
        int radius = diameter/2;
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
//Function to get the line graphic to represent the edge

    public void makeLine() {
        original[0] = fromViewNode.getX();
        original[1] = fromViewNode.getY();
        original[2] = toViewNode.getX();
        original[3] = toViewNode.getY();
        line = new Line2D.Double(niceLine(original)[0], niceLine(original)[1], niceLine(original)[2], niceLine(original)[3]);
        
    }     
    public Line2D getLine() {
        original[0] = fromViewNode.getX();
        original[1] = fromViewNode.getY();
        original[2] = toViewNode.getX();
        original[3] = toViewNode.getY();
        line = new Line2D.Double(niceLine(original)[0], niceLine(original)[1], niceLine(original)[2], niceLine(original)[3]);
        
		return line;
    }

    public void makePath() {
        myPath = new Path2D.Double();
        myPath.moveTo(niceLine(original)[2], niceLine(original)[3]);
        myPath.lineTo(niceLine(original)[4], niceLine(original)[5]);
        myPath.lineTo(niceLine(original)[6], niceLine(original)[7]);
        myPath.closePath();
    }

    public Path2D getPath() {
        myPath = new Path2D.Double();
        myPath.moveTo(niceLine(original)[2], niceLine(original)[3]);
        myPath.lineTo(niceLine(original)[4], niceLine(original)[5]);
        myPath.lineTo(niceLine(original)[6], niceLine(original)[7]);
        myPath.closePath();
    	return myPath;
    }

    public Edge getEdge(){
      return this.edge;
    }
//Position the label equal to 1/4 the total length of the edge
    public double getTextX() {
        double textX = (3*original[0] + original[2])/4;
        return textX;
    }

    public double getTextY() {
        double textY = (3*original[1] + original[3])/4;
        return textY;
    }

}
