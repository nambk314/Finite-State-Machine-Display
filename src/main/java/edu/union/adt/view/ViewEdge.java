package edu.union.adt.view;
//Package for shapes
import edu.union.adt.shape.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import edu.union.adt.fsm.Edge;
import java.awt.Shape;

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
    ShapeEdge shapeEdge;
    String[] theme;
    ShapeEdgeFactory edgeFactory;

//Constructor that take a hashmap and an edge as parameters
    public ViewEdge(HashMap map, Edge newEdge, String[] theme) {
        Node fromNode = newEdge.getFrom();
        Node toNode = newEdge.getTo();
        fromViewNode = (ViewNode) map.get(fromNode);
        toViewNode = (ViewNode) map.get(toNode);
        original[0] = fromViewNode.getX();
        original[1] = fromViewNode.getY();
        original[2] = toViewNode.getX();
        original[3] = toViewNode.getY();
        edge = newEdge;
        edgeFactory = new ShapeEdgeFactory();
        this.theme = theme;
        // shapeEdge = edgeFactory.getShapeEdge(theme[0], this);
    }

    //Function that produce the nice line that do not go into the circle
    // private double[] niceLine(double[] posList) {
    //     int radius = diameter/2;
    // 	double x1 = posList[0];
    // 	double y1 = posList[1];
    // 	double x2 = posList[2];
    // 	double y2 = posList[3];

    // 	double distance = Math.hypot(x2-x1,y2-y1);
    // 	double distanceRatio = radius/distance;
    // 	double xyRatio = (x2-x1)/(y2-y1);

    // 	double newX1 = x1 + (x2-x1)*distanceRatio;
    // 	double newY1 = y1 + (y2-y1)*distanceRatio;
    // 	double newX2 = x2 -(x2-x1)*distanceRatio;
    // 	double newY2 = y2 - (y2-y1)*distanceRatio;

    // 	double polygonPointX = x2-(x2-x1)*(radius + 5)/distance;
    // 	double polygonPointY = y2-(y2-y1)*(radius + 5)/distance;
    // 	double polygonPointX1 = polygonPointX + (y2-y1)*3/distance;
    // 	double polygonPointY1 = polygonPointY - (x2-x1)*3/distance;

    // 	double polygonPointX2 = polygonPointX - (y2-y1)*3/distance;
    // 	double polygonPointY2 = polygonPointY + (x2-x1)*3/distance;



    // 	double[] newList = new double[8];
    // 	newList[0] = newX1;
    // 	newList[1] = newY1;
    // 	newList[2] = newX2;
    // 	newList[3] = newY2;

    //     //Coordinate for the pointer
    // 	newList[4] = polygonPointX1;
    // 	newList[5] = polygonPointY1;
    // 	newList[6] = polygonPointX2;
    // 	newList[7] = polygonPointY2;


    // 	return newList;
    // 	 }


    //Getter
    public double[] getOriginal() {

        return original;
    }

    public ViewNode getFromViewNode() {
        return fromViewNode;
    }

    public ViewNode getToViewNode() {
        return toViewNode;
    }

//Function to make the line graphic to represent the edge

    public void makeLine() {
        shapeEdge = edgeFactory.getShapeEdge(theme[0], this);
        // original[0] = fromViewNode.getX();
        // original[1] = fromViewNode.getY();
        // original[2] = toViewNode.getX();
        // original[3] = toViewNode.getY();
        // line = new Line2D.Double(niceLine(original)[0], niceLine(original)[1], niceLine(original)[2], niceLine(original)[3]);
        shapeEdge.makeLine();
    }     
    public Shape getLine() {
  //       original[0] = fromViewNode.getX();
  //       original[1] = fromViewNode.getY();
  //       original[2] = toViewNode.getX();
  //       original[3] = toViewNode.getY();
  //       line = new Line2D.Double(niceLine(original)[0], niceLine(original)[1], niceLine(original)[2], niceLine(original)[3]);
        
		// return line;
        return shapeEdge.getLine();
    }

    public void makePath() {
        // myPath = new Path2D.Double();
        // myPath.moveTo(niceLine(original)[2], niceLine(original)[3]);
        // myPath.lineTo(niceLine(original)[4], niceLine(original)[5]);
        // myPath.lineTo(niceLine(original)[6], niceLine(original)[7]);
        // myPath.closePath();
        shapeEdge.makePath();
    }

    public Shape getPath() {
     //    myPath = new Path2D.Double();
     //    myPath.moveTo(niceLine(original)[2], niceLine(original)[3]);
     //    myPath.lineTo(niceLine(original)[4], niceLine(original)[5]);
     //    myPath.lineTo(niceLine(original)[6], niceLine(original)[7]);
     //    myPath.closePath();
    	// return myPath;
        return shapeEdge.getPath();
    }

    public Edge getEdge(){
      return this.edge;
    }

    //Getting the self loop graphics
    public void makeSelfLoop() {
        shapeEdge.makeSelfLoop();
    }

    public Shape getSelfLoop() {
        return shapeEdge.getSelfLoop();
    }

    public void makeSelfLoopPath() {
        shapeEdge.makeSelfLoopPath();
    }
    public Shape getSelfLoopPath() {
        return shapeEdge.getSelfLoopPath();
    }
//Position the label equal to 1/4 the total length of the edge
    public double getTextX() {
        return shapeEdge.getTextX();
    }

    public double getTextY() {
        return shapeEdge.getTextY();
    
    }
    public double getTextXSelf() {

        return shapeEdge.getTextXSelf();
    }

    public double getTextYSelf() {
        return shapeEdge.getTextYSelf();
    }


}
