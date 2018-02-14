package edu.union.adt.fsm;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import edu.union.adt.fsm.Edge;

public class ViewEdge {

    Edge edge;
    Line2D.Double line;
    Point2D from;
    Point2D to;
    
    public  ViewEdge(int x1, int y1, int x2, int y2, Edge newEdge) {
	line = new Line2D.Double(x1, y1, x2, y2);
	selected = false;
	edge = newEdge;
    }

    public Line2D getLine() {
		return line;
    }
    
}


