package edu.union.adt.fsm;

public class Edge
{
    /**
     *
     */
    String label;
    Node to;
    Node from;

    Edge(Node startTo, Node startFrom, String startLabel)
    {
      this.label = startLabel;
      this.to = startTo;
      this.from = startFrom;
    }
}
