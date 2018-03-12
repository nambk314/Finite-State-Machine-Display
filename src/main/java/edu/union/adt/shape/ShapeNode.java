// Interface to represent the shape for the nodes so that we can have different theme.
package edu.union.adt.shape;
import edu.union.adt.view.*;
import java.awt.Shape;
public interface ShapeNode {
	public void makeShape();
	public void makeStart();
	public Shape getNodeShape();

	public Shape getSmallShape();

	public void moveShape(double x, double y);

	public Shape[] getStart();
}