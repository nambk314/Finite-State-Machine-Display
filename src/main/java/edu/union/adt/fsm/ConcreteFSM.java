package edu.union.adt.fsm;
import java.util.*;
import edu.union.adt.view.addFSMListener;
public class ConcreteFSM implements FSM
{
    ArrayList<Node> Nodes = new ArrayList<Node>();                  //ArrayList of Nodes
    ArrayList<Edge> Edges = new ArrayList<Edge>();                  //ArrayList of Edges
    ArrayList<Character> extantNodes = new ArrayList<Character>();  //ArrayList of node characters, to make checking for duplicates faster / cleaner
    Node start = null;

    //Listener to the view
    private ArrayList<addFSMListener> listeners;

    /**
     * Set which node is the start
     */
     public void setStart(Node toStart){
       this.start = toStart;
     }

     /**
      * Get which node is the start
      */
      public Node getStart(){
        return this.start;
      }

    /**
     * Add a blank node to the current FSM
     */
    public Node addNode(char label)
    {
      if(extantNodes.contains(label)){                //If this node exists, return null
        return null;
    } else {
      Node newNode = new Node(label);                 //Else, create it, add it to Nodes, and remember that it exists
      Nodes.add(newNode);
      extantNodes.add(label);

      // notifyListeners();

      return newNode;
      }
  }

    public void changeAccept(Node toChange)
    {
      toChange.accept = !toChange.accept;
    }

    /**
     * Add an arrow to the current FSM
     */
    public Edge addEdge(Node from, Node to, String label)
    {
      try{                                                      //Add a new edge
        Edge newEdge = new Edge(from, to, label);
        Edges.add(newEdge);
        return newEdge;
        // notifyListeners();
      } catch (NullPointerException e){                         //Catch nullPointer (i.e., if one of the nodes does not exist)
        return null;
      }

    }

    /**
     * Edit the label of a node of the FSM
     */
    public boolean setNodeLabel(Node toEdit, char newLabel)
    {
      if (extantNodes.contains(newLabel)){
        return false;
      } else {
        int index = extantNodes.indexOf(toEdit.label);
        extantNodes.set(index, newLabel);
        toEdit.label = newLabel;
        // notifyListeners();
        return true;
      }
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

    public void addListener(addFSMListener l) {
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

    public boolean containsNode(Node node) {
	boolean contains = false;
	int k = 0;
	while (k<Nodes.size() && contains==false) {
	    if (Nodes.get(k).getLabel().equals(node.getLabel())) {
		contains = true;
	    }
	}
	return contains;
    }
    
}
