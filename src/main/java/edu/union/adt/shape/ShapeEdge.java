// Interface to represent the shape for the nodes so that we can have different theme.
package edu.union.adt.shape;
import edu.union.adt.view.*;
import java.awt.Shape;
public interface ShapeEdge {
	public void makeLine();
	public void makePath();
	public Shape getPath();

	public Shape getLine();
	public void makeSelfLoop();
	public Shape getSelfLoop();
	public void makeSelfLoopPath();

    public Shape getSelfLoopPath();
    public double getTextX();

    public double getTextY();

    public double getTextXSelf();

    public double getTextYSelf();
}