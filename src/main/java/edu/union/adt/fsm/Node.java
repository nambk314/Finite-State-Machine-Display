package edu.union.adt.fsm;

public class Node
{
    char label;
    boolean accept;

    public Node(char startLabel)
    {
      this.label = startLabel;
    }

    public char getLabel(){
      return this.label;
    }

    public boolean getAccepting(){
      return this.accept;
    }

}
