/*
Factory for the shape
*/
package edu.union.adt.shape;
import edu.union.adt.view.*;
public class ShapeEdgeFactory {

	public static ShapeEdge getShapeEdge(String type, ViewEdge viewEdge){
		if("CIRCLE".equalsIgnoreCase(type)) return new CircleEdge(viewEdge);
		if("SQUARE".equalsIgnoreCase(type)) return new SquareEdge(viewEdge);
		return null;
	}
}