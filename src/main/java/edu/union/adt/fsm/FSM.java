package edu.union.adt.fsm;

public interface FSM
{
    /**
     * Add a blank node to the current FSM
     */
    public void addNode(char label);

    /**
     * Add an arrow to the current FSM
     */
    public void addArrow(Node from, Node to, String label);

    /**
     * Edit the label of a node of the FSM
     */
    public void setNodeLabel(Node toEdit, char newLabel);

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
    public void update();
}