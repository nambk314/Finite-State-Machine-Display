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
	line = new Line2D.Double(x1, y1, x2, y2);
	original[0] = x1;
	original[1] = y1;
	original[2] = x2;
	original[3] = y2;
	edge = newEdge;
    }

    // private double[] niceLine(double[] posList) {
    // 	double distance = 
    // }
    public Line2D getLine() {
		return line;
    }
    
}


