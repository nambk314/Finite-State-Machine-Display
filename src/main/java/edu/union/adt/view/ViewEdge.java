package edu.union.adt.view;

import java.awt.geom.Line2D;
import edu.union.adt.fsm.Edge;

public class ViewEdge
    
{
    boolean selected;; //default false?
    
    Edge edge; // String, node.getX, node.getY
    public ViewEdge () {
    	selected = false;
    	
    }
}
