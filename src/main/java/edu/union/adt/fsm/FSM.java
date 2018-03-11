package edu.union.adt.fsm;
import java.util.*;

public interface FSM
{

  /**
   * Set which node is the start
   */
   public void setStart(Node toStart);


   /**
    * Add a blank node to the current FSM
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

    /**
     * Takes an ArrayList of Nodes and a string, returns
     * an ArrayList of Nodes which can be reached.
     */
     public ArrayList<Node> getNextStates(ArrayList<Node> nodes, String transition);

     /**
      * Takes an ArrayList of Strings
      * returns whether or not an accepting state is reached
      */
      public boolean isSeriesAccepted(ArrayList<String> series);

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
       Returns true if node exists in fsm,
       else, returns false.
     */
    public boolean containsNode(Node node);

    /**
       Returns true if the edge exists in fsm,
       else, returns false.
     */
    public boolean containsEdge(Edge edge);

    /**
     * Lets views know that an update has occurred
     */
    public void notifyListeners();

    public void clear();

}
