/*
Factory for the shape
*/
package edu.union.adt.shape;
import edu.union.adt.view.*;
public class ShapeNodeFactory {

	public static ShapeNode getShapeNode(String type, ViewNode viewNode){
		if("CIRCLE".equalsIgnoreCase(type)) return new CircleShape(viewNode);
		if("SQUARE".equalsIgnoreCase(type)) return new SquareShape(viewNode);
		return null;
	}
}