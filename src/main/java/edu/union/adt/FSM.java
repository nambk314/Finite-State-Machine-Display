package edu.union.adt.fsm;

public interface FSM
{
    /**
     * Add a blank node to the current FSM
     */
    public void addNode(int x, int y);

    /**
     * Add an arrow to the current FSM
     */
    public void addArrow(Element from, Element to);

    /**
     * Edit the label of an element (arrow or node) of the FSM
     */
    public void editLabel(Element toEdit, String newLabel);

    /**
     * Remove an element from the FSM
     */
    public void remove(Element toRemove);

    /**
     * Move an element of the FSM
     */
    public void move(Element toMove, int newX, int newY);

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
