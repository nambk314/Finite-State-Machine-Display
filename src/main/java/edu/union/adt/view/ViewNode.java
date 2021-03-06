/*
* @author Nam Bui
*/


package edu.union.adt.view;
import edu.union.adt.shape.*;
import java.awt.geom.Ellipse2D;
import edu.union.adt.fsm.*;
import java.awt.geom.Line2D;
import java.awt.Color;
import java.awt.geom.RectangularShape;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

public class ViewNode
{
    Node node; //String, boolean

    String[] theme;
    double posX;
    double posY;
    double posW;
    
    ShapeNodeFactory factory;
    ShapeNode nodeShape; 

    public ViewNode(double x, double y, double w, double h, Node newNode, String [] theme) {
    	
    	node = newNode;
    	posX = x;
    	posY = y;
    	posW = w;
        //theme = new String[]{"circle", "black"};
        // circle = new Ellipse2D.Double(posX - w/2, posY - w/2, w ,h);
        // rectangle = new Rectangle2D.Double(posX - w/2, posY - w/2, w ,h);
        // smallCircle = new Ellipse2D.Double(posX -(w/2 - 3.5), posY-(w/2 - 3.5), w-7, h-7);
        // smallRectangle = new Rectangle2D.Double(posX -(w/2 - 3.5), posY-(w/2 - 3.5), w-7, h-7);
        // startPathOne = new Line2D.Double();
        // startPathTwo = new Line2D.Double();
        factory = new ShapeNodeFactory();
        this.theme = theme;
        nodeShape = factory.getShapeNode(theme[0], this);

    }
//Getter method
    public Shape getNodeShape() {
     //    Shape shape;
    	// switch (theme[0]) {
     //        case "rectangle":
     //        shape = (Shape) rectangle;
     //        break;
     //        case "circle":
     //        shape = (Shape) circle;
     //        break;
     //        default:
     //        shape = (Shape) circle;
     //    }
        
     //    return shape;
    	return nodeShape.getNodeShape();
    }

    public Node getNode() {
    	return node;
    }

    public double getX() {
    	return posX;
    }

    public double getY() {
    	return posY;
    }

    public double getW() {
        return posW;
    }

    public Color getColor() {
        Color mainColor = Color.BLACK;        
        switch (theme[1]) {
            case "black":
            mainColor = Color.BLACK;
            break;
            case "green":
            mainColor = Color.GREEN;
            break;
            case "red":
            mainColor = Color.RED;
            break;
            default:
            mainColor = null;
        }

        return mainColor;
    }

//Setter methods
    public void setX(double x) {
        posX = x;
    }

    public void setY(double y) {
        posY = y;
    }

    public void setTheme(String shape, String color) {
        theme[0] = shape;
        theme[1] = color;
    }

    public void makeShape() {
        nodeShape = factory.getShapeNode(theme[0], this);
       // switch (theme[0]) {
       //      case "rectangle":
       //      rectangle.setFrame(posX - posW/2, posY-posW/2, posW, posW);
       //  	smallRectangle.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7); 
       //      break;
       //      case "circle":
       //      circle.setFrame(posX - posW/2, posY-posW/2, posW, posW);
       //      smallCircle.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7); 
       //      break;
       //      default:
            
       //  }
    	nodeShape.makeShape();
    }
    public void moveShape(double x, double y) {
        posX = x;
        posY = y;
        nodeShape.moveShape(x, y);
        // switch (theme[0]) {
        //     case "rectangle":
        //     rectangle.setFrame(posX - posW/2, posY-posW/2, posW, posW);
        // 	smallRectangle.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7); 
        //     break;
        //     case "circle":
        //     circle.setFrame(posX - posW/2, posY-posW/2, posW, posW);
        //     smallCircle.setFrame(posX - (posW/2 - 3.5), posY-(posW/2 - 3.5), posW-7, posW-7); 
        //     break;
        //     default:
            
        // }
    	
    }
// Function to create the start symbol for the starting node
    public void makeStart() {
        // double radius = posW/2;
        // startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
        // startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
        nodeShape.makeStart();
    }

    // public void setStart() {
    //     double radius = posW/2;
    //     startPathOne.setLine(posX - 3*(radius/2), posY - radius/2,posX - radius, posY);
    //     startPathTwo.setLine(posX - 3*(radius/2), posY + radius/2,posX - radius, posY);
    // }

    public Shape[] getStartShape() {
        // Line2D[] startShape = new Line2D[2];
        // startShape[0] = startPathOne;
        // startShape[1] = startPathTwo;
        // return startShape;
        return nodeShape.getStart();
    }
    public boolean isAccept (){
        return node.getAccepting();
    }

    public Shape getSmallShape() {
    	// RectangularShape shape;
    	// switch (theme[0]) {
     //        case "rectangle":
     //        shape = (RectangularShape) smallRectangle;
     //        break;
     //        case "circle":
     //        shape = (RectangularShape) smallCircle;
     //        break;
     //        default:
     //        shape = (RectangularShape) circle;
     //    }
        
     //    return shape;
    	return nodeShape.getSmallShape();
    }

    // public void changeTheme(String shape, String color) {
    //     theme[0] = shape;
    //     theme[1] = color;
    // }

}
