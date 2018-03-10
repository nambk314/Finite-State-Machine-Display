/*
Factory for the shape
*/
package edu.union.adt.view;
public class ShapeNodeFactory {

	public static ShapeNode getShapeNode(String type, ViewNode viewNode){
		if("CIRCLE".equalsIgnoreCase(type)) return new CircleShape(viewNode);
		
		return null;
	}
}