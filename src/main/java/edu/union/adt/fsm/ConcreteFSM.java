package edu.union.adt.fsm;
import java.util.*;

public class ConcreteFSM implements FSM
{
    ArrayList<Node> Nodes = new ArrayList<Node>();
    ArrayList<Edge> Edges = new ArrayList<Edge>();

    /**
     * Add a blank node to the current FSM
     */
    public void addNode(char label)
    {
      Nodes.add(new Node(label));
    }

    public void changeAccept(Node toChange)
    {
      toChange.accept = !toChange.accept;
    }

    /**
     * Add an arrow to the current FSM
     */
    public void addArrow(Node from, Node to, String label)
    {
      Edges.add(new Edge(from, to, label));
    }

    /**
     * Edit the label of a node of the FSM
     */
    public void setNodeLabel(Node toEdit, char newLabel)
    {
      toEdit.label = newLabel;
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
    public void update()
    {

    }
}
