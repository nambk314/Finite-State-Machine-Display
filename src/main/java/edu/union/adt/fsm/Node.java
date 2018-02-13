package edu.union.adt.fsm;

public class Node
{
    char label;
    boolean accept;

    Node(char startLabel)
    {
      this.label = startLabel;
    }
}
