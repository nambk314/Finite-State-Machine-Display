package edu.union.adt.fsm;

public class Node
{
    String label;
    boolean accept;

    public Node(String startLabel)
    {
      this.label = startLabel;
    }

    public String getLabel(){
      return this.label;
    }

    public boolean getAccepting(){
      return this.accept;
    }

}
