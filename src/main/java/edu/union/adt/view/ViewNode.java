package edu.union.adt.view;

import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.Node;

public class ViewNode
{
    boolean selected; //default false?
    Node node; //String, boolean
    Ellipse2D.Double circle; // int int int int

    public ViewNode(int x, int y, int w, int h, Node newNode) {
    	circle = new Ellipse2D.Double(x + w, y - w, w ,h);
    	selected = false;
    	node = newNode;
    }

    public Ellipse2D getCircle() {
    	return circle;
    }

    public boolean isSelected() {
    	return selected;
    }

    public void changeSelected(boolean change) {
    	selected = change;
    }

}
