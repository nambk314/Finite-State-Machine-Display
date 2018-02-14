package edu.union.adt.fsm;

public class Edge
{
    /**
     *
     */
    String label;
    Node to;
    Node from;

    public Edge(Node startFrom, Node startTo, String startLabel)
    {
      this.label = startLabel;
      this.to = startTo;
      this.from = startFrom;
    }
}
