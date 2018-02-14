package edu.union.adt.fsm;
import java.util.*;
import edu.union.adt.view.addFSMListener;
public class ConcreteFSM implements FSM
{
    ArrayList<Node> Nodes = new ArrayList<Node>();
    ArrayList<Edge> Edges = new ArrayList<Edge>();

    //Listener to the view
    private ArrayList<addFSMListener> listeners;

    /**
     * Add a blank node to the current FSM
     */
    public Node addNode(char label)
    {
      Node newNode = new Node(label);  
      Nodes.add(newNode);


      // notifyListeners();
      return newNode;
    }

    public void changeAccept(Node toChange)
    {
      toChange.accept = !toChange.accept;
    }

    /**
     * Add an arrow to the current FSM
     */
    public Edge addArrow(Node from, Node to, String label)
    {  
      Edge newEdge = new Edge(from, to, label);
      Edges.add(newEdge);
      return newEdge;
      // notifyListeners();

    }

    /**
     * Edit the label of a node of the FSM
     */
    public void setNodeLabel(Node toEdit, char newLabel)
    {
      toEdit.label = newLabel;
      // notifyListeners();
    }

    public void setEdgeLabel(Edge toEdit, String newLabel)
    {
      toEdit.label = newLabel;
      // notifyListeners();
    }


    // /**
    //  * Remove a Node from the FSM
    //  */
    // public void removeNode(Node toRemove);

    // /**
    //  * Remove an Edge from the FSM
    //  */
    // public void removeEdge(Edge toRemove);

    /**
     * Returns an ArrayList len = 2
     * The first item is an ArrayList of nodes
     * The second item is an ArrayList of edges
     * Each node is an ArrayList containing
     * x and y position, label, and whether or not it's accepting
     * Each edge is an ArrayList containing
     * radius, label, fromX, fromY, toX, and toY
     */
    public ArrayList getMachine()
    {
      ArrayList<ArrayList> toReturn = new ArrayList<ArrayList>();
      toReturn.add(Nodes);
      toReturn.add(Edges);
      return toReturn;
    }

    /**
     * Lets views know that an update has occurred
     */

    public void addListener(addFSMListener l)
    {
        listeners.add(l);
    }
    
    public void removeListener(addFSMListener l)
    {
        listeners.remove(l);
    }
    
    public void notifyListeners()
    {
        for (addFSMListener l : listeners) {
            l.update();
        }
    }
}
