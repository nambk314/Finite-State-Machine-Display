package edu.union.adt.fsm;
import java.util.*;

public interface FSM
{

  /**
   * Set which node is the start
   */
   public void setStart(Node toStart);

   /**
    * Get which node is the start
    */
    public Node getStart();

    /**
     * Add a blank node to the current FSM
     */
    public Node addNode(String label);

    /**
     * Add an edge to the current FSM
     */
    public Edge addEdge(Node from, Node to, String label);

    /**
     * Edit the label of a node of the FSM
     */
    public boolean setNodeLabel(Node toEdit, String newLabel);

    /**
     * Edit the label of an edge of the FSM
     */
    public void setEdgeLabel(Edge toEdit, String newLabel);

    /**
     * Change the accepting state of a Node
     */
    public void changeAccept(Node toChange);


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
    public ArrayList getMachine();

    /**
     * Lets views know that an update has occurred
     */
    public void notifyListeners();

}
