package edu.union.adt.fsm;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import edu.union.adt.fsm.Edge;
import java.lang.Math;

public class ViewEdge {

    Edge edge;
    Line2D.Double line;
    double[] original = new double[4];
    
    public  ViewEdge(double x1, double y1, double x2, double y2, Edge newEdge) {
	// line = new Line2D.Double(x1, y1, x2, y2);
	original[0] = x1;
	original[1] = y1;
	original[2] = x2;
	original[3] = y2;

	double newPoint = niceLine(original);
	line = new Line2D.Double(newPoint[0], newPoint[1], newPoint[2], newPoint[3]);

	edge = newEdge;
    }

    private double[] niceLine(double[] posList) {
    	double x1 = posList[0];
    	double y1 = posList[1];
    	double x2 = posList[2];
    	double y2 = posList[3];

    	double distance = Math.hypot(x2-x1,y2-y1);
    	double distanceRatio = 30/distance;
    	double xyRatio = (x2-x1)/(y2-y1);

    	double newX1 = x1 + (-(x2-x1)*distanceRatio));
    	double newY1 = y1 + (-(y2-y1)*distanceRatio));
    	double newX2 = x2 + (x2-x1)*distanceRatio;
    	double newY2 = y2 + (y2-y1)*distanceRatio

    	double[] newList = new double[4];
    	newList[0] = newX1;
    	newList[1] = newY1;
    	newList[2] = newX2;
    	newList[3] = newY2;

    	return newList;
    	 }
    public Line2D getLine() {
		return line;
    }
    
}


