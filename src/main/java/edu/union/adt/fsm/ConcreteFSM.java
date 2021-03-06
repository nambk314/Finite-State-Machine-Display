package edu.union.adt.fsm;
import java.util.*;
import edu.union.adt.view.addFSMListener;
public class ConcreteFSM implements FSM
{
    ArrayList<Node> Nodes = new ArrayList<Node>();                  //ArrayList of Nodes
    ArrayList<Edge> Edges = new ArrayList<Edge>();                  //ArrayList of Edges
    ArrayList<String> extantNodes = new ArrayList<String>();  //ArrayList of node Strings, to make checking for duplicates faster / cleaner
    Node start = null;

    //Listener to the view
    private ArrayList<addFSMListener> listeners;

    /**
     * Set which node is the start
     */
    public ConcreteFSM () {
      listeners = new ArrayList();
    }
     public void setStart(Node toStart){
       this.start = toStart;
     }

     /**
      * Get which node is the start
      */
      public Node getStart(){
        return this.start;
      }

    /**
     * Add a blank node to the current FSM
     */
    public Node addNode(String label)
    {
      if(extantNodes.contains(label)){                //If this node exists, return null
        return null;
    } else {
      Node newNode = new Node(label);                 //Else, create it, add it to Nodes, and remember that it exists
      Nodes.add(newNode);
      extantNodes.add(label);

      // notifyListeners();

      return newNode;
      }
  }

    public void changeAccept(Node toChange)
    {
      toChange.accept = !toChange.accept;
    }

    /**
     * Add an arrow to the current FSM
     */
    public Edge addEdge(Node from, Node to, String label)
    {
      try{                                                      //Add a new edge
        Edge newEdge = new Edge(from, to, label);
        Edges.add(newEdge);
        return newEdge;
        // notifyListeners();
      } catch (NullPointerException e){                         //Catch nullPointer (i.e., if one of the nodes does not exist)
        return null;
      }

    }

    /**
     * Edit the label of a node of the FSM
     */
    public boolean setNodeLabel(Node toEdit, String newLabel)
    {
      if (extantNodes.contains(newLabel)){
        return false;
      } else {
        int index = extantNodes.indexOf(toEdit.label);
        extantNodes.set(index, newLabel);
        toEdit.label = newLabel;
        // notifyListeners();
        return true;
      }
    }

    public void setEdgeLabel(Edge toEdit, String newLabel)
    {
      toEdit.setLabel(newLabel);
      // notifyListeners();
    }


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
    public ArrayList getMachine()
    {
      ArrayList<ArrayList> toReturn = new ArrayList<ArrayList>();
      toReturn.add(Nodes);
      toReturn.add(Edges);
      return toReturn;
    }


    /**
     * Takes an ArrayList of Nodes and a string, returns
     * an ArrayList of Nodes which can be reached.
     */
     public ArrayList<Node> getNextStates(ArrayList<Node> startingNodes, String transition){
       ArrayList<Node> toReturn = new ArrayList<Node>();

       //adds all nodes reached only through ϵ-transitions
       ArrayList<Node> tertiaryNodes = new ArrayList<Node>();
       ArrayList<Node> newerNodes = new ArrayList<Node>();
       ArrayList<Node> removeNodes;
      for (Node initialNode : startingNodes){
        newerNodes.add(initialNode);
        do{
          removeNodes = (ArrayList<Node>)newerNodes.clone();
          for (Node secondaryNode : newerNodes){
           for (Edge potentialE : this.Edges){
             if ((potentialE.from == secondaryNode) && (potentialE.hasTransition("ϵ"))){
               if (!toReturn.contains(potentialE.to)){
                 toReturn.add(potentialE.to);
                 tertiaryNodes.add(potentialE.to);
               }
             }
           }
         }
         for (Node toRemove : removeNodes){
           newerNodes.remove(toRemove);
         }
         removeNodes.clear();
         newerNodes.addAll(tertiaryNodes);
         tertiaryNodes.clear();
       } while (newerNodes.size() != 0);
     }

       // //adds all nodes reached through the transition, starting
       // //at a node reached through a an ϵ-transition
       // startingNodes = (ArrayList<Node>)toReturn.clone();
       // for (Node nodeCheck : startingNodes){
       //   for (Edge checkEdge : this.Edges){
       //     if ((checkEdge.from == nodeCheck) && checkEdge.hasTransition(transition)){
       //       if (!toReturn.contains(checkEdge.to)){
       //         toReturn.add(checkEdge.to);
       //       }
       //     }
       //   }
       // }
      //adds all nodes reached through only the transition
      startingNodes.addAll(toReturn);
       for (Node nodeCheck : startingNodes){
         for (Edge checkEdge : this.Edges){
           if ((checkEdge.from == nodeCheck) && checkEdge.hasTransition(transition)){
             if (!toReturn.contains(checkEdge.to)){
               toReturn.add(checkEdge.to);
             }
           }
         }
       }

       //adds all nodes reached through ϵ-transitions, after
       //nodes have been reached through the given transition
      newerNodes.clear();
      startingNodes = (ArrayList<Node>)toReturn.clone();
      for (Node initialNode : startingNodes){
        newerNodes.add(initialNode);
        do{
          removeNodes = (ArrayList<Node>)newerNodes.clone();
          for (Node secondaryNode : newerNodes){
           for (Edge potentialE : this.Edges){
             if ((potentialE.from == secondaryNode) && (potentialE.hasTransition("ϵ"))){
               if (!toReturn.contains(potentialE.to)){
                 toReturn.add(potentialE.to);
                 tertiaryNodes.add(potentialE.to);
               }
             }
           }
         }
         for (Node toRemove : removeNodes){
           newerNodes.remove(toRemove);
         }
         removeNodes.clear();
         newerNodes.addAll(tertiaryNodes);
         tertiaryNodes.clear();
       } while (newerNodes.size() != 0);
     }
        System.out.println(toReturn);
       return toReturn;
     }

     /**
      * Takes an ArrayList of Strings
      * returns whether or not an accepting state is reached
      */
      public boolean isSeriesAccepted(ArrayList<String> series){
        ArrayList<Node> currentNodes = new ArrayList<Node>();
        currentNodes.add(this.start);
        for (String transition : series){
          currentNodes = getNextStates(currentNodes, transition);
        }
        boolean toReturn = false;
        for (Node ending : currentNodes){
          if (ending.getAccepting()){
            toReturn = true;
          }
        }
        return toReturn;
      }

      /**
       * Returns true if the given node exists in the FSM, false otherwise
       */
    public boolean containsNode(Node node) {
      return this.Nodes.contains(node);
    }

    /**
     * Returns true if the given edge exists in the FSM, false otherwise
     */
    public boolean containsEdge(Edge edge) {
      return this.Edges.contains(edge);
    }

    /**
     * Lets views know that an update has occurred
     */

    public void addListener(addFSMListener l) {
        listeners.add(l);
    }

    public void removeListener(addFSMListener l)
    {
        listeners.remove(l);
    }

    public void notifyListeners()
    {
        for (addFSMListener l : listeners) {
            l.update();
        }
    }

    public String toString(){
      String toReturn = "Start = " + this.start;
      toReturn += "\n Nodes:\n";
      toReturn += this.Nodes.toString();
      toReturn += "\n Edges:\n";
      toReturn += this.Edges.toString();
      return toReturn;
    }

    public void clear(){
      this.Nodes = new ArrayList<Node>();                  //ArrayList of Nodes
      this.Edges = new ArrayList<Edge>();                  //ArrayList of Edges
      this.extantNodes = new ArrayList<String>();  //ArrayList of node Strings, to make checking for duplicates faster / cleaner
      this.start = null;
      //notifyListeners();
    }

}
