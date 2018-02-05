package edu.union.adt.fsm;

public interface FSM
{
    /**
     *
     */
    public void addNode();

    /**
     *
     */
    public void addArrow();

    /**
     */
    public void editLabel(Element);

    /**
     */
    public void remove(Element);

    /**
     */
    public void move(Element);

    /**
     */
    public ArrayList getMachine();
}
