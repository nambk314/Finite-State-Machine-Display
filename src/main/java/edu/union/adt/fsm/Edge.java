package edu.union.adt.fsm;
import java.util.*;

public class Edge
{
    /**
     *
     */
    String label;
    ArrayList<String> transitionList;
    Node to;
    Node from;

    public Edge(Node startFrom, Node startTo, String startLabel)
    {
      this.label = startLabel;
      this.to = startTo;
      this.from = startFrom;
    }

    public String getLabel(){
      return this.label;
    }

    public Node getFrom(){
      return this.from;
    }

    public Node getTo(){
      return this.to;
    }

    public boolean hasTransition(String transition){
      if (this.transitionList.contains(transition)){
        return true;
      } else {
        return false;
      }
    }

    public void setLabel(String newLabel){
      this.label = newLabel;
      String[] x = newLabel.split(",");
      this.transitionList = new ArrayList<String>(Arrays.asList(x));
    }

    public String toString(){
      return "from = " + from.label + " to = " + to.label +" transitions are " + this.label;
    }


}
