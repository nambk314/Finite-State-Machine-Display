package edu.union.adt.fsm;
import java.util.*;

public class ConcreteFSM()
{
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;


    public ConcreteFSM()
    {
      this.nodes = new ArrayList<Node>();
      this.edges = new ArrayList<Edge>();
    }

    /**
     * Add a blank node to the current FSM
     */
    public void addNode();

    /**
     * Add an arrow to the current FSM
     */
    public void addArrow(Node from, Node to);

    /**
     * Edit the label of a node of the FSM
     */
    public void setNodeLabel(Node toEdit, char newLabel);

    /**
     * Edit the label of an edge of the FSM
     */
    public void setEdgeLabel(Edge toEdit, char newLabel);

    /**
     * Remove a node from the FSM
     */
    public void remove(Node toRemove);

    /**
     * Remove an edge from the FSM
     */
    public void remove(Edge toRemove);

    /**
     * Move an element of the FSM
     */
    public void move(Element);

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

    /**
     * Lets views know that an update has occurred
     */
    public void update();
}
