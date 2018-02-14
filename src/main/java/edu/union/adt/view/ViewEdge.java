package edu.union.adt.fsm;
import java.awt.geom.Line2D;
import edu.union.adt.fsm.Edge;

public class ViewEdge {

    Edge edge;
    Line2D.Double line;

    
    public  ViewEdge(int x1, int y1, int x2, int y2, Edge newEdge) {
	edge = new Line2D.Double;
	selected = false;
	edge = newEdge;
    }

    public Line2D returnEdge() {
	return edge;
    }
    
}


